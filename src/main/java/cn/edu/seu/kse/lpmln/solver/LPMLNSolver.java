package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public interface LPMLNSolver extends Runnable {
    //TODO:写一个solve接口，只solve不操作
    /**
     * 求解程序
     * @param ruleFile 输入LPMLN程序文件
     * @return 所有回答集
     */
    List<WeightedAnswerSet> solve(File ruleFile);

    /**
     * 求解程序
     * @param program LPMLN程序
     * @return 所有回答集
     */
    List<WeightedAnswerSet> solve(String program);

    /**
     * 直接求解翻译后的
     * @param translatedFile 翻译后的文件
     * @return 返回结果
     */
    List<WeightedAnswerSet> solveTranslated(File translatedFile);

    /**
     * 直接求解构建后的程序
     * @param program
     * @return
     */
    List<WeightedAnswerSet> solveProgram(LpmlnProgram program);

    /**
     * 判断文字是否在可能性最大的回答集内
     * @param literal 文字
     * @return 判断结果true=在，false=不在
     */
    boolean containsLiteral(String literal);

    /**
     * 获取文字成立的可能性
     * @param literal 文字
     * @return 文字成立的可能性
     */
    double getLiteralProbability(String literal);

    /**
     * 获取权重最大的回答集
     * @return 权重最大的回答集，可能多个回答集权重相同
     */
    List<WeightedAnswerSet> findMaxWeightedAs();

    /**
     * 获得所有回答集
     * @return 所有回答集
     */
    List<WeightedAnswerSet> getAllWeightedAs();

    /**
     * 获得边缘分布
     * @return 边缘分布
     */
    String getMarginalDistribution();

    /**
     * for thread pool
     * @param lpmlnProgram to solve
     */
    void setLpmlnProgram(LpmlnProgram lpmlnProgram);

}
