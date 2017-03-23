package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.syntax.SyntaxModule;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPGround4ParallelTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPGroundTranslator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ParallelSolverTest {
    private String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\monty_hall\\m-3.txt";
    private File rulef=new File(path);
    private int factor=0;
    private List<Rule> rules=null;
    private HashSet<String> herbrandUniverse=null;
    private String asptext=null;
    private String metarule=null;

    @Before
    public void parse() throws IOException {
        SyntaxModule sm=new SyntaxModule();
        rules= sm.parse(rulef);
        factor=sm.getFactor();
        metarule=sm.getMetarule();
        herbrandUniverse=sm.getHerbrandUniverse();
        System.out.println("factor "+factor);
        ASPGround4ParallelTranslator translator=new ASPGround4ParallelTranslator();
        translator.setWeakTranslate(true);
        translator.setMetarule(metarule);
        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        asptext=translator.translate(rules);
    }

    @Test
    public void test() throws IOException, InterruptedException {
//        System.out.println(asptext);
        ParallelSolver solver=new ParallelSolver(rules,asptext,3,factor);
        solver.call();
        Thread.sleep(3000);
        System.out.println("most probable answer set");
        System.out.println(solver.findMaxWeightedAs());
        System.out.println("marginal probability");
        System.out.println(solver.marginalDistribution(factor));

    }
}
