package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSubsetPartitioner {
    protected String policy = SPLIT_RANDOM;
    public static final String SPLIT_RANDOM="RANDOM";
    protected AugmentedSolver solver;

    public AugmentedSubsetPartitioner(AugmentedSolver solver){
        this.solver = solver;
    }

    public List<List<Rule>> partition(List<Rule> originSet){
        switch (policy){
            case SPLIT_RANDOM:
            default:
                return randomPartition(originSet);
        }
    }

    public List<List<Rule>> randomPartition(List<Rule> originSet){
        List<List<Rule>> subsets = new ArrayList<>();
        return subsets;
    }
}
