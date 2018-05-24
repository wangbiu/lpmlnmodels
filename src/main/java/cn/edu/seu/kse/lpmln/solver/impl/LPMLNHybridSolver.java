package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;

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

    @Override
    public void executeSolving(){
        //System.out.println("2:"+System.currentTimeMillis());
        LPMLNSolver solver = chooseSolver(arch,lpmlnProgram);
        solver.setCalculatePossibility(false);
        solver.setFiltResult(false);
        weightedAs = solver.solveProgram(lpmlnProgram);
    }
}
