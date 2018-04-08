package cn.edu.seu.kse.lpmln.benchmark;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;
import java.util.Random;

/**
 * @author 许鸿翔
 * @date 2018/4/5
 */
public class tsp {
    private static final int count=40;
    private static final String basePath = "/lpmlnexperiment/benchmark/tsp/";
    private static final Random rand = new Random();
    private static final double k=0.6;

    @Test
    public void exec(){
        File path = new File(basePath);
        path.mkdirs();
        for(int x=2;x<=count;x++){
            exec(x);
        }
    }

    private void exec(int x){
        String filename = "tsp_"+x+".lp";
        File towrite = new File(basePath+filename);
        FileHelper.writeFile(towrite,getprogram(x));
    }

    private String getprogram(int x){
        StringBuilder program = new StringBuilder();
        program.append(":- not back.").append(System.lineSeparator());
        program.append("visited(1).").append(System.lineSeparator());

        for(int i=2;i<=x;i++){
            program.append(":- not visited("+i+").").append(System.lineSeparator());
        }

        for(int i=2;i<=x;i++){
            program.append("back :- visited("+i+"),"+go(i,1)+".").append(System.lineSeparator());
        }

        for(int i=1;i<=x;i++){
            for(int j=i+1;j<=x;j++){
                if(i!=j){
                    if(Math.abs(rand.nextInt())%1000<1000*k||j==i+1||(j==1&&i==x)){
                        int length = Math.abs(rand.nextInt()%20)+1;
                        program.append("-"+length+": "+go(i,j)+".").append(System.lineSeparator());
                        program.append("-"+length+": "+go(j,i)+".").append(System.lineSeparator());
                    }
                }
            }
        }

        for(int i=1;i<=x;i++){
            for(int j=1;j<=x;j++){
                if(i!=j){
                    program.append("visited("+j+"):-visited("+i+"),"+go(i,j)+".").append(System.lineSeparator());
                }
            }
        }

        for(int i=1;i<=x;i++){
            for(int j=1;j<=x;j++){
                for(int k=1;k<=x;k++){
                    if(j!=k&&j!=i&&k!=i){
                        program.append(":- "+go(i,j)+","+go(i,k)+".").append(System.lineSeparator());
                    }
                }
            }
        }

        program.append("#show go/2.").append(System.lineSeparator());
        return program.toString();
    }

    private String go(int x,int y){
        return "go("+x+","+y+")";
    }
}
