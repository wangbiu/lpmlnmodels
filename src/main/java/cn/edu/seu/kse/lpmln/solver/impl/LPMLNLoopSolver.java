package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.exception.solveexception.AssignConflictException;
import cn.edu.seu.kse.lpmln.model.*;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.NEGATION;
import static cn.edu.seu.kse.lpmln.util.CommonStrings.NOT;
import static cn.edu.seu.kse.lpmln.util.CommonStrings.VB;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.*;

/**
 * DPLL简单实现
 * @author 许鸿翔
 * @date 2019/3/19
 */
public class LPMLNLoopSolver extends LPMLNBaseSolver{
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

    private static String SHOW = "#show ";
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
        if(assignStack.isEmpty()){
            return;
        }
        LinkedList<String> toPropagate = new LinkedList<>();
        toPropagate.add(assignStack.peek());
        while(toPropagate.size()>0){
            String cur = toPropagate.poll();
//            if(!nogoodMap.containsKey(cur)){
//                System.out.println(123);
//            }
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
        Set<Integer> unsatRules = new HashSet<>();
        for(int i=0;i<satNogoods.size();i++){
            Nogood sat = satNogoods.get(i);
            if(unsatRules.contains(sat.getRuleId())){
                continue;
            }
            try {
                sat.check(assignment);
            }catch (AssignConflictException e){
                unsatRules.add(sat.getRuleId());
            }
        }
        unsatRules.forEach(i->{
            //兼容翻译求解
            Double w = weights.get(i);
            if(w==null){
                //hard
                was.getWeights().set(1,was.getWeights().get(1)+1);
            }else{
                //TODO:小数处理
                was.getWeights().set(0,was.getWeights().get(0)+w.intValue());
            }
        });
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

        //支持性 nogood
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule r = lpmlnProgram.getRules().get(i);
            for (String h : r.getHead()) {
                //imp1: VB->a1,a2,...,not am+1,not am+2,...  v!heand-h
                //体部成立表示体部项都成立
                for (String pb : r.getPositiveBody()) {
                    Nogood impl1 = new Nogood();
                    impl1.add(getSupPred(i,h),true);
                    impl1.add(pb,false);
                    nogoods.add(impl1);
                }
                for (String nb : r.getNegativeBody()) {
                    Nogood impl1 = new Nogood();
                    impl1.add(getSupPred(i,h),true);
                    impl1.add(nb.substring(NOT.length()),true);
                    nogoods.add(impl1);
                }
                for (String hn : r.getHead()) {
                    if(!hn.equals(h)){
                        Nogood impl1 = new Nogood();
                        impl1.add(getSupPred(i,h),true);
                        impl1.add(hn,true);
                        nogoods.add(impl1);
                    }
                }
                //imp2: a1,a2,...,not am+1,not am+2,... ,!heand-h->Vb
                //体部项都成立表示体部成立
                Nogood impl2 = new Nogood();
                impl2.add(getSupPred(i,h),false);
                r.getPositiveBody().forEach(pb-> impl2.add(pb,true));
                r.getNegativeBody().forEach(nb-> impl2.add(nb.substring(NOT.length()),false));
                r.getHead().forEach(hn->{
                    if(!hn.equals(h)){
                        impl2.add(hn,false);
                    }
                });
                nogoods.add(impl2);
            }

        }

        //atom-oriented nogood
        //impl3: vb1 V vb2 V ... ->a
        //体部成立表示头部成立，头部析取
        //规则成立性，SAT集合中才有此性质
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            Rule r = lpmlnProgram.getRules().get(i);
            for (String h : r.getHead()) {
                Nogood impl3 = new Nogood();
                impl3.add(h,false);
                impl3.add(getSupPred(i,h),true);
                impl3.setRuleId(i);

                satNogoods.add(impl3);
                if(LPMLNApp.semantics.equals(LPMLNApp.SEMANTICS_WEAK)&&!r.isSoft()){
                    //hard rule under weak semantics
                    nogoods.add(impl3);
                }
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
                supNogoodMap.get(h).add(getSupPred(i,h),false);
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
            for (String h : lpmlnProgram.getRules().get(i).getHead()) {
                nogoodMap.put(getSupPred(i,h),new HashSet<>());
            }
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
            Rule r = lpmlnProgram.getRules().get(candidate);
            for (String pb : r.getPositiveBody()) {
                if(loop.contains(pb)){
                    return;
                }
            }
            r.getHead().forEach(h->{
                if(loop.contains(h)){
                    loopNogood.add(getSupPred(candidate,h),false);
                }
            });
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

    private String getSupPred(int i,String lit){
        return VB+i+"_"+lit;
    }

//    private String getBodyPred(int i){
//        return VB+i;
//    }
}

