package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.Clingo4;

import java.util.List;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSubsetSolver extends Clingo4 implements Runnable {
    protected List<WeightedAnswerSet> weightedAnswerSets;
    protected String ruleFile = null;
    @Override
    public void run() {
        weightedAnswerSets = super.call(ruleFile);
    }

    public List<WeightedAnswerSet> getWeightedAnswerSets() {
        return weightedAnswerSets;
    }

    public void setWeightedAnswerSets(List<WeightedAnswerSet> weightedAnswerSets) {
        this.weightedAnswerSets = weightedAnswerSets;
    }

    public String getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(String ruleFile) {
        this.ruleFile = ruleFile;
    }
}
