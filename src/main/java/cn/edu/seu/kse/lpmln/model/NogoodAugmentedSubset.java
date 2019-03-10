package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
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
    private Map<String,List<Nogood>> litToNogood = new HashMap<>();

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

    @Override
    public NogoodAugmentedSubset clone(){
        NogoodAugmentedSubset clone = new NogoodAugmentedSubset(lpmlnProgram);

        //不共享部分
        //lit到nogood的映射，需要维护nogood中的元素数量
        Map<String,List<Nogood>> litToNogoodClone = new HashMap<>();
        litToNogood.forEach((k,v)->{
            List<Nogood> cur = new ArrayList<>();
            v.forEach(nogood->cur.add(nogood.clone()));
            litToNogoodClone.put(k,cur);
        });
        clone.litToNogood = litToNogoodClone;
        //assign
        clone.assignment = (Map<String, Boolean>) ((HashMap)assignment).clone();
        //添加时clone，所以可以共享
        List<Nogood> satNogoodsClone = new ArrayList<>();
        satNogoods.forEach(nogood -> satNogoodsClone.add(nogood.clone()));
        clone.satNogoods = satNogoodsClone;

        //共享部分
        //规则unsat需要添加的signed-literal，相对程序固定
        clone.unsatAssign = unsatAssign;
        //lit_{\Pi}不会变化
        clone.literals = literals;


        return clone;
    }

    public NogoodAugmentedSubset(LpmlnProgram lpmlnProgram){
        super(lpmlnProgram);

        init();

        List<Nogood> nogoods = constructNogood();

        Map<String,Boolean> toAssign = constructNogoodMap(nogoods,litToNogood);

        toAssign.forEach(this::assign);

        if(LPMLNApp.semantics.equals(LPMLNApp.SEMANTICS_WEAK)){
            for (int i=0;i<lpmlnProgram.getRules().size();i++){
                if(!unknownIdx.contains(i)){
                    sat(i);
                }
            }
        }
        System.out.println("");
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
        //impl3
        Nogood toadd = satNogoods.get(idx);
        toadd.adapt(assignment);

        if(toadd.getLiteralSet().size()==1){
            //对应nogood已经可以产出result-unit
            toadd.getSignedLiterals().forEach((k,v)->assign(k,!v));
        }else{
            //将nogood添加到map中，size==0的时候不添加
            toadd.getLiteralSet().forEach(lit->litToNogood.get(lit).add(toadd));
        }

        return super.sat(idx);
    }

    @Override
    public boolean unsat(int idx){

        unsatAssign.get(idx).forEach(this::assign);

        return super.unsat(idx);
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
                throw new SolveException("literal assign conflict");
            } else {
                return;
            }
        }

        assignment.put(literal,sign);
        checkSatisfiability(literal,sign);

        Iterator<Nogood> nogoodIterator = litToNogood.get(literal).iterator();

        while(nogoodIterator.hasNext()){
            Nogood cur = nogoodIterator.next();
            if(cur.isClear()){
                nogoodIterator.remove();
                continue;
            }
            SignedLiteral result = cur.assign(literal,sign);
            //propagation
            if(result==null){
                //nogood un-violable
                nogoodIterator.remove();
            }else if(!"".equals(result.literal)){
                //result-unit
                assign(result.literal,!result.sign);
            }
        }
    }

    private void checkSatisfiability(String literal,boolean sign){
        litToSatCond.get(literal).forEach(idx->{
            Nogood satNogood = satNogoods.get(idx);
            SignedLiteral sLit = satNogood.assign(literal,sign);
            if(sLit==null){
                //satNogood已经无法被违反
                super.sat(idx);
            }else if(satNogood.getLiteralSet().size()==0){
                //satNogood已经被违反
                super.unsat(idx);
            }
        });
    }

    /**
     * 根据nogood列表创建lit到nogood的映射
     * 比如nogood:{Ta,Fb}则a,b都指向它
     * 返回直接的result-unit比如{Ta}
     * @param nogoods nogoods
     * @param map map
     * @return result-units
     */
    private Map<String,Boolean> constructNogoodMap(List<Nogood> nogoods,Map<String,List<Nogood>> map){
        //litToNogood
        Map<String,Boolean> toAssign = new HashMap<>();
        literals.forEach(lit->map.put(lit,new ArrayList<>()));
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            map.put(getBodyPred(i),new ArrayList<>());
        }

        nogoods.forEach(nogood -> {
            Set<String> litSet = nogood.getLiteralSet();
            if(litSet.size()==1){
                nogood.getSignedLiterals().forEach((k,v)->toAssign.put(k,!v));
            }else{
                litSet.forEach(lit->{
                    map.get(lit).add(nogood);
                });
            }

        });
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

    private List<Nogood> constructNogood(){
        List<Nogood> nogoods = new ArrayList<>();

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
            r.getNegativeBody().forEach(nb-> impl2.add(nb,false));
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

        if(LPMLNApp.isDebugging()){
            System.out.println("nogood done");
        }

        return nogoods;
    }

    private String getBodyPred(int i){
        return VB+i;
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
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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