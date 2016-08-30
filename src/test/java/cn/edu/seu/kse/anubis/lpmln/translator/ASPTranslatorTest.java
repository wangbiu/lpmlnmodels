package cn.edu.seu.kse.anubis.lpmln.translator;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.syntax.SyntaxModule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class ASPTranslatorTest {

    @Test
    public void testTranslate() throws IOException {
        String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\friend.txt";
        File rulef=new File(path);
        SyntaxModule sm=new SyntaxModule();
        List<Rule> rules= sm.parse(rulef);
        ASPTranslator translator=new ASPTranslator();
        translator.setFactor(sm.getFactor());
        translator.setHerbrandUniverse(sm.getHerbrandUniverse());
        String asprules=translator.translate(rules);
        System.out.println(asprules);
    }
}
