package cn.edu.seu.kse.anubis.lpmln.grounder;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 王彬 on 2017/3/27.
 */
public class BirdGrounderTest {
    BirdGrounder grounder=new BirdGrounder(3);

    @Test
    public void test(){
        System.out.println(grounder.ground());
    }

    @Test
    public void testGenTestCases() throws IOException {
        File outf=null;
        BufferedWriter bw =null;

        for(int i=3;i<=20;i++){
            outf=new File("b-"+i+".txt");
            bw=new BufferedWriter(new FileWriter(outf));
            grounder.setVarNumber(i);
            bw.write(grounder.ground());
            bw.write(System.lineSeparator());
            bw.write("#show bird/1.");
            bw.write(System.lineSeparator());
            bw.close();
        }

    }
}
