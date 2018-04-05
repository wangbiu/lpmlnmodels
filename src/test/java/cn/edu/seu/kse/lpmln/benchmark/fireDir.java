package cn.edu.seu.kse.lpmln.benchmark;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2018/4/5
 */
public class fireDir {
    private static final int startX = 3;
    private static final int endX = 8;
    private static final int startY = 3;
    private static final int endY = 8;
    private static final String basePath = "/lpmlnexperiment/benchmark/fireDir/";

    @Test
    public void exec(){
        File path = new File(basePath);
        path.mkdirs();
        for(int x=startX;x<endX;x++){
            for(int y=startY;y<endY;y++){
                exec(x,y);
            }
        }
    }

    private void exec(int x,int y){
        String filename = "fireDir_"+x+"_"+y+".lp";
        File towrite = new File(basePath+filename);
        FileHelper.writeFile(towrite,getprogram(x,y));
    }

    private String getprogram(int x,int y){
        StringBuilder program = new StringBuilder();
        program.append("fire(0,0).").append(System.lineSeparator());
        program.append(System.lineSeparator());
        for(int i=0;i<=x;i++){
            for(int j=0;j<=y;j++){
                if(i+1<=x){
                    program.append("0: ");
                    program.append("fire("+(i+1)+","+j+")");
                    program.append(":-");
                    program.append("fire("+i+","+j+").");
                    program.append(System.lineSeparator());
                }
                if(j+1<=y){
                    program.append("0: ");
                    program.append("fire("+i+","+(j+1)+")");
                    program.append(":-");
                    program.append("fire("+i+","+j+").");
                    program.append(System.lineSeparator());
                }
                program.append(System.lineSeparator());
            }
        }

        program.append("#show fire/2.");
        return program.toString();
    }
}
