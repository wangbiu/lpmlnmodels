package cn.edu.seu.kse.anubis.lpmln.splitting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 王彬 on 2017/9/10.
 */
public class BirdsTestCaseGen {
    /**
     * 鸟类问题的分割集求解程序生成器
     * 分割集 U = {residentBird(1..n)}
     * n 为鸟个数的 1/3
     * bottom part :
     *              2 : residentBird(1).
     *
     * top part ：
     *              bird(1) :- residentBird(1).
     *              1 : migratoryBird(1).
     *              bird(1) :- migratoryBird(1).
     *              :- migratoryBird(1), residentBird(1).
     *              #show migratoryBird/1.
     *              #show residentBird/1.
     *              #show bird/1.
     */

    private String basedir="G:\\expriment\\splitting-bird";


    // 生成bottom part
    public void generateBottomPart(int birdNum){
        int botBirdNum=birdNum/3;
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=botBirdNum;i++){
            sb.append("2 : residentBird(").append(i).append(").").append(System.lineSeparator());
//            sb.append("1 : migratoryBird(").append(i).append(").").append(System.lineSeparator());
        }
        sb.append("#show  residentBird/1.").append(System.lineSeparator());
        System.out.println(sb.toString());
        writeFile(basedir+"\\bird"+birdNum+"\\bot.txt",sb.toString());
    }

    public String genPartialEvalPart(int bird, int cases){
        StringBuilder sb=new StringBuilder();
        if(cases == 0){
            sb.append("bird(").append(bird).append(") :- migratoryBird("+bird+").").append(System.lineSeparator());
            sb.append("1 : migratoryBird("+bird+").").append(System.lineSeparator());
        }else  if(cases ==1){
            sb.append("bird(").append(bird).append(") :- migratoryBird("+bird+").").append(System.lineSeparator());
            sb.append(" :- migratoryBird("+bird+").").append(System.lineSeparator());
            sb.append("1 : migratoryBird("+bird+").").append(System.lineSeparator());
            sb.append("bird("+bird+").").append(System.lineSeparator());
        }

        return sb.toString();
    }

    public boolean checkStatus(int[] status){
        int len=status.length;
        boolean isstop=true;
        for(int i=0;i<len;i++){
            if(status[i] != 1){
                isstop=false;
                break;
            }
        }
        return isstop;
    }

    public void statusAddOne(int[] status){
        int len=status.length-1;
        for(int i=len;i>=0;i--){
            status[i]+=1;
            if(status[i] == 2){
                status[i] = 0;
                continue;
            }else {
                break;
            }
        }
    }

    // 生成partial evaluation 结果
    public void generatePartialEvaluationResults(int birdNum){
        int botBirdNum=birdNum/3;
        int [] status=new int[botBirdNum];
        StringBuilder sb=null;
        int cnt=0;
        boolean isstop=false;

        while (!isstop){
            if(checkStatus(status)){
                isstop=true;
            }
            sb=new StringBuilder();
            for(int i=0;i<botBirdNum;i++){
                sb.append(genPartialEvalPart(i,status[i]));
            }

            for(int j=botBirdNum+1;j<=birdNum;j++){
                sb.append("2 : residentBird(").append(j).append(").").append(System.lineSeparator());
                sb.append("1 : migratoryBird(").append(j).append(").").append(System.lineSeparator());
                sb.append("bird("+j+") :- residentBird("+j+").").append(System.lineSeparator());
                sb.append("bird("+j+") :- migratoryBird("+j+").").append(System.lineSeparator());
                sb.append(":- migratoryBird("+j+"), residentBird("+j+").").append(System.lineSeparator());
            }
            sb.append("#show bird/1.").append(System.lineSeparator());

            writeFile(basedir+"\\bird"+birdNum+"\\pe-"+cnt+".txt",sb.toString());
            cnt++;
            statusAddOne(status);

        }


    }



    public void writeFile(String filepath, String content){
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(new File(filepath)));
            bw.write(content);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateAll(){
        for(int i=10;i<=14;i++){
            generateBottomPart(i);
            generatePartialEvaluationResults(i);
        }
    }

    public void generateTranslationCmd(int birdNum){
        int botBirdNum=birdNum/3;
        int penum= (int) Math.pow(2,botBirdNum);

        StringBuilder sb=new StringBuilder();

        sb.append("java -jar ../lpmlnmodels-1.1.jar -t  -r clingo -s weak -i bot.txt -o bot-trans.txt\n");
        for(int i=0;i<penum;i++){
            sb.append("java -jar ../lpmlnmodels-1.1.jar -t  -r clingo -s weak -i pe-"+i+".txt -o pe-trans-"+i+".txt\n");
        }
        writeFile(basedir+"\\translate-bird-"+birdNum+".sh",sb.toString());

    }


}
