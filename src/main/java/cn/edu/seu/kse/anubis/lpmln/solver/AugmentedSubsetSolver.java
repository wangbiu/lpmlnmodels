package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.SyntaxMoudle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class AugmentedSubsetSolver extends BaseSolver {
    private int wh;
    private int ws;
    //全部回答集的soft weight
    private List<Integer> asweights =new ArrayList<>();;

    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("clingo 0 --opt-mode enum ").append(rulefile).append("");
        System.out.println(cmd);
        return super.call(cmd.toString());
    }

    private void fixWeight(){
        int aswh=wh;
        int asws=ws;
        for(WeightedAnswerSet was:weightedAs){
            List<Integer> weight=was.getWeights();
            aswh+=weight.get(1);
            asws+=weight.get(0);
            weight.clear();
            weight.add(asws);
            weight.add(aswh);
            asweights.add(asws);
        }

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
                }
                weights.add(as.getWeights().get(0));
            }
        }
        return marginalWeight;
    }


    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
        SyntaxMoudle sm=new SyntaxMoudle();
        int posstart=result.indexOf("Answer: 1");
        if(posstart <0){
            return new ArrayList<>();
        }
        int posend=result.indexOf("OPTIMUM FOUND");
        System.out.println(result);
        result=result.substring(posstart,posend);
        List<WeightedAnswerSet> was=sm.parseClingoResult(result);
        return was;
    }

    public List<Integer> getAsweights() {
        return asweights;
    }

    public void setAsweights(List<Integer> asweights) {
        this.asweights = asweights;
    }
}
