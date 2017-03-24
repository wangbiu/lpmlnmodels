package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.SolverStats;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;
import net.sf.json.JSONObject;
import net.sf.json.filters.MappingPropertyFilter;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class LPMLNBaseSolver extends BaseSolver {

    @Override
    public String marginalDistribution(int factor){
        Date enter=new Date();
        HashMap<String,Double> result=new HashMap<>();
        double wsum=0;
        double expw=0;
        for(WeightedAnswerSet as:weightedAs){
            expw= Math.exp(as.getWeights().get(0)*1.0/factor);
            wsum+=expw;
            as.setProbability(expw);
        }

        for(WeightedAnswerSet as:weightedAs){
            expw=as.getProbability();
            as.setProbability(expw/wsum);
        }

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

        String res= formateMarginalResult(result);
        Date exit=new Date();
        StringBuilder sb=new StringBuilder();
        sb.append("求边缘分布用时：").append(exit.getTime()-enter.getTime()).append(" ms");
        sb.append(System.lineSeparator());
        marginalTime=sb.toString();
        return res;
    }

    public String formateMarginalResult(HashMap<String, Double> result){
        StringBuilder fres=new StringBuilder();
        for(HashMap.Entry<String,Double> entry:result.entrySet()){
            fres.append(entry.getKey()).append("  ").append(entry.getValue()).append(System.lineSeparator());
        }
        return fres.toString();
    }
}
