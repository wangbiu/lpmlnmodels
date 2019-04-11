package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.solver.impl.LPMLNCDNLSolver;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.EXT;
import static cn.edu.seu.kse.lpmln.util.CommonStrings.NOT;

/**
 * @author 许鸿翔
 * @date 2019/3/8
 * 基于nogood思想的构造，HeuristicAugmentedSubset的进阶版
 * 方便起见有的函数输入直接从成员变量读取
 */
public class NogoodAugmentedSubset extends AugmentedSubset{
    private AugmentedSubsetEvaluator evaluator;
    private List<NogoodAugmentedSubset> pool;
    private Set<Integer> bannedIdx;
    private Integer aimCount;
    private NogoodAugmentedSubset copy = null;
    private double evaluationUpper = Double.MAX_VALUE;
    public NogoodAugmentedSubset(LpmlnProgram program){
        super(program);
        evaluator = new AugmentedSubsetEvaluator();
        evaluator.setLpmlnProgram(program);
        evaluator.init();
        evaluator.subset = this;
        evaluator.buildCond();
        evaluator.checkSatUnsat();
        bannedIdx = new HashSet<>();
    }

    public Pair<NogoodAugmentedSubset,NogoodAugmentedSubset> split(LinkedList<NogoodAugmentedSubset> load){
        load.removeLast();
//        if(load.size()==0){
//            base = 0;
//        }else{
//            base = load.peekLast().getEvaluation()-Math.log(aimCount)/Math.log(2);
//        }
//        double sum = getSum(load);
        NogoodAugmentedSubset sat = this;
        NogoodAugmentedSubset unsat;
        if(copy==null){
            unsat = copy();
            copy = unsat;
        }else{
            unsat = copy;
        }

        LinkedList<Integer> candidate = new LinkedList<>(unknownIdx);
        candidate.removeIf(idx->bannedIdx.contains(idx));
        while(candidate.size()>0){
            int next = candidate.pop();
            sat.evaluator.sat(next);
            unsat.evaluator.unsat(next);
            if(sat.evaluator.isConflict()||unsat.evaluator.isConflict()){
                sat.evaluator.recover();
                unsat.evaluator.recover();
                bannedIdx.add(next);
                continue;
            }

            double as1 = sat.getEvaluation();
            double as2 = unsat.getEvaluation();
            if(load.size()>0 &&
                    less(as1,as2,load.peek().getEvaluation())){
                //<<
                sat.evaluator.recover();
                unsat.evaluator.recover();
                double bigger = Math.max(as1,as2);
                double smaller = Math.min(as1,as2);
                if(bigger-smaller>64){
                    evaluationUpper = Math.min(bigger,evaluationUpper);
                }else{
                    evaluationUpper = Math.min(Math.log(Math.pow(2,bigger-smaller)+1)/Math.log(2)+smaller,evaluationUpper);
                }
                return null;
            }
//            else if((sum/as1>aimCount||sum/as2>aimCount)&&
//                    Math.max(as1/as2,as2/as1)>aimCount){
//                //>< || <>
//                evaluationUpper = (int)Math.min(Math.log(as1+as2)/Math.log(2)+base,evaluationUpper);
//                if(as1/as2>aimCount||as2/as1>aimCount){
//                    bannedIdx.add(next);
//                }
//                sat.evaluator.recover();
//                unsat.evaluator.recover();
//                sum -= as1+as2;
//            }
            else{
                //>>
                copy = null;
                sat.sat(next);
                unsat.unsat(next);
//                if(new AugmentedSubsetSolver(sat).solve().size()==0){
//                    sat.evaluator.doSolving();
//                    System.out.println();
//                }
//                if(new AugmentedSubsetSolver(unsat).solve().size()==0){
//                    System.out.println();
//                }
                sat.evaluator.checkSatUnsat();
                unsat.evaluator.checkSatUnsat();
                return new Pair<>(sat,unsat);
            }
        }

        evaluationUpper = -1;
        return null;
    }

    private boolean less(double a1,double a2,double loadPk){
        double bigger = Math.max(a1,a2);
        double smaller = Math.min(a1,a2);
        if(bigger<loadPk){
            if(loadPk-bigger>1){
                return true;
            }else{
                return Math.pow(2,loadPk-bigger)-1-Math.pow(2,smaller-bigger)>0;
            }
        }else{
            return false;
        }
    }

    private NogoodAugmentedSubset copy(){
        NogoodAugmentedSubset unsat = pool.remove(pool.size()-1);
        Set<Integer> toSat = new HashSet<>(satIdx);
        Set<Integer> toUnsat = new HashSet<>(unsatIdx);
        toSat.removeIf(idx->!unsat.unknownIdx.contains(idx));
        toUnsat.removeIf(idx->!unsat.unknownIdx.contains(idx));
        toSat.forEach(i->{
            unsat.sat(i);
            unsat.evaluator.sat(i);
        });
        toUnsat.forEach(i->{
            unsat.unsat(i);
            unsat.evaluator.unsat(i);
        });
        unsat.bannedIdx.addAll(bannedIdx);
        return unsat;
    }

//    private double getSum(List<NogoodAugmentedSubset> list){
//        double result = 0;
//        for (NogoodAugmentedSubset val : list) {
//            result += Math.pow(2,val.getEvaluation()-base);
//        }
//        return result;
//    }

    public double getEvaluation(){
        int remain = evaluator.getRemainSize();
        return Math.min(remain,evaluationUpper);
    }

    public void setPool(List<NogoodAugmentedSubset> pool) {
        this.pool = pool;
    }

    public void setBannedIdx(Set<Integer> bannedIdx) {
        this.bannedIdx = bannedIdx;
    }

    public void setAimCount(Integer aimCount) {
        this.aimCount = aimCount;
    }
}

class AugmentedSubsetEvaluator extends LPMLNCDNLSolver{
    private int assignStackLastSize = 0;
    private int satNogoodIdx;
    protected NogoodAugmentedSubset subset;
    private Map<String,Set<Integer>> satSupport = new HashMap<>();
    private Map<String,Set<Integer>> unsatSupport = new HashMap<>();
    private List<List<SignedLiteral>> unsatConds = new ArrayList<>();

    public void sat(int idx){
        assignStackLastSize = assignStack.size();
        satNogoodIdx = nogoodDynamic.size();
        Rule r = rules.get(idx);

//        List<Nogood> sat
        Nogood satNogood = new Nogood();
        satNogood.add(getVB(idx),true);
        r.getHead().forEach(h->satNogood.add(h,false));
        putInDynamic(satNogood);
        String key = r.getHead().size()>0?r.getHead().get(0):null;
        key = (key==null&&r.getPositiveBody().size()>0)?r.getPositiveBody().get(0):key;
        key = (key==null&&r.getNegativeBody().size()>0)?r.getNegativeBody().get(0).substring(NOT.length()):key;
        getResultUnit(key,ltnDynamicWatch,nogoodDynamic);
        if(!conflict){
            propagation();
        }
    }

    public void unsat(int idx){
        assignStackLastSize = assignStack.size();
        satNogoodIdx = -1;
        Rule r = rules.get(idx);
        r.getHead().forEach(h->{
            resultUnits.add(new SignedLiteral(h,false));
        });
        resultUnits.add(new SignedLiteral(getVB(idx),true));
        propagation();
    }

    public void checkSatUnsat(){
        for(int i=0;i<assignStack.size()-assignStackLastSize;i++){
            String toCheck = assignStack.get(i);
            boolean val = assignment.get(toCheck);
            String cur = EXT+(val?"T_":"F_")+toCheck;
            if(satSupport.containsKey(cur)){
                satSupport.get(cur).forEach(idx->{
                    if(subset.getUnknownIdx().contains(idx)){
                        subset.sat(idx);
                    }
                });
            }
            if(unsatSupport.containsKey(cur)){
                unsatSupport.get(cur).forEach(idx->{
                    if(subset.unknownIdx.contains(idx)){
                        for (SignedLiteral sl : unsatConds.get(idx)) {
                            if(!assignment.containsKey(sl.getLiteral())||
                                    sl.isSign()!=assignment.get(sl.getLiteral())){
                                return;
                            }
                        }
                        subset.unsat(idx);
                    }
                });
            }
        }
    }

    public void recover(){
        if(satNogoodIdx>=0){
            removeFromDyn(satNogoodIdx);
        }
        while(assignStack.size()> assignStackLastSize){
            resign(assignStack.pop());
        }
    }

    private void removeFromDyn(int idx){
        Nogood rmvd = nogoodDynamic.remove(idx);
        removeWatch(rmvd.getW1(),idx);
        removeWatch(rmvd.getW2(),idx);
        dynamicNogoodSet.remove(rmvd.getCore());
    }

    private void removeWatch(String str,Integer idx){
        if(str!=null){
            ltnDynamicWatch.get(str).remove(idx);
        }
    }

    public int getRemainSize(){
        return toAssign.size();
    }

    public void buildCond(){
        for(int i=0;i<rules.size();i++){
            Rule r = rules.get(i);
            for (String h : r.getHead()) {
                addIntoMap(satSupport,EXT+"T_"+h,i);
                addIntoMap(unsatSupport,EXT+"F_"+h,i);
            }
            for (String pb : r.getPositiveBody()) {
                addIntoMap(satSupport,EXT+"F_"+pb,i);
                addIntoMap(unsatSupport,EXT+"T_"+pb,i);
            }
            for (String nb : r.getNegativeBody()) {
                addIntoMap(satSupport,EXT+"T_"+nb.substring(NOT.length()),i);
                addIntoMap(unsatSupport,EXT+"F_"+nb.substring(NOT.length()),i);
            }
        }

        for(int i=0;i<rules.size();i++){
            Rule r = rules.get(i);
            List<SignedLiteral> unsatCond = new ArrayList<>();
            r.getHead().forEach(h->unsatCond.add(new SignedLiteral(h,false)));
            r.getPositiveBody().forEach(pb->unsatCond.add(new SignedLiteral(pb,true)));
            r.getNegativeBody().forEach(nb->unsatCond.add(new SignedLiteral(nb.substring(NOT.length()),false)));
            unsatConds.add(unsatCond);
        }
    }

    public boolean isConflict(){
        return conflict;
    }


}