package cn.edu.seu.kse.anubis.lpmln.splitting;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 王彬 on 2017/9/10.
 */
public class BirdTestCaseGenTest {
    private BirdsTestCaseGen btc=new BirdsTestCaseGen();
    private int birdNum=10;

    @Test
    public void  testgenerateBottomPart(){
        btc.generateBottomPart(birdNum);
    }

    @Test
    public void testStatusAddOne(){
        int[] status=new int[4];
        int cnt=1;

        while (!btc.checkStatus(status)){
            btc.statusAddOne(status);
            System.out.println(Arrays.toString(status));
            cnt++;
        }
        System.out.println(cnt);
    }

    @Test
    public void testGeneratePartialEvaluation(){
        btc.generatePartialEvaluationResults(birdNum);
    }

    @Test
    public void testGenerateAll(){
        btc.generateAll();
    }

    @Test
    public void testgenerateTranslationCmd(){
        for(int b=10;b<=14;b++){
            btc.generateTranslationCmd(b);
        }
    }

    @Test
    public void testGenerateIndependentlyDivisibleProgram(){
        btc.generateIndependentlyDivisibleProgram();
    }

    @Test
    public void  testTranslationCmd(){
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<15;i++){
            sb.append("java -jar ../lpmlnmodels-1.1.jar -t  -r clingo -s weak -i bird-"+i+".txt -o bird-trans-"+i+".txt\n");
        }
        System.out.println(sb.toString());
    }
}
