package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.lpmln.SolverValidator;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNHybridSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSubsetPartitioner;
import cn.edu.seu.kse.lpmln.solver.parallel.independentway.IndependentSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway.SplittingSolver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/4/16
 */
public class GeneralTest {
    public String[] filePaths = {
            "./src/test/resources/benchmark/bird/b-3.txt",
            "./src/test/resources/benchmark/fire/fire_3_3.lp",
            "./src/test/resources/benchmark/fireDir/fireDir_2_2.lp",
            "./src/test/resources/benchmark/hat/hat_5.lp",
            "./src/test/resources/benchmark/maxclique/maxclique_4.lp",
            "./src/test/resources/benchmark/monty_hall/m-4.txt",
            "./src/test/resources/benchmark/tsp/tsp_3.lp"
    };
    public List<LPMLNSolver> toCheck = new ArrayList<>();
    public List<String> testFilePaths;
    public LPMLNSolver base = new LPMLNBaseSolver();

    @Before
    public void initSolvers(){
        LPMLNApp.semantics = "weak";
        testFilePaths = Arrays.asList(filePaths);
        toCheck.add(asSimple());
        toCheck.add(asRandom());
        toCheck.add(asHeuristic());
//        toCheck.add(spBot());
//        toCheck.add(spLit());
        toCheck.add(spDyn());
        toCheck.add(ind());
        toCheck.add(hybridISA());
        toCheck.add(hybridIA());
        toCheck.add(hybridAS());
        toCheck.add(hybridAI());
    }

    @Test
    public void generatTest(){
        toCheck.forEach(lpmlnSolver -> {
            System.out.println("solver: " + lpmlnSolver.getClass().getSimpleName());
            SolverValidator solverValidator = new SolverValidator(lpmlnSolver);
            testFilePaths.forEach(paths->{
                System.out.println("test file: " + paths);
                solverValidator.validate(paths);
            });
        });
    }

    public LPMLNSolver asSimple(){
        AugmentedSolver solver = new AugmentedSolver();
        solver.setPolicy(AugmentedSubsetPartitioner.SPLIT_TYPE.DIVIDE_SIMPLE);
        return solver;
    }

    public LPMLNSolver asRandom(){
        AugmentedSolver solver = new AugmentedSolver();
        solver.setPolicy(AugmentedSubsetPartitioner.SPLIT_TYPE.DIVIDE_RANDOM);
        return solver;
    }

    public LPMLNSolver asHeuristic(){
        AugmentedSolver solver = new AugmentedSolver();
        solver.setPolicy(AugmentedSubsetPartitioner.SPLIT_TYPE.DIVIDE_HEURISTIC);
        return solver;
    }

    public LPMLNSolver spLit(){
        SplittingSolver solver = new SplittingSolver();
        solver.setPolicy(SplittingSolver.SPLIT_TYPE.SPLIT_LIT);
        return solver;
    }

    public LPMLNSolver spBot(){
        SplittingSolver solver = new SplittingSolver();
        solver.setPolicy(SplittingSolver.SPLIT_TYPE.SPLIT_BOT);
        return solver;
    }

    public LPMLNSolver spDyn(){
        SplittingSolver solver = new SplittingSolver();
        solver.setPolicy(SplittingSolver.SPLIT_TYPE.SPLIT_DYNAMIC);
        return solver;
    }

    public LPMLNSolver ind(){
        IndependentSolver solver = new IndependentSolver();
        return solver;
    }

    public LPMLNSolver hybridISA(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("ISA");
        return solver;
    }

    public LPMLNSolver hybridIA(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("IA");
        return solver;
    }

    public LPMLNSolver hybridAS(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("AS");
        return solver;
    }

    public LPMLNSolver hybridAI(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("AI");
        return solver;
    }
}
