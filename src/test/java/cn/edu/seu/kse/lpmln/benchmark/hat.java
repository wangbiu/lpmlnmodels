package cn.edu.seu.kse.lpmln.benchmark;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2018/4/5
 */
public class hat {
    private static final int count=100;
    private static final String basePath = "/lpmlnexperiment/benchmark/hat/";

    @Test
    public void exec(){
        File path = new File(basePath);
        path.mkdirs();
        for(int x=2;x<=count;x++){
            exec(x);
        }
    }

    private void exec(int x){
        String filename = "hat_"+x+".lp";
        File towrite = new File(basePath+filename);
        FileHelper.writeFile(towrite,getprogram(x));
    }

    private String getprogram(int x){
        StringBuilder program = new StringBuilder();
        program.append("red|black.").append(System.lineSeparator());
        program.append(":- red,black.").append(System.lineSeparator());
        program.append("red(1).").append(System.lineSeparator());

        for(int i=1;i<=x;i++){
            program.append("red("+i+")|black("+i+").").append(System.lineSeparator());
            program.append(":-red("+i+"),black("+i+").").append(System.lineSeparator());
        }
        program.append("black:-red(1)");
        for(int i=2;i<=x;i++){
            program.append(",red("+i+")");
        }
        program.append(".").append(System.lineSeparator());

        for(int i=1;i<=x;i++){
            program.append("10: :-red");
            for(int j=1;j<=x;j++){
                if(i!=j){
                    program.append(",red("+j+")");
                }
            }
            program.append(".").append(System.lineSeparator());
        }

        program.append("#show red/0.").append(System.lineSeparator());
        program.append("#show black/0.").append(System.lineSeparator());
        return program.toString();
    }
}
