package cn.edu.seu.kse.lpmln.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class TestDLVResultProcess {
    @Test
    public void testParseDLV() throws IOException {
        File f=new File("G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\dlvresult.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            result.append(line).append(System.lineSeparator());
        }
        SyntaxModule sm=new SyntaxModule();
        int pos=result.toString().indexOf(System.lineSeparator());
        String rawdata=result.toString().substring(pos);
//        System.out.println(rawdata);
        List<WeightedAnswerSet> was=sm.parseDLVResult(rawdata);

        System.out.println(was);

        br.close();
    }

    @Test
    public void testParseClingo() throws IOException {
        File f=new File("G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\clingoplainresult.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            result.append(line).append(System.lineSeparator());
        }
//        System.out.println(result.toString());
        SyntaxModule sm=new SyntaxModule();

        List<WeightedAnswerSet> was=sm.parseClingoResult(result.toString());

        System.out.println(was);

        br.close();
    }
}
