package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.Clingo4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSolver extends Clingo4 {
    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        List<WeightedAnswerSet> finalWas = new ArrayList<>();
        return finalWas;
    }
}
