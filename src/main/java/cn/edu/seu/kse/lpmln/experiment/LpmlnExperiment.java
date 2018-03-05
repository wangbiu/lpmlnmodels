package cn.edu.seu.kse.lpmln.experiment;

import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.experiment.util.AnswerValidater;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2018/2/26
 */
public class LpmlnExperiment {
    protected LPMLNSolver baseSolver;
    protected LPMLNSolver augmentedSolver;
    public static final String PROGRAM_PATH = "/lpmlnmodels/experiment/lpmln/";

    public static final String SIMPLE_EXAMPLE = "asu_2asp_SimpleExample.lp";

    public LpmlnExperiment(){
        baseSolver = new LPMLNBaseSolver();
        augmentedSolver = new AugmentedSolver();
    }

    public void testAll(){
        simpletest();
    }

    public void simpletest(){
        test(SIMPLE_EXAMPLE);
    }

    public void test(String filename){
        File toTest = new File(PROGRAM_PATH+filename);
        baseSolver.solve(toTest);
        augmentedSolver.solve(toTest);
        if(AnswerValidater.isConsistent(baseSolver,augmentedSolver)){
            System.out.println("Test "+filename+" passed.");
        }else{
            System.out.println("Test "+filename+" failed!!!!!!!!!!!");
        }
    }
}
