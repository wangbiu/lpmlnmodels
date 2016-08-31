package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.SyntaxMoudle;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class TestDLVResultProcess {
    @Test
    public void testParse() throws IOException {
        File f=new File("G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\dlvresult.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            result.append(line).append(System.lineSeparator());
        }
        SyntaxMoudle sm=new SyntaxMoudle();
        int pos=result.toString().indexOf(System.lineSeparator());
        String rawdata=result.toString().substring(pos);
//        System.out.println(rawdata);
        List<WeightedAnswerSet> was=sm.parse(rawdata);

        System.out.println(was);

        br.close();
    }
}
