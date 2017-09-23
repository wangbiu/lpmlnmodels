package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.util.*;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class LPMLNBaseSolver extends AdvancedBaseSolver {

    @Override
    public String marginalDistribution(int factor){
        Date enter=new Date();
        HashMap<String,Double> result=new HashMap<>();
        double wsum=0;
        double expw=0;


        HashSet<String> literals=null;
        for(WeightedAnswerSet as : weightedAs){
            expw=as.getProbability();
            literals=as.getAnswerSet().getLiterals();
            for(String lit:literals){
                if(result.containsKey(lit)){
                    wsum=result.get(lit);
                }else {
                    wsum=0;
                }
                wsum+=expw;
                result.put(lit,wsum);
            }
        }

        String res= formatMarginalResult(result);
        Date exit=new Date();
        StringBuilder sb=new StringBuilder();
        sb.append("求边缘分布用时：").append(exit.getTime()-enter.getTime()).append(" ms");
        sb.append(System.lineSeparator());
        marginalTime=sb.toString();
        return res;
    }

    public String formatMarginalResult(HashMap<String, Double> result){
        StringBuilder fres=new StringBuilder();
        for(HashMap.Entry<String,Double> entry:result.entrySet()){
            fres.append(entry.getKey()).append("  ").append(entry.getValue()).append(System.lineSeparator());
        }
        return fres.toString();
    }
}
