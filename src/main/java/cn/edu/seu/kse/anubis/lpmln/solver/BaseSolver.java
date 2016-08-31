package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;
import net.sf.json.JSONObject;
import net.sf.json.filters.MappingPropertyFilter;

import java.util.*;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class BaseSolver {
    protected List<WeightedAnswerSet> weightedAs=null;
    protected List<WeightedAnswerSet> maxWeightAs=null;
    protected String maxWeight;

    public List<WeightedAnswerSet> call(String cmd){
        String[] cmdres= CommandLineExecute.callShellwithReturn(cmd,1);
        List<WeightedAnswerSet> was=solverResultProcess(cmdres[0]);
        weightedAs=was;
        return was;
    }

    public List<WeightedAnswerSet> findMaxWeightedAs(){
        int level2=0;
        int maxlevel1=0;
        maxWeightAs=new ArrayList<>();
        for(WeightedAnswerSet as:weightedAs){
            level2=as.getWeights().get(1);
            if(maxlevel1<as.getWeights().get(0)){
                maxlevel1=as.getWeights().get(0);
            }
        }

        for(WeightedAnswerSet as :weightedAs){
            if(as.getWeights().get(0) == maxlevel1){
                maxWeightAs.add(as);
            }
        }
        maxWeight=""+level2+"*alpha + "+maxlevel1;
        return maxWeightAs;
    }

    public String marginalDistribution(int factor){
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

        return formateMarginalResult(result);
    }

    public String formateMarginalResult(HashMap<String, Double> result){
        StringBuilder fres=new StringBuilder();
        for(HashMap.Entry<String,Double> entry:result.entrySet()){
            fres.append(entry.getKey()).append("  ").append(entry.getValue()).append(System.lineSeparator());
        }
        return fres.toString();
    }

    public List<WeightedAnswerSet> solverResultProcess(String result){
        return null;
    }

    public List<WeightedAnswerSet> getMaxWeightAs() {
        return maxWeightAs;
    }

    public void setMaxWeightAs(List<WeightedAnswerSet> maxWeightAs) {
        this.maxWeightAs = maxWeightAs;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }
}
