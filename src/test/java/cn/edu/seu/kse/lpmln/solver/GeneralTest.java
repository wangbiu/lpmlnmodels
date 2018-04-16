package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNHybridSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSubsetPartitioner;
import cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway.SplittingSolver;
import org.junit.Before;

import java.util.ArrayList;

/**
 * @author 许鸿翔
 * @date 2018/4/16
 */
public class GeneralTest {
    public String[] testfiles = {"./src/test/resources/benchmark/bird/b-3.txt",
            "./src/test/resources/benchmark/fire/fire_3_3.lp",
            "./src/test/resources/benchmark/fireDir/fireDir_2_2.lp",
            "./src/test/resources/benchmark/hat/hat_5.lp",
            "./src/test/resources/benchmark/maxclique/maxclique_10.lp",
            "./src/test/resources/benchmark/monty_hall/m-4.txt",
            "./src/test/resources/benchmark/bird/b-3.txt"};
    public ArrayList<LPMLNSolver> toCheck = new ArrayList<>();
    public LPMLNSolver base = new LPMLNBaseSolver();

    @Before
    public void initSolvers(){
        toCheck.add(asRandom());
        toCheck.add(asHeuristic());
        toCheck.add(spBot());
        toCheck.add(spLit());
        toCheck.add(hybridISA());
        toCheck.add(hybridIA());
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

    public LPMLNSolver hybridISA(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("ISA");
        return solver;
    }

    public LPMLNSolver hybridIA(){
        LPMLNHybridSolver solver = new LPMLNHybridSolver("IA");
        return solver;
    }

}
