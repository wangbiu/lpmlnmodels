package cn.edu.seu.kse.anubis.lpmln.grounder;

import org.junit.Test;

import java.io.*;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class NaiveMontyHallGrounderTest {
    private NaiveMontyHallGrounder grounder=new NaiveMontyHallGrounder(7);

    @Test
    public void test(){
        System.out.println(grounder.ground());

    }

    @Test
    public void testBoxRule(){
        System.out.println(grounder.groundBoxRule());

    }

    @Test
    public void testGenTestCases() throws IOException {
        File outf=null;
        BufferedWriter bw =null;

        for(int i=3;i<=10;i++){
            outf=new File("m-"+i+".txt");
            bw=new BufferedWriter(new FileWriter(outf));
            grounder.setBoxNumber(i);
            bw.write(grounder.ground());
            bw.write(System.lineSeparator());
            bw.write("#show select/1.");
            bw.write(System.lineSeparator());
            bw.write("#show has_key/1.");
            bw.write(System.lineSeparator());
            bw.write("#show open/1.");
            bw.write(System.lineSeparator());
            bw.write("#show win_stay/0.");
            bw.write(System.lineSeparator());
            bw.write("#show switch/1.");
            bw.write(System.lineSeparator());
            bw.write("#show win_switch/0.");
            bw.close();
        }

    }
}
