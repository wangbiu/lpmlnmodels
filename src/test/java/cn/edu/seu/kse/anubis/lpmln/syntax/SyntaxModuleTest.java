package cn.edu.seu.kse.anubis.lpmln.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class SyntaxModuleTest {

    @Test
    public void testParse() throws IOException {
        String path="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\bird.txt";
        File rulef=new File(path);
        SyntaxModule sm=new SyntaxModule();
        List<Rule> rules= sm.parse(rulef);

        System.out.println("");
        for(Rule r:rules){
            System.out.println(r);
        }
    }

}
