package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.translator.impl.AugmentedSubsetTranslator;
import cn.edu.seu.kse.lpmln.translator.impl.LPMLN2ASPTranslator;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/22
 */
public class LPMLNTranslatorTest {
    public LPMLNTranslator translator;
    public LpmlnProgram program;
    public String filePath = "./src/test/resources/bird/b-3.txt";
    //下面这俩是翻译生成的文件
    public String out = "./src/test/resources/asp/out.lp";
    public String out17 = "./src/test/resources/asp/out-17.lp";
    public String monty40 = "./src/test/resources/benchmark/monty_hall/m-40.txt";

    @Before
    public void parse() throws IOException {
        //program = SyntaxModule.parseLPMLN(new File(filePath));
    }

    @Test
    public void effi() throws IOException {
        program = SyntaxModule.parseLPMLN(new File(monty40));
    }

    @Test
    public void testTranslate() throws Exception {
        translator = new LPMLN2ASPTranslator();
        String result = translator.translate(program);
        String toComp = FileHelper.getFileContent(new File(out));
        assert toComp.equals(result);
    }

    //增强子集翻译测试
    @Test
    public void testTranslateAug(){
        Set<Integer> sat = new HashSet<>();
        Set<Integer> unsat = new HashSet<>();
        sat.add(8);
        sat.add(13);
        unsat.add(12);
        translator = new AugmentedSubsetTranslator(sat,unsat);
        String result = translator.translate(program);
        String toComp = FileHelper.getFileContent(new File(out17));
        assert toComp.equals(result);
    }

    @After
    public void done(){
        FileHelper.cleanFiles();
        System.out.println("done.");
    }
}