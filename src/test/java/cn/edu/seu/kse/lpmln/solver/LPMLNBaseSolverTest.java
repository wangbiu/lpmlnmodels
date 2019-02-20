package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.grounder.GringoGrounder;
import cn.edu.seu.kse.lpmln.grounder.LPMLNGrounder;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/22
 */
public class LPMLNBaseSolverTest {
    public String birdPath = "./src/test/resources/bird/b-3.txt";
    public String outPath = "./src/test/resources/asp/out.lp";
    public LPMLNSolver solver;
    public String marginalResult = "bird(3)  0.9099694268296192 " +
            "bird(2)  0.9099694268296192 " +
            "bird(1)  0.9099694268296193 ";

    @Before
    public void init(){
        solver = new LPMLNBaseSolver();
    }

    @Test
    public void testSolve() throws Exception {
        LPMLNGrounder grounder = new GringoGrounder();
        String groundProgram = grounder.grounding(new File(birdPath));
        List<WeightedAnswerSet> was = solver.solve(groundProgram);
        String marginal = solver.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ");
        String result[] = marginal.split(" ");
        solver.getAllWeightedAs().forEach(System.out::println);
        assert result[1].startsWith("0.909");
        assert result[3].startsWith("0.909");
        assert result[5].startsWith("0.909");
    }

    @Test
    public void testSolveTranslated() throws Exception {
        List<WeightedAnswerSet> was = solver.solveTranslated(new File(outPath));
        String marginal = solver.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ");
        String result[] = marginal.split(" ");
        assert result[1].startsWith("0.909");
        assert result[3].startsWith("0.909");
        assert result[5].startsWith("0.909");
    }

    @After
    public void done(){
        FileHelper.cleanFiles();
        System.out.println("done.");
    }
}