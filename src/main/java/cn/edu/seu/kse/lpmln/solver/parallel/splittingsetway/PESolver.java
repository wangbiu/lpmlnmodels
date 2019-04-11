package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.NOT;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.getLiteral;

public class PESolver extends LPMLNBaseSolver implements Runnable {
    private LpmlnProgram top;
    private Set<String> U;
    private WeightedAnswerSet x;
    private LpmlnProgram partialEvaluation;
    private String arch;
    private LPMLNSolver solver;
    private Set<String> assertAtoms;
    private Set<Integer> in;
    private Set<Integer> out;
    private Set<Set<String>> scc;
    private Set<String> headInuP;
    private Set<String> bodyPOutuP;
    private Set<String> supportedLit = new HashSet<>();
    private Map<Integer,String> supLit = new HashMap<>();
    private Map<String,Integer> litToSL = new HashMap<>();
    private List<Set<String>> sl = new ArrayList<>();
    private List<Rule> eccu;
    private List<Rule> external;
    private Set<String> metaFilt = new HashSet<>();
    private static final String SHOW = "#show ";
    private boolean splittingSet = false;


    public PESolver(LpmlnProgram top, Set<String> U,Splitter splitter, WeightedAnswerSet x, String arch) {
        this.top = top;
        this.U = U;
        this.x = x;
        this.arch = arch;
        this.assertAtoms = splitter.getAssertAtoms();
        this.in = splitter.getIn();
        this.out = splitter.getOut();
        this.splittingSet = splitter.isSplittingSet();
    }

    @Override
    public void run() {
        generatePartialEvaluation();
        solver = new AugmentedSolver();
        solver.setFiltResult(false);
        solver.setCalculatePossibility(false);
        weightedAs = solver.solveProgram(partialEvaluation);
        filtX();
        if(weightedAs.size()!=new LPMLNBaseSolver().solveProgram(partialEvaluation).size()){
            System.out.println(123);
        }
//        System.out.println("was:"+weightedAs.size()+"\t"+Thread.currentThread().getId());
        combineAnswerSet();
    }

    private void filtX(){
        String[] metas = lpmlnProgram.getMetarule().split("\r\n");
        if(metas.length>0){
            Set<String> filt = new HashSet<>();
            for (String meta : metas) {
                meta = meta.substring(SHOW.length());
                meta = meta.substring(0,meta.indexOf("/"));
                filt.add(meta);
            }
            Iterator<String> xiter = x.getAnswerSet().getLiterals().iterator();
            while (xiter.hasNext()){
                String str = xiter.next();
                int aim = str.indexOf("(");
                aim = aim>0?aim:str.length();
                if(!filt.contains(str.substring(0,aim))){
                    xiter.remove();
                }
            }
        }
    }

    // TODO: following function might not be correct!
    private void combineAnswerSet() {
        weightedAs.forEach(AS -> {
            AS.getAnswerSet().getLiterals().addAll(x.getAnswerSet().getLiterals());
            // 权重对应相加
            for (int i = 0; i < 2; i++) {
                AS.getWeights().set(i,x.getWeights().get(i)+AS.getWeights().get(i));
            }
        });
    }

    private void generatePartialEvaluation() {
        if(splittingSet){
            eccu = new ArrayList<>();
            external = new ArrayList<>();
            out.forEach(idx->{
                external.add(lpmlnProgram.getRules().get(idx).clone());
            });
        }else{
            eccu = getECCU();
            external = getExternalRule();
        }

        // 1. 计算deleting set
        List<Rule> deletingSet = getDeletingSet();

        StringBuilder botResult = new StringBuilder();
        x.getAnswerSet().getLiterals().forEach(lit->{
            if(U.contains(lit)){
                botResult.append(lit).append(".").append(System.lineSeparator());
            }
        });

        // 2. 计算partial evaluation
        List<Rule> peRules = new ArrayList<>();
        List<Rule> candidate = new ArrayList<>(top.getRules());
        candidate.addAll(eccu);
        candidate.addAll(external);

        for (Rule rule: candidate) {
            if(rule.isUnWeighted()){
                peRules.add(rule);
                continue;
            }
//            with deleting set as a hash set, question is how to calculate
//            the hash code of a Rule
//            if (deletingSet.contains(rule)) {
//                continue;
//            }
            if (contains(deletingSet, rule) > -1) {
                continue;
            }
            rule.getPositiveBody().removeIf(l -> U.contains(l));
            rule.getNegativeBody().removeIf(l -> U.contains(l.substring(4)));
            rule.setText(getText(rule, false));
            rule.setOriginalrule(getText(rule, true));

            int ind;
            // group 和 rep rule 的计算
            if ((ind = contains(peRules, rule)) > -1) {
                peRules.get(ind).setWeight(peRules.get(ind).getWeight() + rule.getWeight());
            }
            else {
                peRules.add(rule);
            }
        }

        for(int i=0;i<peRules.size();i++){
            peRules.get(i).setId(i);
        }

//        System.out.println("PE Done");
        partialEvaluation = new LpmlnProgram(peRules, top.getFactor(), top.getHerbrandUniverse(), top.getMetarule(),top.getSolversUsed());
    }

    private List<Rule> getExternalRule(){
        List<Rule> external = new ArrayList<>();
        getSupport();
        getDsl();

        in.forEach(idx->{
            String head = supLit.get(idx);
            Integer slIdx = litToSL.get(head);
            if(slIdx!=null){
                Rule r = new Rule();
                r.setSoft(false);
                r.getPositiveBody().addAll(lpmlnProgram.getRules().get(idx).getPositiveBody());
                r.getNegativeBody().addAll(lpmlnProgram.getRules().get(idx).getNegativeBody());
                r.getHead().add("xE_"+slIdx);
                r.setOriginalrule(getText(r,true));
                external.add(r);
            }
        });

        out.forEach(idx->{
            Rule original = lpmlnProgram.getRules().get(idx).clone();
            Rule r = new Rule();
            r.setSoft(original.isSoft());
            r.setWeight(original.getWeight());
            r.setPositiveBody(new ArrayList<>(original.getPositiveBody()));
            r.setNegativeBody(new ArrayList<>(original.getNegativeBody()));
            r.setHead(original.getHead());
            original.getPositiveBody().forEach(pb->{
                if(litToSL.containsKey(pb)){
                    Integer slIdx = litToSL.get(pb);
                    r.getPositiveBody().add("xE_"+slIdx);
                }
            });
            r.setOriginalrule(getText(r,true));
            external.add(r);
        });

        return external;
    }

    private void getDsl(){
        Map<String,Set<String>> gp = LpmlnProgramHelper.getDependency(lpmlnProgram);
        scc = LpmlnProgramHelper.reachableToLitSets(LpmlnProgramHelper.dependToReachable(gp));

        headInuP = new HashSet<>();
        bodyPOutuP = new HashSet<>();

        in.forEach(idx->{
            lpmlnProgram.getRules().get(idx).getHead().forEach(l->headInuP.add(getLiteral(l)));
        });

        out.forEach(idx->{
            lpmlnProgram.getRules().get(idx).getPositiveBody().forEach(l->bodyPOutuP.add(getLiteral(l)));
        });

        Set<Set<String>> s1s = subsets(headInuP);
        Set<Set<String>> s2s = subsets(bodyPOutuP);

        s1s.forEach(s1->{
            s2s.forEach(s2->{
                Set<String> induced = new HashSet<>(headInuP);
                induced.addAll(bodyPOutuP);
                induced.removeAll(s1);
                induced.removeAll(s2);
                Set<String> loop;
                while(true){
                    loop = scc(gp,induced,s1.iterator().next());
                    if(loop.size()==0){
                        return;
                    }
                    Set<String> e = new HashSet<>(loop);
                    e.retainAll(U);
                    Set<Integer> rNegIdxs = rNeg(e);
                    if(x.getAnswerSet().getLiterals().containsAll(e)&&
                            in.containsAll(rNegIdxs)){
                        e.forEach(l->{
                            litToSL.put(l,sl.size());
                        });
                        sl.add(e);
                        return;
                    }else{
                        Set<String> s = new HashSet<>();
                        rNegIdxs.forEach(idx->{
                            lpmlnProgram.getRules().get(idx).getHead().forEach(h->{
                                s.add(getLiteral(h));
                            });
                        });
                        e.removeAll(x.getAnswerSet().getLiterals());
                        s.addAll(e);
                        induced.addAll(s);
                    }
                }

            });
        });


//        scc.forEach(loop->{
//            Set<String> s1 = new HashSet<>(loop);
//            Set<String> s2 = new HashSet<>(loop);
//            s1.retainAll(headInuP);
//            s2.retainAll(bodyPOutuP);
//            if(s1.isEmpty()||s2.isEmpty()){
//                return;
//            }
//            loop.forEach(l->{
//                litToSL.put(l,sl.size());
//            });
//            sl.add(loop);
//        });
    }

    private Set<Integer> rNeg(Set<String> e){
        Set<Integer> idxs = new HashSet<>();
        supLit.forEach((k,v)->{
            if(e.contains(v)){
                idxs.add(k);
            }
        });
        return idxs;
    }

    private Set<String> scc(Map<String,Set<String>> gp, Set<String> induced, String key){
        Set<String> scc = new HashSet<>();
        if(induced.contains(key)){
            return scc;
        }
        LinkedList<String> tovisit = new LinkedList<>();
        tovisit.add(key);
        scc.add(key);
        while(!tovisit.isEmpty()){
            String cur = tovisit.poll();
            gp.getOrDefault(cur,new HashSet<>()).forEach(to->{
                if(!induced.contains(to)&&!scc.contains(to)){
                    tovisit.offer(to);
                    scc.add(to);
                }
            });
        }
        return scc;
    }

    private Set<Set<String>> subsets(Set<String> sst){
        List<String> eles = new ArrayList<>(sst);
        Set<Set<String>> subs = new HashSet<>();
        boolean[] permutation = new boolean[eles.size()];
        //不要空集
        while(next(permutation)){
            Set<String> sub = new HashSet<>();
            for(int i=0;i<eles.size();i++){
                if(permutation[i]){
                    sub.add(eles.get(i));
                }
            }
            subs.add(sub);
        }
        return subs;
    }

    private boolean next(boolean[] permutation){
        int idx=0;
        while (idx<permutation.length){
            if(!permutation[idx]){
                permutation[idx] = true;
                return true;
            }else{
                permutation[idx] = false;
                idx++;
            }
        }
        return false;
    }

    private void getSupport(){
//        Set<String> aim = new HashSet<>();
//        aim.add("fire(0,0)");
//        aim.add("fire(0,2)");
//        aim.add("fire(2,1)");
//        aim.add("fire(2,0)");
//        if(x.getAnswerSet().getLiterals().containsAll(aim)){
//            System.out.println();
//        }
        for(int i=0;i<lpmlnProgram.getRules().size();i++){
            boolean nextRule = false;
            Rule r = lpmlnProgram.getRules().get(i);
            for (String pb : r.getPositiveBody()) {
                pb = getLiteral(pb);
                if(!x.getAnswerSet().getLiterals().contains(pb)){
                    nextRule = true;
                    break;
                }
            }
            if(nextRule) continue;
            for (String nb : r.getNegativeBody()) {
                nb = getLiteral(nb);
                if(!U.contains(nb)||x.getAnswerSet().getLiterals().contains(nb)){
                    nextRule = true;
                    break;
                }
            }
            if(nextRule) continue;
            String sup = null;
            for (String h : r.getHead()) {
                h = getLiteral(h);
                if(x.getAnswerSet().getLiterals().contains(h)){
                    if(sup!=null){
                        nextRule = true;
                        break;
                    }
                    sup = h;
                }
            }
            if(nextRule) continue;
            if(sup!=null){
                supLit.put(i,sup);
                supportedLit.add(sup);
            }
        }
    }

    private List<Rule> getECCU(){
        List<Rule> eccu = new ArrayList<>();
        Set<String> xLits = x.getAnswerSet().getLiterals();
        assertAtoms.forEach(l->{
            Rule r = new Rule();
            r.setSoft(false);
            if(xLits.contains(l)){
                r.getNegativeBody().add(NOT+l);
                r.setOriginalrule(":- "+NOT+l+".");
            }else{
                r.getPositiveBody().add(l);
                r.setOriginalrule(":- "+l+".");
            }
            eccu.add(r);
        });
        return eccu;
    }

    private int contains(List<Rule> rules, Rule rule) {
        for (Rule r: rules) {
            if (!(r.getHead().size() == rule.getHead().size() && r.getHead().containsAll(rule.getHead()))) {
                continue;
            }
            if (!(r.getPositiveBody().size() == rule.getPositiveBody().size() && r.getPositiveBody().containsAll(rule.getPositiveBody()))) {
                continue;
            }
            if (!(r.getNegativeBody().size() == rule.getNegativeBody().size() && r.getNegativeBody().containsAll(rule.getNegativeBody()))) {
                continue;
            }
            return rules.indexOf(r);
        }
        return -1;
    }

    private String getText(Rule rule, boolean originalText) {
        StringBuilder sb = new StringBuilder();
        rule.getHead().forEach(lit -> sb.append(lit).append("; "));
        int i = sb.lastIndexOf(";");
        if (i != -1){
            sb.deleteCharAt(i);
        }
        if (rule.getPositiveBody().size() != 0 || rule.getNegativeBody().size() != 0) {
            sb.append(":- ");
            rule.getPositiveBody().forEach(lit -> sb.append(lit).append(", "));
            rule.getNegativeBody().forEach(lit -> sb.append(lit).append(", "));
            if (originalText) {
                sb.deleteCharAt(sb.lastIndexOf(","));
                sb.deleteCharAt(sb.lastIndexOf(" "));
                sb.append(".");
            }
        }
        else {
            if (!originalText) sb.append(" :- ");
            else sb.append(".");
        }

        return sb.toString();
    }

    private List<Rule> getDeletingSet() {
        Set<String> X = x.getAnswerSet().getLiterals();
        X.removeIf(literal -> literal.equals("kse_solve_trick"));
        List<Rule> DS = new ArrayList<>();
        List<Rule> candidates = new ArrayList<>(top.getRules());
        candidates.addAll(eccu);
        candidates.addAll(external);
        for (Rule rule: candidates) {
            boolean willAdd = false;
            boolean isNull = true;
            for (String pb: rule.getPositiveBody()) {
                if (U.contains(pb)) {
                    isNull = false;
                    if (!X.contains(pb)) {
                        willAdd = true;
                        break;
                    }
                }
            }
            if (!isNull) {
                if (willAdd) {
                    DS.add(rule);
                    continue;
                }
            }
            willAdd = false;
            for (String nb: rule.getNegativeBody()) {
                String[] nbs = nb.split(" ");
                if (U.contains(getLiteral(nbs[1]))) {
                    if (X.contains(nbs[1])) {
                        willAdd = true;
                        break;
                    }
                }
            }
            if (willAdd) {
                DS.add(rule);
            }
        }
        return DS;
    }

    public LPMLNSolver getSolver() {
        return solver;
    }

    public void setSolver(LPMLNSolver solver) {
        this.solver = solver;
    }
}
