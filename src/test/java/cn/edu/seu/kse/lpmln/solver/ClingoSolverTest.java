package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.ClingoSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/22
 */
public class ClingoSolverTest {
    public AspSolver solver = new ClingoSolver();
    public String filePath = "./src/test/resources/asp/out.lp";

    @Test
    public void testSolve() throws Exception {
        File program = new File(filePath);
        List<WeightedAnswerSet> result = solver.solve(program);
        assert result.size()==27;
    }

    @Test
    public void testSolve1() throws Exception {
        String content = FileHelper.getFileContent(new File(filePath));
        List<WeightedAnswerSet> result = solver.solve(content);
        assert result.size()==27;
    }

    @After
    public void done(){
        FileHelper.cleanFiles();
        System.out.println("done.");
    }
}