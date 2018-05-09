package cn.edu.seu.kse.lpmln.model;

import java.util.*;

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
    private HashMap<String,Integer> atomRestrict;
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
    private HashMap<String,Set<Integer>> activeRuleRestrict;
    /**
     * lit所有状态都可行
     */
    private static final int TRUE=7;
    private double weight;
    private List<String> allLiterals;
    private List<Loop> loops;
    /**
     * 文字到失败条件映射，满足失败条件会导致规则体部无法被满足，外部支持规则无法支持
     */
    private Map<String,Set<FailCond>> supConds;
    /**
     * 可能成立的外部支持规则,到loop的映射
     */
    private Map<Rule,Loop> supLoop = new HashMap<>();

    public HeuristicAugmentedSubset(LpmlnProgram lpmlnProgram) {
        super(lpmlnProgram);
        enumerable = new HashSet<>(unknownIdx);
        atomRestrict = new HashMap<>();
        satRestricts = new SATRestrict[lpmlnProgram.getRules().size()];
        unsatRestricts = new HashMap[lpmlnProgram.getRules().size()];
        activeRuleRestrict = new HashMap<>();
        loops = new ArrayList<>();
        supLoop = new HashMap<>();
        List<Rule> rules = lpmlnProgram.getRules();
        allLiterals = new ArrayList<>();
        lpmlnProgram.getRules().forEach(r->{
            r.getHead().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
            r.getPositiveBody().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
            r.getNegativeBody().forEach(lit->allLiterals.add(getLitCond(lit).realLit));
        });
        allLiterals = new ArrayList<>(new HashSet<>(allLiterals));
        supConds = new HashMap<>();
        allLiterals.forEach(lit->{
            supConds.put(lit,new HashSet<>());
        });
        for(int i=0;i<rules.size();i++){
            getRuleCond(rules.get(i),i);
        }
        for(int i=0;i<rules.size();i++){
            if(!unknownIdx.contains(i)){
                unknownIdx.add(i);
                sat(i);
            }
        }
        System.out.println("init done");
    }

//    private void findLoops(){
//        Map<String,Set<String>>
//    }

    public int getRuleIdx(){
        int ans=-1;
        double eval=0;
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            if(unknownIdx.contains(i)){
                double nextEval;
                HeuristicAugmentedSubset positive = this.clone();
                HeuristicAugmentedSubset negative = this.clone();
                if(positive.sat(i)&&negative.unsat(i)){
                    nextEval = positive.weight+negative.weight-Math.abs(positive.weight-negative.weight);
                    System.out.println(""+positive.weight+"\t"+negative.weight);
                    if(nextEval>eval){
                        ans = i;
                        eval = nextEval;
                    }
                }
            }

        }
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
            equation(headLit);
            LitCond cond = getLitCond(headLit);
            satRestrict.restrict.put(cond.realLit,cond.cond|satRestrict.restrict.getOrDefault(cond.realLit,0));
            unsatRestrict.put(cond.realLit,(TRUE^cond.cond)&unsatRestrict.getOrDefault(cond.realLit,TRUE));
        });
        //TODO:这里暂时没考虑析取
        body.forEach(bodyLit->{
            equation(bodyLit);
            LitCond cond = getLitCond(bodyLit);
            satRestrict.restrict.put(cond.realLit,(TRUE^cond.cond)|satRestrict.restrict.getOrDefault(cond.realLit,0));
            unsatRestrict.put(cond.realLit,cond.cond&unsatRestrict.getOrDefault(cond.realLit,TRUE));
            supConds.get(cond.realLit).add(new FailCond(cond.cond,idx));
        });
    }

    //表达式比较，需要完善
    private void equation(String str){
        if(str.contains("!=")){
            String[] tocomp = str.split("!=");
            if(tocomp[0].replaceAll(" ","").equals(tocomp[1].replaceAll(" ",""))){
                atomRestrict.put(str,5);
            }else{
                atomRestrict.put(str,2);
            }
        }
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
        HeuristicAugmentedSubset cloned = new HeuristicAugmentedSubset();
        cloned.setLpmlnProgram(lpmlnProgram);
        cloned.satIdx = new HashSet<>(satIdx);
        cloned.unsatIdx = new HashSet<>(unsatIdx);
        cloned.unknownIdx = new HashSet<>(unknownIdx);
        cloned.atomRestrict = new HashMap<>(atomRestrict);
        cloned.enumerable = new HashSet<>(enumerable);
        cloned.satRestricts = new SATRestrict[satRestricts.length];
        for(int i=0;i<satRestricts.length;i++){
            cloned.satRestricts[i] = satRestricts[i].clone();
            cloned.satRestricts[i].status = satRestricts[i].status;
        }
        cloned.unsatRestricts = unsatRestricts;
        //不确定深复制对效率的影响
        cloned.activeRuleRestrict = activeRuleRestrict;
        cloned.weight = weight;
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
        satRestrict.status = true;
        Map<String,Integer> toRestrict = new HashMap<>();
        for (Map.Entry<String,Integer> ent : satRestrict.restrict.entrySet()) {
            int ori = atomRestrict.getOrDefault(ent.getKey(),TRUE-2);
            int nextStatus = ori&ent.getValue();
            if(nextStatus == ori){
                //规则已经被满足
                satRestrict.status = false;
                return true;
            }
            if(nextStatus!=0){
                toRestrict.put(ent.getKey(),nextStatus);
            }
        }
        if(toRestrict.size()>1){
            satRestrict.restrict.clear();
            toRestrict.forEach((lit,stat)->{
                satRestrict.restrict.put(lit,stat);
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
            satRestrict.status = false;
            return restrictLit(toRestrict.keySet().iterator().next(),toRestrict.values().iterator().next());
        }
    }

    //这个函数需要优化一下

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
            //判断外部支持规则能否支持
            supConds.get(nextCond.realLit).forEach(supCond -> {
                if((supCond.stat&nextCond.cond)==0){

                }
            });
            //根据unsat判断
            int ori = atomRestrict.getOrDefault(nextCond.realLit,TRUE);
            if((ori&nextCond.cond)==0){
                return false;
            }else{
                atomRestrict.put(nextCond.realLit,ori&nextCond.cond);
            }
            //根据sat判断
            Set<Integer> ruleIdxs = activeRuleRestrict.getOrDefault(nextCond.realLit,new HashSet<>());
            for (int idx : ruleIdxs) {
                SATRestrict satRestrict = satRestricts[idx];
                //规则被激活
                if(satRestrict.status){
                    //规则中这个lit还可能被满足
                    if(satRestrict.restrict.containsKey(nextCond.realLit)){
                        ori = satRestrict.restrict.get(nextCond.realLit);
                        if((ori&nextCond.cond)==0){
                            //合取式中无法被满足
                            satRestrict.restrict.remove(nextCond.realLit);
                            if(satRestrict.restrict.size()==1){
                                //合取式中仅剩一个条件可被满足
                                conds.offer(new LitCond(satRestrict.restrict.keySet().iterator().next(),satRestrict.restrict.values().iterator().next()));
                                satRestrict.status = false;
                            }
                        }else{
                            if(((ori&nextCond.cond)|atomRestrict.get(nextCond.realLit))==(ori&nextCond.cond)){
                                satRestrict.status = false;
                            }else{
                                //加上约束后规则中这个lit还可能被满足
                                satRestrict.restrict.put(nextCond.realLit,ori);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 规则放到unsat后有回答集
     * @param idx 规则下标
     * @return true=有
     */
    private boolean unsatable(int idx){
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

    private static class SATRestrict {
        //true:restrict should be considered
        boolean status;
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

    private static class FailCond{
        int stat;
        int ruleIdx;
        FailCond(int stat,int ruleIdx){
            this.stat = stat;
            this.ruleIdx = ruleIdx;
        }
    }

    private static class Loop{
        Set<Integer> literalIdx;
        int support;
        Loop(){
            literalIdx = new HashSet<>();
            support = 0;
        }
    }
}
