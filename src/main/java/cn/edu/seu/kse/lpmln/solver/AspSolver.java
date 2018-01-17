package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public interface AspSolver {
    /**
     * asp推理接口
     * @param file asp程序文件
     * @return asp推理结果
     */
    List<WeightedAnswerSet> solve(File file);

    /**
     * asp推理接口
     * @param aspProgram asp程序
     * @return asp推理结果
     */
    List<WeightedAnswerSet> solve(String aspProgram);
}
