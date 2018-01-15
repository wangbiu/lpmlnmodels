package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public interface AspSolver {
    List<WeightedAnswerSet> solve(File file);
}
