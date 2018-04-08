package cn.edu.seu.kse.lpmln.benchmark;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;
import java.util.Random;

/**
 * @author 许鸿翔
 * @date 2018/4/6
 */
public class maxclique {
    private static final int end=200;
    private static final int start=10;
    private static final String basePath = "/lpmlnexperiment/benchmark/maxclique/";
    private static final Random rand = new Random();
    private static final double density = 0.5;

    @Test
    public void exec(){
        File path = new File(basePath);
        path.mkdirs();
        for(int x=start;x<=end;x++){
            exec(x);
        }
    }

    private void exec(int x){
        String filename = "maxclique_"+x+".lp";
        File towrite = new File(basePath+filename);
        FileHelper.writeFile(towrite,getprogram(x));
    }

    private String getprogram(int x){
        StringBuilder program = new StringBuilder();
        for(int i=1;i<=x;i++){
            program.append("1: "+node(i)+".").append(System.lineSeparator());
        }
        program.append(System.lineSeparator());

        for(int i=1;i<=x;i++){
            for(int j=i+1;j<=x;j++){
                if(rand.nextInt()%1000<1000*density){
                    program.append(edge(i,j)+".").append(System.lineSeparator());
                    program.append(edge(j,i)+".").append(System.lineSeparator());
                }
            }
        }

        program.append(System.lineSeparator());

        for(int i=1;i<=x;i++) {
            for (int j = i + 1; j <= x; j++) {
                program.append(":- ").append(node(i)+","+node(j)+", not "+edge(i,j)+".").append(System.lineSeparator());
            }
        }

        program.append(System.lineSeparator());

        program.append("#show node/1.");

        return program.toString();
    }

    private String node(int x){
        return "node("+x+")";
    }

    private String edge(int x,int y){
        return "edge("+x+","+y+")";
    }
}
