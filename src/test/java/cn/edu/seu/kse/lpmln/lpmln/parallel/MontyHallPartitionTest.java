package cn.edu.seu.kse.lpmln.lpmln.parallel;

import cn.edu.seu.kse.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.translator.ASPGround4ParallelTranslator;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import cn.edu.seu.kse.lpmln.solver.parallel.BirdPartition;
import cn.edu.seu.kse.lpmln.solver.parallel.MontyHallPartition;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/25.
 */
public class MontyHallPartitionTest {
    private String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\bird\\b-3.txt";
    private File rulef=new File(path);
    private int factor=0;
    private List<Rule> rules=null;
    private HashSet<String> herbrandUniverse=null;
    private String asptext=null;

    @Before
    public void parse() throws IOException {
        SyntaxModule sm=new SyntaxModule();
        rules= sm.parseLPMLN(rulef);
        factor=sm.getFactor();
        herbrandUniverse=sm.getHerbrandUniverse();
        System.out.println("factor "+factor);
        ASPGround4ParallelTranslator translator=new ASPGround4ParallelTranslator();
        translator.setWeakTranslate(true);
        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        translator.setMetarule(sm.getMetarule());
        asptext=translator.translate(rules);
    }


    @Test
    public void test() throws IOException {
        System.out.println(rules.size());
        MontyHallPartition partition=new MontyHallPartition(rules,asptext,factor);
        partition.setProblemN(3);
        partition.partition(6);
//        System.out.println(asptext);
        List<AugmentedSubset> split=partition.getSplit();
        List<File> splits=partition.genSplitFiles();
//        System.out.println(splits);

        System.out.println(split);
    }

    @Test
    public void testBirdPartition() throws IOException {
        System.out.println(rules.size());
        BirdPartition partition=new BirdPartition(rules,asptext,factor);
        partition.setWeakPartition(true);
        partition.partition(9);
//        System.out.println(asptext);
        List<AugmentedSubset> split=partition.getSplit();
//        List<File> splits=partition.genSplitFiles();
//        System.out.println(splits);

        System.out.println(split);
    }
}
