package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.exception.solveexception.AssignConflictException;
import cn.edu.seu.kse.lpmln.model.AnswerSet;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.util.*;

import static cn.edu.seu.kse.lpmln.solver.impl.LPMLNLoopSolver.NEGATION;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.*;

/**
 * DPLL简单实现,优化方式：ASP中的 & 2watch
 * @author 许鸿翔
 * @date 2019/3/19
 */
public class LPMLNLoopSolver extends LPMLNBaseSolver{
    protected static final String NOT = "not ";
    protected static final String NEGATION = "-";
    protected static final String VB = "VB_";
    /**
     * T:in
     * F:not in
     * no key:unassigned
     */
    private Map<String,Boolean> assignment;

    /**
     * 现在为T，需要flip为F
     */
    private Set<String> toFlip;

    /**
     * unassigned
     */
    private LinkedList<String> toAssign;

    /**
     * assigned stack
     */
    private LinkedList<String> assignStack;

    /**
     * 文字集合,lit_{\Pi}
     */
    private Set<String> literals;

    /**
     * literal到nogood的映射
     */
    private Map<String,Set<Nogood>> nogoodMap;

    private List<Nogood> satNogoods;

    private List<Double> weights;

    private Map<String,Set<Integer>> litInHead;

    private Set<String> metaFilt;


    private void init(){
        assignment = new HashMap<>();
        toFlip = new HashSet<>();
        toAssign = new LinkedList<>();
        assignStack = new LinkedList<>();
        satNogoods = new ArrayList<>();
        nogoodMap = new HashMap<>();
        literals = new HashSet<>();
        litInHead = new HashMap<>();
        weights = new ArrayList<>();
        weightedAs = new ArrayList<>();

        getLiterals();

        generateMeta();

        toAssign.addAll(literals);

        constructNogoodMap();
    }

    @Override
    public void executeSolving(){
        init();
        boolean top = false;
        while(!top){
            try{
                propagate();
            }catch(AssignConflictException e){
                top = backTrackAndFlip();
                continue;
            }
            if(toAssign.size()==0){
                produceWas();
                top = backTrackAndFlip();
            }else{
                decide();
            }
        }
    }

    static String SHOW = "#show ";
    private void generateMeta(){
        Set<String> allowedPreds = new HashSet<>();
        if(lpmlnProgram.getMetarule().length()>0){
            metaFilt = new HashSet<>();
            String[] metas = lpmlnProgram.getMetarule().split("\n");
            for (String meta : metas) {
                int idx1 = meta.indexOf(SHOW);
                int idx2 = meta.indexOf("/");
                allowedPreds.add(meta.substring(idx1+SHOW.length(),idx2));
            }
        }

        literals.forEach(lit->{
            String predicate = null;
            if(lit.indexOf("(")>0){
                predicate = lit.substring(0,lit.indexOf("("));
            }else{
                predicate = lit;
            }
            if(allowedPreds.contains(predicate)){
                metaFilt.add(lit);
            }
        });
    }

    private void decide(){
        String next = toAssign.pop();
        assignment.put(next,true);
        toFlip.add(next);
        assignStack.push(next);
    }

    /**
     * TODO:实现很水，需要优化
     */
    private void propagate(){
        LinkedList<String> toPropagate = new LinkedList<>();
        toPropagate.add(assignStack.peek());
        while(toPropagate.size()>0){
            String cur = toPropagate.poll();
            nogoodMap.get(cur).forEach(nogood -> {
                SignedLiteral cons = nogood.check(assignment);
                if(cons!=null){
                    if(!assignment.containsKey(cons.getLiteral())){
                        toAssign.remove(cons.getLiteral());
                        assignment.put(cons.getLiteral(),!cons.isSign());
                        assignStack.push(cons.getLiteral());
                        toPropagate.offer(cons.getLiteral());
                    }else if(assignment.get(cons.getLiteral()).equals(cons.isSign())){
                        throw new AssignConflictException("assign conflict in&not in");
                    }
                }
            });
        }
    }

    private void initialPropagate(List<Nogood> nogoods){
        nogoods.forEach(nogood -> {
            SignedLiteral cons = nogood.check(assignment);
            if(cons!=null){
                if(!assignment.containsKey(cons.getLiteral())){
                    toAssign.remove(cons.getLiteral());
                    assignment.put(cons.getLiteral(),!cons.isSign());
                    assignStack.push(cons.getLiteral());
                    propagate();
                }else if(assignment.get(cons.getLiteral()).equals(cons.isSign())){
                    throw new AssignConflictException("assign conflict in&not in");
                }
            }
        });
    }

    /**
     *
     * @return T:solved F:continue
     */
    private boolean backTrackAndFlip(){
        while(assignStack.size()>0){
            String next = assignStack.pop();
            assignment.remove(next);
            if(toFlip.contains(next)){
                toFlip.remove(next);
                assignStack.push(next);
                assignment.put(next,false);
                return false;
            }
            toAssign.add(next);
        }
        return true;
    }

    private void produceWas(){
        WeightedAnswerSet was = new WeightedAnswerSet();
        AnswerSet as = new AnswerSet();
        was.setAnswerSet(as);
        Integer[] ini = {0,0};
        was.setWeights(Arrays.asList(ini));
        for(int i=0;i<satNogoods.size();i++){
            Nogood sat = satNogoods.get(i);
            try {
                sat.check(assignment);
            }catch (AssignConflictException e){
                //兼容翻译求解
                Double w = weights.get(i);
                if(w==null){
                    //hard
                    was.getWeights().set(1,was.getWeights().get(1)+1);
                }else{
                    //TODO:小数处理
                    was.getWeights().set(0,was.getWeights().get(0)+w.intValue());
                }
            }
        }
        assignment.forEach((k,v)->{
            if(v&&!k.startsWith(VB)){
                if(metaFilt==null||metaFilt.contains(k)){
                    as.add(k);
                }
            }
        });
        weightedAs.add(was);
    }

    private void constructNogoodMap(){
        List<Nogood> nogoods = new ArrayList<>();
        //解释一致性产出的nogood
        literals.forEach(literal->{
            if(literal.startsWith(NEGATION) &&
                    literals.contains(literal.substring(NEGATION.length()))){
                Nogood toadd = new Nogood(literal.substring(NEGATION.length()));
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
            //imp2: a1,a2,...,not am+1,not am+2,...->Vb
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
                //litToSatCond.get(h).add(satNogoods.size());
            });
            impl3.add(getBodyPred(i),true);
//            litToSatCond.get(getBodyPred(i)).add(satNogoods.size());
            satNogoods.add(impl3);
            if(LPMLNApp.semantics.equals(LPMLNApp.SEMANTICS_WEAK)&&!r.isSoft()){
                //hard rule under weak semantics
                nogoods.add(impl3);
            }
            if(!r.isSoft()){
                //hard rule
                weights.add(null);
            }else{
                //soft rules
                weights.add(r.getWeight());
            }
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

        //loop nogood,LF定义用CLF
        Set<Set<String>> loops = reachableToLitSets(dependToReachable(getLiteralPostiveDependency(lpmlnProgram)));
        loops.forEach(loop-> expandAndAddLF(loop,nogoods));

        constructMap(nogoods);

        initialPropagate(nogoods);

    }

    private void constructMap(List<Nogood> nogoods){
        literals.forEach(lit->{
            nogoodMap.put(lit,new HashSet<>());
        });
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            nogoodMap.put(getBodyPred(i),new HashSet<>());
        }
        nogoods.forEach(nogood -> {
            nogood.getKeySet().forEach(lit->{
                nogoodMap.get(lit).add(nogood);
            });
        });
    }

    private void expandAndAddLF(Set<String> loop,List<Nogood> nogoods){
        List<Boolean> cur = new ArrayList<>();
        for(int i=0;i<loop.size();i++){
            cur.add(false);
        }
        List<String> loopLits = new ArrayList<>(loop);
        while(nextPermutation(cur)){
            Set<String> subset = new HashSet<>();
            for(int i=0;i<loopLits.size();i++){
                if(cur.get(i)){
                    subset.add(loopLits.get(i));
                }
            }
            nogoods.add(addLF(subset));
        }
    }

    private boolean nextPermutation(List<Boolean> cur){
        for(int i=0;i<cur.size();i++){
            if(!cur.get(i)){
                cur.set(i,true);
                return true;
            }else{
                cur.set(i,false);
            }
        }
        return false;
    }

    private Nogood addLF(Set<String> loop){
        Nogood loopNogood = new Nogood();
        Set<Integer> candidates = new HashSet<>();
        loop.forEach(lit->{
            loopNogood.add(lit,true);
            candidates.addAll(litInHead.get(lit));
        });

        candidates.forEach(candidate->{
            for (String pb : lpmlnProgram.getRules().get(candidate).getPositiveBody()) {
                if(loop.contains(pb)){
                    return;
                }
            }
            loopNogood.add(getBodyPred(candidate),false);
            //TODO:not in head
            for (String h : lpmlnProgram.getRules().get(candidate).getHead()) {
                if(!loop.contains(h)){
                    loopNogood.add(h,false);
                }
            }
        });
        return loopNogood;
    }

    private void getLiterals(){
        //先获取所有文字
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule rule = lpmlnProgram.getRules().get(i);
            //只出现在体部的文字在实例化的时候就挂了
            for (String headStr : rule.getHead()) {
                if(headStr.startsWith(NOT)){
                    headStr = headStr.substring(NOT.length());
                }
                literals.add(headStr);
                Set<Integer> inHead = litInHead.getOrDefault(headStr,new HashSet<>());
                inHead.add(i);
                litInHead.put(headStr,inHead);
            }
        }
    }

    private String getBodyPred(int i){
        return VB+i;
    }
}

class Nogood{
    private Map<String,Boolean> signedLiterals = new HashMap<>();

    /**
     *
     */
    Nogood(){
    }

    void add(String literal,boolean sign){
        signedLiterals.put(literal,sign);
    }

    /**
     * 解释一致性产出的nogood
     * @param atom atom
     */
    Nogood(String atom){
        signedLiterals.put(atom,true);
        signedLiterals.put(NEGATION+atom,true);
    }

    Set<String> getKeySet(){
        return signedLiterals.keySet();
    }

    SignedLiteral check(Map<String,Boolean> assignment){
        if(signedLiterals.size()==0){
            return null;
        }else if(signedLiterals.size()==1){
            Map.Entry<String,Boolean> entry = signedLiterals.entrySet().iterator().next();
            return new SignedLiteral(entry.getKey(),entry.getValue());
        }else{
            Iterator<Map.Entry<String,Boolean>> sgIterator = signedLiterals.entrySet().iterator();
            int uncertainCount = 0;
            SignedLiteral result = null;
            while(sgIterator.hasNext()){
                Map.Entry<String,Boolean> ent = sgIterator.next();
                Boolean val = assignment.get(ent.getKey());
                if(val==null){
                    //不确定项
                    uncertainCount++;
                    if(result==null){
                        result = new SignedLiteral(ent.getKey(),ent.getValue());
                    }
                }else if(val.equals(ent.getValue())){
                    //此项满足
                }else{
                    //!val.equals(ent.getValue())
                    //nogood已经违反
                    return null;
                }
            }
            if(uncertainCount==1){
                return result;
            }else if(uncertainCount==0){
                throw new AssignConflictException("assign conflict");
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return signedLiterals.toString();
    }

}

class SignedLiteral{
    private String literal;
    private boolean sign;
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
