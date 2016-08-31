package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class BaseSolver {
    protected List<WeightedAnswerSet> maxWeightAs=null;
    protected String maxWeight;

    public List<WeightedAnswerSet> call(String cmd){
        String[] cmdres= CommandLineExecute.callShellwithReturn(cmd,1);
        List<WeightedAnswerSet> was=solverResultProcess(cmdres[0]);
        return was;
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
