package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.grounder.GringoGrounder;
import cn.edu.seu.kse.lpmln.grounder.LPMLNGrounder;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2019/3/27
 */
public class LPMLNCDNLSolverTest {
    private String filePath = "./src/test/resources/bird/b-3.txt";

    private LPMLNCDNLSolver solver = new LPMLNCDNLSolver();

    @Test
    public void getSingleAs() {
        filePath = "test.lp";
        LPMLNGrounder grounder = new GringoGrounder();
        String groundProgramStr = grounder.grounding(new File(filePath));
        File groundFile = FileHelper.randomFile();
        FileHelper.writeFile(groundFile,groundProgramStr);
        LpmlnProgram groundProgram = solver.parse(groundFile);
        solver.solveProgram(groundProgram);
        System.out.println(solver.getAllWeightedAs());
    }
}