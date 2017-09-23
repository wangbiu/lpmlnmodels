package cn.edu.seu.kse.lpmln.lpmln.parallel;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.translator.ASPTranslator;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/19.
 */
public class ASPStochasticPartitionTest {
    private String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\friend\\f-10.txt";
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
        ASPTranslator translator=new ASPTranslator();
        translator.setWeakTranslate(true);
        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        asptext=translator.translate(rules);
    }


//    @Test
//    public void test() throws IOException {
//        System.out.println(rules.size());
//        ASPStochasticPartition partition=new ASPStochasticPartition(rules,asptext,factor);
//        partition.partition(5);
////        System.out.println(asptext);
//        List<AugmentedSubset> split=partition.getSplit();
//        List<File> splits=partition.genSplitFiles();
//        System.out.println(splits);
//
//        System.out.println(split);
//    }

}
