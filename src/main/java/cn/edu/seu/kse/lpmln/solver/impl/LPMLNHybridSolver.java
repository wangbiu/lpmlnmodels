package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;

import java.util.List;

public class LPMLNHybridSolver extends LPMLNBaseSolver{
    private String arch;

    /**
     * 混合lpmln求解器。
     *
     * @param arch: 求解器从顶到底的结构，两个可选：{ISA} or {SIA}，分别
     *            表示 independent-splitting-augmented, splitting-
     *            independent-augmented。
     */
    public LPMLNHybridSolver(String arch) {
        this.arch = arch;
    }

    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program) {
        lpmlnProgram = program;
        LPMLNSolver solver = chooseSolver(arch);
        List<WeightedAnswerSet> result = solver.solveProgram(program);
        weightedAs = calculateProbability(filtWas(result));
        return weightedAs;
    }
}
