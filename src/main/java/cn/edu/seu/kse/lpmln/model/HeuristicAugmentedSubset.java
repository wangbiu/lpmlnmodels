package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;
import cn.edu.seu.kse.lpmln.util.UnionFindSet;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.getLiteral;

/**
 * @author 许鸿翔
 * @date 2018/3/31
 */
public class HeuristicAugmentedSubset extends AugmentedSubset {
    /**
     * 对于原子的限制，描述于规则和AS上
     * 共三位，4表示not，2表示+，1表示-
     * 1表示可行，0表示不可行
     * 010表示原子a只支持a
     * 101表示原子可以是not a或者-a
     */
    private Map<String,Integer> atomRestrict;
    /**
     * sat需要加的限制，析取式，无法成立的项去除
     */
    private SATRestrict[] satRestricts;
    /**
     * unsat需要加的限制，合取表达式
     */
    private Map<String,Integer>[] unsatRestricts;
    private Set<Integer> enumerable;
    /**
     * 从文字到规则下标idx的映射，表示satRestricts[idx]中需要考虑这个文字
     */
    private Map<String,Set<Integer>> activeRuleRestrict;
    /**
     * lit所有状态都可行
     */
    private static final int TRUE=7;
    private double weight;
    private List<String> allLiterals;
    private List<Loop> loops;
    /**
     * 文字到支持条件映射，破坏支持条件会导致规则体部无法被满足，外部支持规则无法支持
     */
    private Map<String,Set<SupportCond>> supConds;
    /**
     * 支持条件到loop的映射，无支持条件会导致loop失败
     */
    private Map<SupportCond,Loop> supLoop;
    /**
     * literal到loop的映射，辅助用
     */
    private Map<String,Loop> litToLoop;
    private Map<Loop,Set<SupportCond>> loopSupports;
    private Set<SupportCond> failedConds;
    private Set<Integer> truthSatIdx;
    private Map<String,Integer> truthRes;

    public HeuristicAugmentedSubset(LpmlnProgram lpmlnProgram) {
        super(lpmlnProgram);
        init();
        List<Rule> rules = lpmlnProgram.getRules();
        for(int i=0;i<rules.size();i++){
            getRuleCond(rules.get(i),i);
        }
        for(int i=0;i<rules.size();i++){
            if(!unknownIdx.contains(i)){
                unknownIdx.add(i);
                sat(i);
            }
        }
        findLoops();
        filtSatRestricts();
        refreshWeight();
        System.out.println("1:"+System.currentTimeMillis());
        System.out.println("init done");
    }

    private void init(){
        allLiterals = new ArrayList<>();
        lpmlnProgram.getRules().forEach(r->{
            r.getHead().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
            r.getPositiveBody().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
            r.getNegativeBody().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
        });
        allLiterals = new ArrayList<>(new HashSet<>(allLiterals));
        enumerable = new HashSet<>(unknownIdx);
        atomRestrict = new HashMap<>();
        satRestricts = new SATRestrict[lpmlnProgram.getRules().size()];
        unsatRestricts = new HashMap[lpmlnProgram.getRules().size()];
        activeRuleRestrict = new HashMap<>();
        loops = new ArrayList<>();
        supLoop = new HashMap<>();
        supConds = new HashMap<>();
        litToLoop = new HashMap<>();
        truthRes = new HashMap<>();
        loopSupports = new HashMap<>();
        failedConds = new HashSet<>();
    }

    private void filtSatRestricts(){
        for(int i=0;i<satRestricts.length;i++){
            SATRestrict satRestrict = satRestricts[i];
            for (String lit : new HashSet<>(satRestrict.restrict.keySet())) {
                int strict = satRestrict.restrict.get(lit);
                int current = atomRestrict.get(lit);
                //条件无法成立
                if((strict&atomRestrict.get(lit))==0){
                    satRestrict.restrict.remove(lit);
                }
                //规则无法成立
                if((current|strict)==strict){
                    satIdx.add(i);
                    break;
                }
            }
        }
        truthSatIdx = satIdx;
        satIdx = new HashSet<>();
        new HashSet<Map.Entry<String,Integer>>(atomRestrict.entrySet()).forEach(ent->{
            if(getCount(ent.getValue())==1){
                atomRestrict.remove(ent.getKey());
                truthRes.put(ent.getKey(),ent.getValue());
            }
        });
    }

    private void findLoops(){
        Map<String,Set<String>> pdg = LpmlnProgramHelper.getPostiveDependency(lpmlnProgram);
        Map<String,Set<String>> reachable = getReachable(pdg);
        Set<Set<String>> loopLits = getLoopLits(reachable);
        loopLits.forEach(litSet->{
            Loop loop = new Loop(litSet);
            loops.add(loop);
            litSet.forEach(lit->{
                litToLoop.put(lit,loop);
            });
        });
        allLiterals.forEach(lit->{
            if(!unchangable(lit)){
                supConds.put(lit,new HashSet<>());
            }
        });
        buildSupCond();
        loops.forEach(loop-> loopSupports.put(loop,new HashSet<>()));
        supLoop.forEach((cond,loop)->{
           loopSupports.get(loop).add(cond);
        });
    }

    private Set<Set<String>> getLoopLits(Map<String,Set<String>> reachable){
        UnionFindSet<String> unionFindSet = new UnionFindSet<>(reachable.keySet());
        reachable.forEach((from,toSet)->{
            toSet.forEach(to->{
                if(reachable.keySet().contains(to)&&reachable.get(to).contains(from)){
                    unionFindSet.join(from,to);
                }
            });
        });
        Map<String,Set<String>> ansMap = new HashMap<>();
        reachable.keySet().forEach(lit->{
            String root = unionFindSet.find(lit);
            Set<String> rootAim;
            if(ansMap.containsKey(root)){
                rootAim = ansMap.get(root);
            }else{
                rootAim = new HashSet<>();
                ansMap.put(root,rootAim);
            }
            rootAim.add(lit);
        });
        return new HashSet<>(ansMap.values());
    }

    private Map<String,Set<String>> getReachable(Map<String,Set<String>> pdg){
        Map<String,Set<String>> reachable = new HashMap<>();
        pdg.keySet().forEach(start->{
            Set<String> end = new HashSet<>();
            reachable.put(start,end);
            LinkedList<String> toVisit = new LinkedList<>(pdg.get(start));
            while(toVisit.size()>0){
                String next = toVisit.poll();
                if(!end.contains(next)){
                    end.add(next);
                    if(pdg.containsKey(next)){
                        toVisit.addAll(pdg.get(next));
                    }
                }
            }
        });
        return reachable;
    }


    public int getRuleIdx(){
        System.out.println("2:"+System.currentTimeMillis());
        int ans=-1;
        double eval=0;
        //System.out.println("unknownIdx size:"+unknownIdx.size());
        int sum=0;
        for (int i : unknownIdx) {
            long clonestart = System.currentTimeMillis();
            double nextEval;
            //System.out.println("11:"+System.currentTimeMillis());
            HeuristicAugmentedSubset positive = this.clone();
            HeuristicAugmentedSubset negative = this.clone();
            //sum += (System.currentTimeMillis()-clonestart);
            //System.out.println("12:"+System.currentTimeMillis());
            boolean sat = positive.sat(i);
            //System.out.println("13:"+System.currentTimeMillis());
            boolean unsat = negative.unsat(i);
            //System.out.println("14:"+System.currentTimeMillis());
            if(sat&&unsat){
                nextEval = positive.weight+negative.weight-Math.abs(positive.weight-negative.weight);
                //System.out.println(""+positive.weight+"\t"+negative.weight);
                if(nextEval>eval){
                    ans = i;
                    eval = nextEval;
                }
            }
            //System.out.println("15:"+System.currentTimeMillis());
        }
        System.out.println("3:"+System.currentTimeMillis());
        return ans;
    }

    /**
     * 生成规则对于谓词的要求，暂时未处理重复谓词
     * @param r 需要处理的规则
     * @param idx 规则在程序中的位置
     */
    private void getRuleCond(Rule r,int idx){
        SATRestrict satRestrict = new SATRestrict();
        satRestricts[idx] = satRestrict;
        Map<String,Integer> unsatRestrict = new HashMap<>();
        unsatRestricts[idx] = unsatRestrict;
        List<String> body = new ArrayList<>(r.getPositiveBody());
        body.addAll(r.getNegativeBody());
        r.getHead().forEach(headLit->{
            atomRestrict.put(getLitCond(headLit).realLit,TRUE);
            LitCond cond = getLitCond(headLit);
            satRestrict.restrict.put(cond.realLit,cond.cond|satRestrict.restrict.getOrDefault(cond.realLit,0));
            unsatRestrict.put(cond.realLit,(TRUE^cond.cond)&unsatRestrict.getOrDefault(cond.realLit,TRUE));
        });
        body.forEach(bodyLit->{
            equation(bodyLit);
            LitCond cond = getLitCond(bodyLit);
            satRestrict.restrict.put(cond.realLit,(TRUE^cond.cond)|satRestrict.restrict.getOrDefault(cond.realLit,0));
            unsatRestrict.put(cond.realLit,cond.cond&unsatRestrict.getOrDefault(cond.realLit,TRUE));
        });
    }

    private boolean unchangable(String lit){
        return atomRestrict.containsKey(lit)&&(getCount(atomRestrict.get(lit))==1);
    }

    private void buildSupCond(){
        lpmlnProgram.getRules().forEach(rule -> {
            rule.getHead().forEach(supLit->{
                String realSup = getLiteral(supLit);
                List<LitCond> conds = new ArrayList<>();
                rule.getPositiveBody().forEach(lit->conds.add(getLitCond(lit)));
                rule.getNegativeBody().forEach(lit->conds.add(getLitCond(lit)));
                rule.getHead().stream().filter(head->!getLiteral(head).equals(realSup)).forEach(lit->conds.add(getLitCond("not "+lit)));

                boolean supportable = true;
                SupportCond supCond = new SupportCond();
                for (LitCond cond : conds) {
                    if(!satisfied(cond)){
                        if(satisfiable(cond)){
                            supCond.support.put(cond.realLit,cond.cond);
                        }else{
                            supportable = false;
                            break;
                        }
                    }
                }
                if(supportable){
                    if(supCond.support.size()==0){
                        if(!rule.isSoft()){
                            killLoop(litToLoop.get(realSup));
                        }
                    }else{
                        supCond.support.forEach((k,v)->{
                            supConds.get(k).add(supCond);
                        });
                        if(litToLoop.get(realSup)==null){
                            System.out.println("");
                        }
                        supLoop.put(supCond,litToLoop.get(realSup));
                    }
                }
            });
        });
    }

    private void killLoop(Loop loop){
        loops.remove(loop);
        Iterator it = supLoop.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<SupportCond,Loop> ent = (Map.Entry<SupportCond,Loop>)it.next();
            if(ent.getValue().equals(loop)){
                it.remove();
            }
        }
    }

    private boolean satisfiable(LitCond cond){
        return (atomRestrict.getOrDefault(cond.realLit,TRUE)&cond.cond)!=0;
    }

    private boolean satisfied(LitCond cond){
        int stat = atomRestrict.getOrDefault(cond.realLit,TRUE)&truthRes.getOrDefault(cond.realLit,TRUE);
        return stat==(cond.cond&stat);
    }

//    private void buildSupCond(Rule r,String supLit){
//        SupportCond supCond = new SupportCond();
//        //supLoop.put(supCond,litToLoop.get(supLit));
//        r.getHead().forEach(headLit->{
//            LitCond cond = getLitCond(headLit);
//            if(!cond.realLit.equals(supLit)){
//                supCond.support.put(cond.realLit,TRUE^cond.cond);
//                supConds.get(cond.realLit).add(supCond);
//            }
//        });
//        List<String> body = new ArrayList<>(r.getPositiveBody());
//        body.addAll(r.getNegativeBody());
//        body.forEach(bodyLit->{
//            LitCond cond = getLitCond(bodyLit);
//            supCond.support.put(cond.realLit,cond.cond);
//            supConds.get(cond.realLit).add(supCond);
//        });
//        supConds = supConds.
//    }

    //表达式比较，需要完善
    private boolean equation(String str){
        if(str.contains("!=")){
            String[] tocomp = str.split("!=");
            if(tocomp[0].replaceAll(" ","").equals(tocomp[1].replaceAll(" ",""))){
                atomRestrict.put(str,4);
            }else{
                atomRestrict.put(str,2);
            }
            return true;
        }
        return false;
    }

    private LitCond getLitCond(String literal){
        String realLit = literal.trim();
        //0:弱否定，1：强否定
        LinkedList<Integer> neg = new LinkedList<>();
        int status = 2;
        //not* -? lit
        while(realLit.startsWith("not")||realLit.startsWith("-")){
            if(realLit.startsWith("not")){
                neg.push(0);
                realLit = realLit.substring(3).trim();
            }else if(realLit.startsWith("-")){
                neg.push(1);
                realLit = realLit.substring(1).trim();
            }
        }
        while(neg.size()>0){
            int next = neg.pop();
            if(next==0){
                status = (TRUE^status);
            }else{
                status=1;
            }
        }
        return new LitCond(realLit,status);
    }

    HeuristicAugmentedSubset(){
    }

    @Override
    public HeuristicAugmentedSubset clone(){
        //long start = System.currentTimeMillis();
        //System.out.println("clone start:"+start);
        //System.out.println("18"+System.currentTimeMillis());
        HeuristicAugmentedSubset cloned = new HeuristicAugmentedSubset();
        cloned.setLpmlnProgram(lpmlnProgram);
        cloned.satIdx = (HashSet)((HashSet)satIdx).clone();
        cloned.unsatIdx = (HashSet)((HashSet)unsatIdx).clone();
        cloned.unknownIdx = (HashSet)((HashSet)unknownIdx).clone();
        cloned.atomRestrict = (HashMap)((HashMap)atomRestrict).clone();
        cloned.satRestricts = satRestricts;
        //System.out.println("17"+System.currentTimeMillis());
        cloned.unsatRestricts = unsatRestricts;
        //不确定深复制对效率的影响
        cloned.activeRuleRestrict = activeRuleRestrict;
        cloned.weight = weight;
        cloned.loops = loops;
        cloned.supConds = supConds;
        cloned.supLoop = supLoop;
        cloned.litToLoop = litToLoop;
        cloned.truthSatIdx = truthSatIdx;
        cloned.truthRes = truthRes;
        cloned.loopSupports = loopSupports;
        cloned.failedConds = (HashSet)((HashSet)failedConds).clone();
        //System.out.println("clone start:"+(System.currentTimeMillis()-start));
        return cloned;
    }

    /**
     * 根据单原子事实或者约束对原子进行约束
     * @param idx   规则下标
     * @return sat结果
     */
    @Override
    public boolean sat(int idx){
        boolean result = satable(idx)&super.sat(idx);
        refreshWeight();
        return result;
    }

    /**
     * 根据规则unsat对原子进行约束，body and ！head
     * @param idx   规则下标g
     * @return sat结果
     */
    @Override
    public boolean unsat(int idx){
        boolean result = unsatable(idx)&super.unsat(idx);
        refreshWeight();
        return result;
    }

    public void refreshWeight(){
        weight=0;
        for (Map.Entry<String,Integer> ent: atomRestrict.entrySet()) {
            if(ent.getValue()!=TRUE){
                weight += (1-(getCount(ent.getValue())/3));
                //System.out.println(ent.getKey()+"weight:"+(1-(getCount(ent.getValue())/3))+"value:"+ent.getValue());
            }
            //weight += 1-(getCount(ent.getValue())/3)*;
        }
    }


    private static final Integer[] COUNT = {0,1,1,2,1,2,2,3};
    private double getCount(int n){
        return COUNT[n];
    }

    /**
     * 规则放到sat中后有回答集
     * @param idx 规则下标
     * @return true=有
     */
    private boolean satable(int idx){
        SATRestrict satRestrict = satRestricts[idx];
        Map<String,Integer> toRestrict = new HashMap<>();
        for (Map.Entry<String,Integer> ent : satRestrict.restrict.entrySet()) {
            int ori = atomRestrict.getOrDefault(ent.getKey(),TRUE-2);
            int nextStatus = ori&ent.getValue();
            if(nextStatus == ori){
                //规则已经被满足
                return true;
            }
            if(nextStatus!=0){
                toRestrict.put(ent.getKey(),nextStatus);
            }
        }
        if(toRestrict.size()>1){
            toRestrict.forEach((lit,stat)->{
                if(activeRuleRestrict.containsKey(lit)){
                    activeRuleRestrict.get(lit).add(idx);
                }else{
                    Set<Integer> ruleIdxs = new HashSet<>();
                    ruleIdxs.add(idx);
                    activeRuleRestrict.put(lit,ruleIdxs);
                }
            });
            return true;
        }else if(toRestrict.size()==0){
            //合取无法被满足
            return false;
        }else{
            return restrictLit(toRestrict.keySet().iterator().next(),toRestrict.values().iterator().next());
        }
    }

    /**
     * 约束一个lit到某个状态，可能应发一系列变化
     * @param lit
     * @param status
     * @return
     */
    private boolean restrictLit(String lit,int status){
        LinkedList<LitCond> conds = new LinkedList<>();
        conds.offer(new LitCond(lit,status));
        while(conds.size()>0){
            LitCond nextCond = conds.poll();
            //根据atomRestrict判断
            int ori = atomRestrict.getOrDefault(nextCond.realLit,TRUE);
            int now = ori&nextCond.cond;
            if(satisfied(nextCond)){
                continue;
            }
            if(!satisfiable(nextCond)){
                return false;
            }
            atomRestrict.put(nextCond.realLit,now);
            //判断外部支持规则能否支持
            restrictInLoop(nextCond).forEach(conds::offer);
            //根据sat判断
            Set<Integer> ruleIdxs = activeRuleRestrict.getOrDefault(nextCond.realLit,new HashSet<>());
            for (int idx : ruleIdxs) {
                SATRestrict satRestrict = satRestricts[idx];
                int resStat = 0;
                try{
                    resStat = satRestrict.restrict.get(nextCond.realLit);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //如果加限制有影响，即新导致规则中有原子不被满足,即规则对应的析取表达式可能产出新的atomres
                if((resStat&ori)!=0
                        &&((resStat&now)==0)){
                    Map<String,Integer> currentCond = new HashMap<>();
                    satRestrict.restrict.forEach((k,v)->{
                        int stat = v&atomRestrict.get(k);
                        if(stat!=0){
                            currentCond.put(k,stat);
                        }
                    });
                    if(currentCond.size()==1){
                        currentCond.forEach((k,v)->conds.offer(new LitCond(k,v)));
                    }
                }
            }
        }
        return true;
    }

    private List<LitCond> restrictInLoop(LitCond start){
        List<LitCond> ans = new ArrayList<>();
        supConds.getOrDefault(start.realLit,new HashSet<>()).forEach(cond->{
            if((cond.support.get(start.realLit)&start.cond)==0&&supLoop.containsKey(cond)){
                failedConds.add(cond);
                Loop unsupLoop = supLoop.get(cond);
                Set<SupportCond> supportConds = loopSupports.get(unsupLoop);
                boolean supported = false;
                for (SupportCond sup : supportConds) {
                    if(!failedConds.contains(sup)){
                        supported = true;
                        break;
                    }
                }
                if(!supported){
                    unsupLoop.literal.forEach(lit->{
                        ans.add(new LitCond(lit,4));
                    });
                }
            }
        });
        //System.out.println("loopinfo:"+ans.size());
        return ans;
    }

    private boolean loopAlive(Loop loop){
        for (Map.Entry<SupportCond,Loop> condToLoop : supLoop.entrySet()) {
            if(condToLoop.getValue().equals(loop)){
                SupportCond cond = condToLoop.getKey();
                boolean condAlive = true;
                for (Map.Entry<String,Integer> supCond : cond.support.entrySet()) {
                    if(!satisfiable(new LitCond(supCond.getKey(),supCond.getValue()))){
                        condAlive = false;
                        break;
                    }
                }
                if(condAlive){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 规则放到unsat后有回答集
     * @param idx 规则下标
     * @return true=有
     */
    private boolean unsatable(int idx){
        long start = System.currentTimeMillis();
        Map<String,Integer> unsatMap = unsatRestricts[idx];
        for (Map.Entry<String,Integer> ent : unsatMap.entrySet()) {
            if(!restrictLit(ent.getKey(),ent.getValue())){
                return false;
            }
        }
        return true;
    }

    public Set<Integer> getEnumerable() {
        return enumerable;
    }

    public void setEnumerable(Set<Integer> enumerable) {
        this.enumerable = enumerable;
    }

    public void setUnenumerable(Integer unenumerable){
        this.enumerable.remove(unenumerable);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Set<Integer> getSatIdx() {
        Set<Integer> result = new HashSet<>(satIdx);
        result.addAll(truthSatIdx);
        return result;
    }

    private static class SATRestrict {
        //true:restrict should be considered
        Map<String,Integer> restrict;
        SATRestrict(){
            restrict = new HashMap<>();
        }

        @Override
        public SATRestrict clone(){
            SATRestrict cloned = new SATRestrict();
            cloned.restrict = new HashMap<>(this.restrict);
            return cloned;
        }
    }

    private static class LitCond{
        String realLit;
        Integer cond;
        LitCond(String realLit, Integer cond){
            this.realLit = realLit;
            this.cond = cond;
        }
    }

    private static class SupportCond{
        Map<String,Integer> support;
        SupportCond(){
            support = new HashMap<>();
        }
    }

    private static class Loop{
        Set<String> literal;
        int support;
        Loop(Set<String> literal){
            this.literal = literal;
            support = 0;
        }
    }
}
