package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.solver.parallel.augmentedSubsetWay.AugmentedSolver;
import org.junit.Before;

/**
 * @author 许鸿翔
 * @date 2018/1/23
 */
public class AugmentedSolverTest extends LPMLNBaseSolverTest {
    @Before
    public void init(){
        solver = new AugmentedSolver();
    }
}