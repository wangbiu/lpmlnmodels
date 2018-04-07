package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway.PESolver;
import cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway.Splitter;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PESolverTest {
    @Test
    public void test() throws IOException{
        LpmlnProgram program = SyntaxModule.parseLPMLN(new File("E:\\ML-KR\\LPMLN\\code\\lpmlnmodels\\src\\test\\resources\\bird\\b-3.txt"));
        Splitter splitter = new Splitter();
        splitter.split(program, 0.5);
        LpmlnProgram bottom = splitter.getBottom();
        LpmlnProgram top = splitter.getTop();
        Set<String> U = splitter.getU();
        LPMLNBaseSolver s1 = new LPMLNBaseSolver();
        WeightedAnswerSet x = s1.solveProgram(bottom).get(0);
        PESolver solver = new PESolver(top, U, x);
        solver.run();
        solver.getAllWeightedAs().forEach(System.out::println);
    }
}
