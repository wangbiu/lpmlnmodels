package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class AugmentedSubsetSolver extends AdvancedBaseSolver {
    private int wh;
    private int ws;
    //全部回答集的soft weight
    private List<Integer> asweights =new ArrayList<>();

    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("clingo 0 --opt-mode enum ").append(rulefile).append("");
//        System.out.println(cmd);
        weightedAs=super.call(cmd.toString());
        fixWeight();
        return weightedAs;
    }

    private void fixWeight(){
        int aswh=wh;
        int asws=ws;
        for(WeightedAnswerSet was:weightedAs){
//            aswh=wh;
//            asws=ws;
            // FIXME: aswh,asws 应该设为augmented subset的base weight
            aswh=0;
            asws=0;
            List<Integer> weight=was.getWeights();
//            System.out.println("weight: "+weight);
            aswh+=weight.get(1);
            asws+=weight.get(0);
//            System.out.printf("aswh: %d, asws: %d %n",aswh,asws);
            weight.clear();
            weight.add(asws);
            weight.add(aswh);
            asweights.add(asws);
        }
//        System.out.println("origin asweight: "+asweights);
    }

    @Override
    public HashMap<String, List<Integer>> marginalDistribution(int factor){
        HashMap<String, List<Integer>> marginalWeight=new HashMap<>();
        HashSet<String> literals=null;
        List<Integer> weights=null;
        for(WeightedAnswerSet as : weightedAs){
            literals=as.getAnswerSet().getLiterals();
            for(String lit:literals){
                if(marginalWeight.containsKey(lit)){
                    weights=marginalWeight.get(lit);
                }else {
                    weights=new ArrayList<>();
                    marginalWeight.put(lit,weights);
                }
                weights.add(as.getWeights().get(0));
            }
        }
        return marginalWeight;
    }


    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
        SyntaxModule sm=new SyntaxModule();
        int posstart=result.indexOf("Answer: 1");
        if(posstart <0){
            return new ArrayList<>();
        }
        int posend=result.indexOf("OPTIMUM FOUND");
//        System.out.println(result);
        String asresult=result.substring(posstart,posend);
        String statinfo=result.substring(posend);
        result=null;

        List<WeightedAnswerSet> was=sm.parseClingoResult(asresult);
        sm=null;
//        System.out.println("was: "+was);

        // 抽取时间信息

        String[] stats=statinfo.split(System.lineSeparator());
        statinfo=stats[stats.length-2];
        posstart=statinfo.indexOf(":")+1;
        posend=statinfo.indexOf("s");
        String time=statinfo.substring(posstart,posend).trim();
        totalSolverTime=Double.valueOf(time);

        return was;
    }

    public List<Integer> getAsweights() {
        return asweights;
    }

    public void setAsweights(List<Integer> asweights) {
        this.asweights = asweights;
    }

    public int getWh() {
        return wh;
    }

    public void setWh(int wh) {
        this.wh = wh;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }
}
