package cn.edu.seu.kse.lpmln;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2019/3/12
 */
public class KSETest {
    //private List<Double> weights = Arrays.asList(0.85,0.85,0.85,0.85);
    private List<Double> weights = Arrays.asList(0.9,0.3,0.3,0.3);


    private double calculate(List<Double> exist,List<Double> all){
        double up=0;
        double down=Math.exp(0);

        for (double upi : exist) {
            up += Math.exp(upi);
        }

        for (double downi : all) {
            down += Math.exp(downi);
        }

        return up/down;
    }

    @Before
    public void preprocess(){
        for (int i=0;i<weights.size();i++){
            double ori = weights.get(i);
            double newWeight = Math.log(ori/(1-ori));
            weights.set(i,newWeight);
        }
    }

    @Test
    public void single(){
        weights.forEach(weight->{
            List<Double> existAndAll = new ArrayList<>();
            existAndAll.add(weight);
            System.out.println(weight+" : "+calculate(existAndAll,existAndAll));
        });
    }

    @Test
    public void valIncrease(){
        //模型推理结果有i个
        for(int i=1;i<=weights.size();i++){
            List<Double> existAndAll = new ArrayList<>();
            int max = (int)Math.pow(2.0,i);
            //枚举所有可能性
            for(int j=1;j<max;j++){
                //该可能性的权重和
                double sum=0;
                //判断每位成立
                for(int k=0;k<i;k++){
                    if((j>>k)%2==1){
                        sum += weights.get(k);
                    }
                }
                existAndAll.add(sum);
            }
            System.out.println("p(lit): "+calculate(existAndAll,existAndAll));
        }
    }
}
