package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * @author 许鸿翔
 * @date 2019/2/28
 */
public class LPMLN2MLNSolverTest {
    public String filePath = "./src/test/resources/bird/b-3.txt";
    private LPMLN2MLNSolver solver = new LPMLN2MLNSolver();
    private String mlnOutPath = "/lpmlnmodels/tmp/09a26fb4-8864-49c1-8ac5-42293c2b4e56.tmp";

    @Test
    public void beforeAlchemy() {
        solver.solve(new File(filePath));
    }

    @Test
    public void afterAlchemy(){
        String result = FileHelper.getFileContent(new File(mlnOutPath));
        Map<Integer,String> map = solver.getTranslator().getReverseMapping();
        map.put(6,"bird(1)");
        map.put(7,"bird(2)");
        map.put(8,"bird(3)");
        solver.processResult(result);
        System.out.println(solver.getMarginalDistribution());
        System.out.println("done");
    }
}