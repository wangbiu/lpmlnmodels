package cn.edu.seu.kse.lpmln.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class ASPTranslatorTest {
    private String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\bird.txt";
    private File rulef=new File(path);
    private int factor=0;
    private List<Rule> rules=null;
    private HashSet<String> herbrandUniverse=null;

    @Before
    public void parse() throws IOException {
        SyntaxModule sm=new SyntaxModule();
        rules= sm.parseLPMLN(rulef);
        factor=sm.getFactor();
        herbrandUniverse=sm.getHerbrandUniverse();
        System.out.println("factor "+factor);
    }

    //TODO:重写下test
//    @Test
//    public void testTranslate() {
//        Date start =new Date();
//        ASPTranslator translator=new ASPTranslator();
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate_parts(rules);
//        System.out.println(asprules);
//        Date end=new Date();
//        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");
//    }
//
//    @Test
//    public void testDLVTranslate() {
//        Date start =new Date();
//        DLVTranslator translator=new DLVTranslator();
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate(rules);
//        System.out.println(asprules);
//        Date end=new Date();
//        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");
//    }

//    @Test
//    public void testPlogTranslator() {
//        PlogTranslator translator=new PlogTranslator();
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate(rules);
//        System.out.println(asprules);
//    }

//    @Test
//    public void testPlogCRRuleTranslator(){
//        PlogCRRuleTranslator translator=new PlogCRRuleTranslator();
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate(rules);
//        System.out.println(asprules);
//    }

//    @Test
//    public void testWeakASPTranslator(){
//        Date start =new Date();
//        ASPTranslator translator=new ASPTranslator("weak");
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate_parts(rules);
//        System.out.println(asprules);
//        Date end=new Date();
//        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");
//    }
//
//    @Test
//    public void testWeakDLVTranslator(){
//        Date start =new Date();
//        DLVTranslator translator=new DLVTranslator("weak");
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate(rules);
//        System.out.println(asprules);
//        Date end=new Date();
//        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");
//    }

//    @Test
//    public void testASPGroundTranslator(){
//        ASPGroundTranslator translator=new ASPGroundTranslator();
//        translator.setFactor(factor);
//        translator.setHerbrandUniverse(herbrandUniverse);
//        String asprules=translator.translate(rules);
//        System.out.println(asprules);
//    }
}
