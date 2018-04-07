package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway.SplittingSolver;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SplittingSolverTest {
    @Test
    public void test() throws IOException{
        LpmlnProgram program = SyntaxModule.parseLPMLN(new File("E:\\ML-KR\\LPMLN\\code\\lpmlnmodels\\src\\test\\resources\\bird\\b-3.txt"));

        SplittingSolver solver = new SplittingSolver();

        List<WeightedAnswerSet> as = solver.solveProgram(program);

        Iterator<WeightedAnswerSet> iter = as.iterator();
        int i = 1;
        while (iter.hasNext()) {
            System.out.println(i++);
            System.out.println(iter.next());
        }
//        as.forEach(System.out::println);
    }
}
