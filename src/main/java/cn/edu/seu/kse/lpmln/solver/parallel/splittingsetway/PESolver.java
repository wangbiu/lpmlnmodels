package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PESolver extends LPMLNBaseSolver implements Runnable {
    private LpmlnProgram top;
    private Set<String> U;
    private WeightedAnswerSet x;
    private LpmlnProgram partialEvaluation;

    public PESolver(LpmlnProgram top, Set<String> U, WeightedAnswerSet x) {
        this.top = top;
        this.U = U;
        this.x = x;
    }

    public void run() {
        generatePartialEvaluation();
        solveProgram(partialEvaluation);
        combineAnswerSet();
    }

    // TODO: following function might not be correct!
    private void combineAnswerSet() {
        List<WeightedAnswerSet> result = new ArrayList<>();
        weightedAs.forEach(AS -> {
            WeightedAnswerSet answerSet = new WeightedAnswerSet();
            List<Integer> weights = new ArrayList<>();
            // 权重对应相加
            for (int i = 0; i < 2; i++) {
                weights.add(x.getWeights().get(i) + AS.getWeights().get(i));
            }
            answerSet.setWeights(weights);
            // 加入partial evaluation的SM的所有literal
            answerSet.getAnswerSet().getLiterals().addAll(AS.getAnswerSet().getLiterals());
            x.getAnswerSet().getLiterals().forEach(lit -> {
                if (!lit.equals("kse_solve_trick")) {
                    answerSet.getAnswerSet().add(lit);
                }
            });
            // 将新的回答集加入最终结果
            result.add(answerSet);
        });
        // 过滤并计算概率
//        weightedAs = calculateProbability(filtWas(result));
        weightedAs = calculateProbability(result);
    }

    private void generatePartialEvaluation() {
        // 1. 计算deleting set
        List<Rule> deletingSet = getDeletingSet();

        // 2. 计算partial evaluation
        List<Rule> peRules = new ArrayList<>();
        for (Rule rule: top.getRules()) {
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
        partialEvaluation = new LpmlnProgram(peRules, top.getFactor(), top.getHerbrandUniverse(), top.getMetarule());
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
        for (Rule rule: top.getRules()) {
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
                if (U.contains(nbs[1])) {
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
}
