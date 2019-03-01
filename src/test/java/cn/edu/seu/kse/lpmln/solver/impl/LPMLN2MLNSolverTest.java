package cn.edu.seu.kse.lpmln.solver.impl;

import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2019/2/28
 */
public class LPMLN2MLNSolverTest {
    public String filePath = "./src/test/resources/bird/b-3.txt";
    private LPMLN2MLNSolver solver = new LPMLN2MLNSolver();

    @Test
    public void beforeAlchemy() {
        solver.solve(new File(filePath));
    }

    @Test
    public void afterAlchemy(){

    }
}