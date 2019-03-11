package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.exception.solveexception.AssignConflictException;
import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2019/3/8
 * 基于nogood思想的构造，HeuristicAugmentedSubset的进阶版
 * 方便起见有的函数输入直接从成员变量读取
 */
public class NogoodAugmentedSubset extends AugmentedSubset{
    public static final String NOT = "not ";
    public static final String NEGATION = "-";
    public static final String VB = "VB_";

    /**
     * lit到nogood的映射，在assign以及propagate要用
     */
    private Map<String,List<Integer>> litToNogood = new HashMap<>();

    private List<Nogood> nogoods;

    /**
     * 应该是list，k为literal，v是not与否，不出现说明还没赋值
     */
    private Map<String,Boolean> assignment = new HashMap<>();

    /**
     * sat某条规则需要添加的nogood，下标与lpmlnProgram.rules中对应
     */
    private List<Nogood> satNogoods = new ArrayList<>();

    /**
     * unsat某条规则时需要assign的kv对，下标与lpmlnProgram.rules中对应
     */
    private List<Map<String,Boolean>> unsatAssign = new ArrayList<>();

    /**
     * 文字到satNogoods中的nogood下标的映射
     */
    private Map<String,List<Integer>> litToSatCond = new HashMap<>();

    /**
     * 文字集合,lit_{\Pi}
     */
    private Set<String> literals = new HashSet<>();

    /**
     * 专门记录VB_值，预留
     */
    private List<Boolean> bodyAssignment;

    /**
     * 表示是否在尝试拆分
     */
    private boolean trySplitting = false;

    /**
     * 恢复时的操作栈，记录操作的反向
     * key:String
     *  语法：target operation...
     *  target : litToNogood|nogoods|assignment|satisfiability|satNogoods
     *  operation:
     *      litToNogood: (remove literal idx)|(add literal idx val)
     *      nogoods: remove|set idx
     *      assignment: remove literal
     *      satisfiability: undosat|undounsat idx
     *      satNogoods: set idx
     * value:nogood
     */
    private LinkedList<Pair<String,Nogood>> recoverStack = new LinkedList();

    /**
     * 仅供clone用
     */
    private NogoodAugmentedSubset(){

    }

    @Override
    public NogoodAugmentedSubset clone(){
        NogoodAugmentedSubset clone = new NogoodAugmentedSubset();

        //不共享部分
        //lit到nogood的映射，需要维护nogood中的元素数量
//        long last = System.currentTimeMillis();
//        long all = System.currentTimeMillis();
        Map<String,List<Integer>> litToNogoodClone = (Map<String, List<Integer>>) ((HashMap)litToNogood).clone();
        litToNogood.forEach((k,v)->{
            litToNogoodClone.put(k,(ArrayList<Integer>)((ArrayList<Integer>)v).clone());
        });
        clone.litToNogood = litToNogoodClone;
        clone.nogoods = new ArrayList<>(nogoods.size());
        nogoods.forEach(nogood -> {clone.nogoods.add(nogood.clone());});
//        System.out.println("point1: "+(System.currentTimeMillis()-last));
//        last = System.currentTimeMillis();

        //assign
        clone.assignment = (Map<String, Boolean>) ((HashMap)assignment).clone();
//        System.out.println("point2: "+(System.currentTimeMillis()-last));
//        last = System.currentTimeMillis();

        //添加时clone，所以可以共享
        List<Nogood> satNogoodsClone = new ArrayList<>();
        satNogoods.forEach(nogood -> satNogoodsClone.add(nogood.clone()));
        clone.satNogoods = satNogoodsClone;
//        System.out.println("point3: "+(System.currentTimeMillis()-last));
//        last = System.currentTimeMillis();

        //共享部分
        //规则unsat需要添加的signed-literal，相对程序固定
        clone.unsatAssign = unsatAssign;
        //lit_{\Pi}不会变化
        clone.literals = literals;
        clone.litToSatCond = litToSatCond;

        //super clone
        clone.satIdx = new HashSet<>(satIdx);
        clone.unsatIdx = new HashSet<>(unsatIdx);
        clone.unknownIdx = new HashSet<>(unknownIdx);
        clone.lpmlnProgram = lpmlnProgram;

//        System.out.println("point4: "+(System.currentTimeMillis()-last));
//        last = System.currentTimeMillis();

//        if((System.currentTimeMillis()-all)>3000){
//            System.out.println(123);
//        }
//        System.out.println("point4: "+(System.currentTimeMillis()-all));
//        all = System.currentTimeMillis();


        return clone;
    }

    private static final String REMOVE = "remove";
    private static final String ADD = "add";
    private static final String SET = "set";
    /**
     * 恢复
     * key:String
     *  语法：target operation...
     *  target : litToNogood|nogoods|assignment|satisfiability|satNogoods
     *  operation:
     *      litToNogood: (remove(1) literal(2) idx(3))|(add(1) litera(2) idx(3) val(4))
     *      nogoods: remove(1) idx(2)
     *      assignment: remove(1)
     *      satisfiability: undosat(1)|undounsat(1) idx(2)
     *      satNogoods: set(1)
     * value:nogood
     * @return t:成功 f:操作未记录，失败
     */
    private boolean recover(){
        if(trySplitting){
            trySplitting = false;

            while(recoverStack.size()>0){
                Pair<String,Nogood> f = recoverStack.pop();
                String[] params = f.getKey().split(" ");
                switch (params[0]){
                    case "litToNogood":
                        if(REMOVE.equals(params[1])){
                            litToNogood.get(params[2]).remove((int)Integer.valueOf(params[3]));
                        }else if (ADD.equals(params[2])){
                            litToNogood.get(params[2]).add(Integer.valueOf(params[3]),Integer.valueOf(params[4]));
                        }else{
                            throw new SolveException("recover pattern unrecognized: "+f.getKey());
                        }
                        break;
                    case "nogoods":
                        if(REMOVE.equals(params[1])){
                            nogoods.remove((int)Integer.valueOf(params[2]));
                        }else if(SET.equals(params[1])){
                            nogoods.set(Integer.valueOf(params[2]),f.getValue());
                        }else{
                            throw new SolveException("recover pattern unrecognized: "+f.getKey());
                        }
                        break;
                    case "assignment":
                        if(REMOVE.equals(params[1])){
                            assignment.remove(params[2]);
                        }else{
                            throw new SolveException("recover pattern unrecognized: "+f.getKey());
                        }
                        break;
                    case "satisfiability":
                        int aim = Integer.valueOf(params[2]);
                        if("undosat".equals(params[1])){
                            satIdx.remove(aim);
                            unknownIdx.add(aim);
                        }else if("undounsat".equals(params[1])){
                            unsatIdx.remove(aim);
                            unknownIdx.add(aim);
                        }else{
                            throw new SolveException("recover pattern unrecognized: "+f.getKey());
                        }
                        break;
                    case "satNogoods":
                        if(SET.equals(params[1])){
                            satNogoods.set(Integer.valueOf(params[2]),f.getValue());
                        }else{
                            throw new SolveException("recover pattern unrecognized: "+f.getKey());
                        }
                        break;
                    default:
                        throw new SolveException("recover pattern unrecognized: "+f.getKey());
                }
            }

            return true;
        }else{
            return false;
        }
    }

    public Pair<NogoodAugmentedSubset,NogoodAugmentedSubset> split(){
        //返回值选择优先级，选择最低的
        Comparator<Pair<Integer,Integer>> comparator = (o1, o2) -> {
            return o1.getValue()-o2.getValue();
        };
        PriorityQueue<Pair<Integer,Integer>> queue = new PriorityQueue<>(comparator);
        NogoodAugmentedSubset toSat = this.clone();
        NogoodAugmentedSubset toUnsat = this.clone();

//        long t1 = System.currentTimeMillis();
        for (int idx : unknownIdx) {
//            long t2 = System.currentTimeMillis();
            try{
                toSat.setTrySplitting(true);
                toSat.sat(idx);

//                System.out.println("2-1:"+(System.currentTimeMillis()-t2));
//                t2 = System.currentTimeMillis();

                toUnsat.setTrySplitting(true);
                toUnsat.unsat(idx);

//                System.out.println("2-2:"+(System.currentTimeMillis()-t2));
//                t2 = System.currentTimeMillis();

//                AugmentedSubsetSolver solver1 = new AugmentedSubsetSolver(toSat);
//                AugmentedSubsetSolver solver2 = new AugmentedSubsetSolver(toUnsat);
//                if(solver1.solve().size()==0){
//                    System.out.println(111);
//                }
//
//                if(solver2.solve().size()==0){
//                    System.out.println(222);
//                }

                toSat.recover();
                toUnsat.recover();

//                System.out.println("2-3:"+(System.currentTimeMillis()-t2));
//                t2 = System.currentTimeMillis();

                queue.offer(new Pair<>(idx,getEvaluation(toSat,toUnsat)));
            }catch (AssignConflictException e){
                if(LPMLNApp.isDebugging()){
                    System.out.println("detect conflict, abandon this case");
                }
                //懒得优化了先这么写
                toSat = this.clone();
                toUnsat = this.clone();
            }
//            System.out.println("2:"+(System.currentTimeMillis()-t2));
        }
//        System.out.println("2:"+(System.currentTimeMillis()-t1));

        if(queue.size()==0){
            return null;
        }

        int rIdx = queue.peek().getKey();
        try{
            toSat.sat(rIdx);
            toUnsat.unsat(rIdx);
        }catch (Exception e){
            e.printStackTrace();
        }


        //queue为空则返回null，表示没有规则以供划分
        return new Pair<>(toSat,toUnsat);
    }

    /**
     * 返回值选择优先级，选择最低的
     * @param sat sat
     * @param unsat unsat
     * @return 优化程度
     */
    private int getEvaluation(NogoodAugmentedSubset sat, NogoodAugmentedSubset unsat){
        //在这修改选择策略
//        return -(sat.getAssignmentSize()+unsat.getAssignmentSize());
        return Math.abs(sat.getAssignmentSize()-unsat.getAssignmentSize());
    }

    public NogoodAugmentedSubset(LpmlnProgram lpmlnProgram){
        super(lpmlnProgram);

        init();

        constructNogood();

        Map<String,Boolean> toAssign = constructNogoodMap(litToNogood);

        toAssign.forEach(this::assign);

        if(LPMLNApp.semantics.equals(LPMLNApp.SEMANTICS_WEAK)){
            unknownIdx.addAll(satIdx);
            Set<Integer> toCheck = satIdx;
            satIdx = new HashSet<>();
            toCheck.forEach(this::sat);
        }

        System.out.println();
    }

    private void init(){
        bodyAssignment = new ArrayList<>(lpmlnProgram.getRules().size());

        getLiterals();

        literals.forEach(lit->litToSatCond.put(lit,new ArrayList<>()));
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            litToSatCond.put(getBodyPred(i),new ArrayList<>());
        }

        lpmlnProgram.getRules().forEach(r->{
            Map<String,Boolean> curUnsat = new HashMap<>();
            r.getHead().forEach(h->curUnsat.put(h,false));
            r.getPositiveBody().forEach(pb->curUnsat.put(pb,true));
            r.getNegativeBody().forEach(nb->curUnsat.put(nb.substring(NOT.length()),false));
            unsatAssign.add(curUnsat);
        });
    }

    @Override
    public boolean sat(int idx){
        boolean res = super.sat(idx);
        if(!res){
            return false;
        }

        //impl3
        Nogood toadd = satNogoods.get(idx);

//        if(trySplitting){
//            recoverStack.push(new Pair<>("satNogoods set "+idx,toadd.clone()));
//        }
//        toadd.adapt(assignment);

        if(toadd.getLiteralSet().size()==1){
            //对应nogood已经可以产出result-unit
            toadd.getSignedLiterals().forEach((k,v)->assign(k,!v));
        }else{
            //将nogood添加到map中，size==0的时候不添加
            toadd.getLiteralSet().forEach(lit->{
                if(trySplitting){
                    recoverStack.push(new Pair<>("litToNogood remove "+lit+" "+litToNogood.get(lit).size(),null));
                }
                litToNogood.get(lit).add(nogoods.size());
            });
            if(toadd.getLiteralSet().size()!=0){
                if(trySplitting){
                    recoverStack.push(new Pair<>("nogoods remove "+nogoods.size(),null));
                }
                nogoods.add(toadd);
            }
        }


        if(trySplitting){
            recoverStack.push(new Pair<>("satisfiability undosat "+idx,null));
        }
        return res;
    }



    @Override
    public boolean unsat(int idx){
        boolean res = super.unsat(idx);
        if(!res){
            return false;
        }

        unsatAssign.get(idx).forEach(this::assign);

        if(trySplitting){
            recoverStack.push(new Pair<>("satisfiability undounsat "+idx,null));
        }

        return res;
    }

    /**
     * 给literal赋值，需要propagation
     * @param literal literal
     * @param sign t：exist f：not
     */
    private void assign(String literal,boolean sign){
        Boolean assigned = assignment.get(literal);
        if(assigned!=null) {
            if (assigned != sign) {
                throw new AssignConflictException("literal assign conflict");
            } else {
                return;
            }
        }

        if(trySplitting){
            recoverStack.push(new Pair<>("assignment remove "+literal,null));
        }
        assignment.put(literal,sign);

        checkSatisfiability(literal,sign);

        List<Integer> nogoodList = litToNogood.get(literal);

        for(int i=0;i<nogoodList.size();i++){
            Nogood cur = nogoods.get(nogoodList.get(i));
//            if(cur.isClear()){
//                if(trySplitting){
//                    recoverStack.push(new Pair<>("litToNogood add "+literal+" "+i+" "+nogoodList.get(i),cur.clone()));
//                }
//                nogoodList.remove(i);
//                continue;
//            }

            if(trySplitting){
                recoverStack.push(new Pair<>("nogoods set "+nogoodList.get(i),cur.clone()));
            }
//            SignedLiteral result = null;
//            try {
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }

            SignedLiteral result = cur.assign(literal,sign);

            //propagation
            if(result==null){
//                //nogood un-violable
//                if(trySplitting){
//                    recoverStack.push(new Pair<>("litToNogood add "+literal+" "+i+" "+nogoodList.get(i),cur.clone()));
//                }
//                nogoodList.remove(i);
            }else if(!"".equals(result.literal)){
                //result-unit
                assign(result.literal,!result.sign);
            }
        }
    }

    private void checkSatisfiability(String literal,boolean sign){
        if(!litToSatCond.containsKey(literal)){
            System.out.println("err");
        }
        List<Integer> satIdx =  litToSatCond.get(literal);
        if(satIdx==null){
            System.out.println("err");
        }
        satIdx.forEach(idx->{
            Nogood satNogood = satNogoods.get(idx);
            if(trySplitting){
                recoverStack.push(new Pair<>("satNogoods set "+idx,satNogood.clone()));
            }
            SignedLiteral sLit = satNogood.assign(literal,sign);
            if(sLit==null){
                //satNogood已经无法被违反
                boolean res = super.sat(idx);
                if(trySplitting && res){
                    recoverStack.push(new Pair<>("satisfiability undosat "+idx,null));
                }
            }else if(satNogood.getLiteralSet().size()==0){
                //satNogood已经被违反
                boolean res = super.unsat(idx);
                if(trySplitting && res){
                    recoverStack.push(new Pair<>("satisfiability undounsat "+idx,null));
                }
            }
        });
    }

    /**
     * 根据nogood列表创建lit到nogood的映射
     * 比如nogood:{Ta,Fb}则a,b都指向它
     * 返回直接的result-unit比如{Ta}
     * @param map map
     * @return result-units
     */
    private Map<String,Boolean> constructNogoodMap(Map<String,List<Integer>> map){
        //litToNogood
        Map<String,Boolean> toAssign = new HashMap<>();
        literals.forEach(lit->map.put(lit,new ArrayList<>()));
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            map.put(getBodyPred(i),new ArrayList<>());
        }

        for(int i=0;i<nogoods.size();i++){
            Nogood nogood = nogoods.get(i);
            Set<String> litSet = nogood.getLiteralSet();
            if(litSet.size()==1){
                nogood.getSignedLiterals().forEach((k,v)->toAssign.put(k,!v));
            }else{
                for (String lit : litSet) {
                    map.getOrDefault(lit,new ArrayList<>()).add(i);
                }
            }
        }
        return toAssign;
    }

    private void getLiterals(){
        //先获取所有文字
        lpmlnProgram.getRules().forEach(rule -> {
            //只出现在体部的文字在实例化的时候就挂了
            rule.getHead().forEach(headStr->{
                if(headStr.startsWith(NOT)){
                    headStr = headStr.substring(NOT.length());
                }
                literals.add(headStr);
            });
        });
    }

    private void constructNogood(){
        nogoods = new ArrayList<>();

        //解释一致性产出的nogood
        literals.forEach(literal->{
            if(literal.startsWith(NEGATION) &&
                    literals.contains(literal.substring(NEGATION.length()))){
                Nogood toadd = new Nogood();
                toadd.add(literal,true);
                toadd.add(literal.substring(NEGATION.length()),true);
                nogoods.add(toadd);
            }
        });

        //body-oriented nogood
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule r = lpmlnProgram.getRules().get(i);
            //imp1: VB->a1,a2,...,not am+1,not am+2,...
            //体部成立表示体部项都成立
            for (String pb : r.getPositiveBody()) {
                Nogood impl1 = new Nogood();
                impl1.add(getBodyPred(i),true);
                impl1.add(pb,false);
                nogoods.add(impl1);
            }
            for (String nb : r.getNegativeBody()) {
                Nogood impl1 = new Nogood();
                impl1.add(getBodyPred(i),true);
                impl1.add(nb.substring(NOT.length()),true);
                nogoods.add(impl1);
            }
            //imp2: a1,a2,...,not am+1,not am+2,...->b
            //体部项都成立表示体部成立
            Nogood impl2 = new Nogood();
            impl2.add(getBodyPred(i),false);
            r.getPositiveBody().forEach(pb-> impl2.add(pb,true));
            r.getNegativeBody().forEach(nb-> impl2.add(nb.substring(NOT.length()),false));
            nogoods.add(impl2);
        }

        //atom-oriented nogood
        //impl3: vb1 V vb2 V ... ->a
        //体部成立表示头部成立，头部析取
        //规则成立性，SAT集合中才有此性质
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule r = lpmlnProgram.getRules().get(i);
            Nogood impl3 = new Nogood();
            r.getHead().forEach(h->{
                impl3.add(h,false);
                litToSatCond.get(h).add(satNogoods.size());
            });
            impl3.add(getBodyPred(i),true);
            litToSatCond.get(getBodyPred(i)).add(satNogoods.size());
            satNogoods.add(impl3);
        }
        //impl4: a-> vb1 V vb2 V ...
        //原子支持，loop nogood的特殊情形(|loop|=1)
        Map<String,Nogood> supNogoodMap = new HashMap<>();
        literals.forEach(literal->supNogoodMap.put(literal,new Nogood()));
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule r = lpmlnProgram.getRules().get(i);
            for (String h : r.getHead()) {
                supNogoodMap.get(h).add(getBodyPred(i),false);
            }
        }
        //加上Ta
        supNogoodMap.forEach((k,v)->{
            v.add(k,true);
            nogoods.add(v);
        });

        //loop nogood指数级别，先不添加

//        if(LPMLNApp.isDebugging()){
//            System.out.println("nogood done");
//        }
    }

    private String getBodyPred(int i){
        return VB+i;
    }

    public int getAssignmentSize(){
        return assignment.size();
    }

    /**
     * 检查nogood异常，调试用
     */
    private void checkNogoods(){
        Set<Nogood> all = new HashSet<>();
        all.addAll(nogoods);
        all.addAll(satNogoods);
        all.forEach(nogood -> {
            nogood.getLiteralSet().forEach(lit->{
                if(lit.startsWith(NOT)){
                    throw new SolveException("nogood contain not: "+nogood.toString());
                }
            });
        });
    }

    public void setTrySplitting(boolean trySplitting) {
        this.trySplitting = trySplitting;
    }
}


class Nogood {
    /**
     * 原本应该是set，这里把signedLiteral写一起了
     */
    private Map<String,Boolean> signedLiterals = new HashMap<>();
    private boolean clear = false;

    Nogood(){

    }

    @Override
    protected Nogood clone(){
        Nogood clone = new Nogood();
        clone.signedLiterals = (HashMap<String,Boolean>)((HashMap<String,Boolean>)signedLiterals).clone();
        clone.clear = clear;
        return clone;
    }

    Nogood(Map<String,Boolean> signedLiteral){
        this.signedLiterals = signedLiteral;
    }

    void add(String literal,boolean sign){
        signedLiterals.put(literal,sign);
    }

    Set<String> getLiteralSet(){
        return signedLiterals.keySet();
    }

    @Override
    public String toString(){
        return signedLiterals.toString();
    }

    public Map<String, Boolean> getSignedLiterals() {
        return signedLiterals;
    }

    /**
     * 分配一个文字
     * @param literal literal
     * @param sign sign
     * @return 返回result-unit,返回null表示nogood已经可以移除,返回literal为""表示继续
     */
    SignedLiteral assign(String literal,boolean sign){
        boolean cur = signedLiterals.get(literal);
        if(sign==cur){
            signedLiterals.remove(literal);
            if(signedLiterals.size()==1){
                Map.Entry<String,Boolean> entry = signedLiterals.entrySet().iterator().next();
                return new SignedLiteral(entry.getKey(), entry.getValue());
            }else{
                //暂时无法产出结果
                return new SignedLiteral("",true);
            }
        }else{
            //nogood已经有节点违反，不用再考虑
            this.clear = true;
            return null;
        }
    }

    /**
     * 根据当前assignment调整nogood，避免冲突，也避免已经满足的nogood
     * 需要在此nogood已经违反的时候直接添加到unsat集合中，或者已经满足的时候放到sat中
     * 这里不考虑这种情况
     * TODO:考虑弱语义下强规则发生冲突，暂不考虑
     * @param assignment assignment
     */
    void adapt(Map<String,Boolean> assignment){
        assignment.keySet().forEach(signedLiterals::remove);
    }

    public boolean isClear() {
        return clear;
    }
}

class SignedLiteral{
    String literal;
    boolean sign;
    SignedLiteral(String literal,boolean sign){
        this.literal = literal;
        this.sign = sign;
    }

    public String getLiteral() {
        return literal;
    }

    public boolean isSign() {
        return sign;
    }
}