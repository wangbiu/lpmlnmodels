package cn.edu.seu.kse.lpmln.experiment;

import cn.edu.seu.kse.lpmln.experiment.util.AnswerValidater;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2018/2/26
 */
public class LpmlnExperiment {
    protected LPMLNSolver baseSolver;
    protected LPMLNSolver augmentedSolver;
    public static final String PROGRAM_PATH = "./src/test/resources/experiment/lpmln/";

    public static final String SIMPLE_EXAMPLE = "asu_2asp_SimpleExample.lp";


    @Before
    public void init(){
        baseSolver = new LPMLNBaseSolver();
        augmentedSolver = new AugmentedSolver();
    }

    @Test
    public void testAll(){
        simpletest();
    }

    @Test
    public void simpletest(){
        test(SIMPLE_EXAMPLE);
    }

    public void test(String filename){
        File toTest = new File(PROGRAM_PATH+filename);
        baseSolver.solve(toTest);
        augmentedSolver.solve(toTest);
        AnswerValidater.isConsistent(baseSolver,augmentedSolver);
    }
}
