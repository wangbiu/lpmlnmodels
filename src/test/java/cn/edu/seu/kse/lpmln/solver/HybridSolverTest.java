package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNHybridSolver;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class HybridSolverTest {
    @Test
    public void test() throws IOException{
        LpmlnProgram program = SyntaxModule.parseLPMLN(new File("D:\\ideaSpace\\lpmlnmodels\\src\\test\\resources\\bird\\b-3.txt"));

        LPMLNSolver solver = new LPMLNHybridSolver("ISA");

        List<WeightedAnswerSet> as = solver.solveProgram(program);

        Iterator<WeightedAnswerSet> iter = as.iterator();
        int i = 1;
        while (iter.hasNext()) {
            System.out.println(i++);
            System.out.println(iter.next());
        }
    }
}
