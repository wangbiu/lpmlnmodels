package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;
import cn.edu.seu.kse.anubis.lpmln.solver.Solver;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ParallelSolver {
    private List<Rule> rules = null;
    private String asptext=null;
    private int cores;
    private int factor;
    private List<AugmentedSubsetSolver> solvers;
    private List<File> splits=null;
    List<SolverThread> threads=null;

    public ParallelSolver(){
        solvers=new ArrayList<>();
        factor=1;
    }

    public ParallelSolver(List<Rule> rules, String asptext, int cores, int factor) {
        this();
        this.rules = rules;
        this.asptext = asptext;
        this.cores = cores;
        this.factor=factor;
    }

    public void partition() throws IOException {
        ASPStochasticPartition partition = new ASPStochasticPartition(rules, asptext,1);
        partition.setSoftPartition(true);
        partition.partition(cores);
        splits=partition.genSplitFiles();
        for(int i=0;i<cores;i++){
            AugmentedSubsetSolver assolver=new AugmentedSubsetSolver();
            solvers.add(assolver);
        }
    }

    public void call() throws IOException {
        partition();
        threads=new ArrayList<>();

        for(int i=0;i<cores;i++){
            SolverThread t=new SolverThread(solvers.get(i),splits.get(i));
            t.setName("solver #"+i);
            t.setDaemon(false);
            threads.add(t);
            t.start();
        }

//        while (!isStop(threads)){}

    }

    private boolean isStop(List<SolverThread> threads){
        boolean stop=true;
        for(SolverThread st:threads){
            if(st.isAlive()){
                stop=false;
                break;
            }
        }
        return stop;
    }

    //FIXME: factor未处理，暂时只考虑权重是正整数的情况考虑
    public List<WeightedAnswerSet> findMaxWeightedAs(){
        List<WeightedAnswerSet> max=new ArrayList<>();
        int maxSoftWeight=0;
        int curSoftWeight=0;
        List<WeightedAnswerSet> map=null;
        for(SolverThread st:threads){
            map=st.getMap();
            if(!map.isEmpty()){
                curSoftWeight=map.get(0).getWeights().get(0);
            }else {
                curSoftWeight=-1;
            }
            if(curSoftWeight>=maxSoftWeight){
                maxSoftWeight=curSoftWeight;
            }
        }

        for(SolverThread st:threads){
            map=st.getMap();
            if(!map.isEmpty()){
                curSoftWeight=map.get(0).getWeights().get(0);
            }else {
                curSoftWeight=-1;
            }

            if(curSoftWeight == maxSoftWeight){
                max.addAll(map);
            }
        }

        return max;
    }

    //FIXME: factor未处理，暂时只考虑权重是正整数的情况考虑
    public String marginalDistribution(int factor){
        StringBuilder sb=new StringBuilder();
        HashMap<Integer,Double> expweights=new HashMap<>();
        double sum=0;
        List<WeightedAnswerSet> weightedAnswerSets=null;
        List<Integer> asweights=null;
        double expw=0;
        for(SolverThread st:threads){
            asweights=st.getAssolver().getAsweights();
            for(int w:asweights){
                if(expweights.containsKey(w)){
                    expw=expweights.get(w);
                }else {
                    expw=Math.exp(w);
                }
                sum+=expw;
            }
        }

        double expwvalue=0;
        Set<Integer> expwkeyset= expweights.keySet();
        for(int key:expwkeyset){
            expwvalue=expweights.get(key);
            expwvalue/=sum;
            expweights.put(key,expwvalue);
        }

        HashMap<String,List<Integer>> margs=new HashMap<>();
        HashMap<String,List<Integer>> tmpmargs=null;
        List<Integer> litweights=null;
        for(SolverThread st:threads){
            tmpmargs=st.getMarginal();
            for(Map.Entry<String,List<Integer>> entry:tmpmargs.entrySet()){
                if(margs.containsKey(entry.getKey())){
                    litweights=entry.getValue();
                }else {
                    litweights=new ArrayList<>();
                    margs.put(entry.getKey(),litweights);
                }
                litweights.addAll(entry.getValue());
            }
        }

        String litkey=null;
        double litexpw=0;
        for(Map.Entry<String,List<Integer>> entry:margs.entrySet()){
            litkey=entry.getKey();
            litweights=entry.getValue();
            sb.append(litkey).append(" ");
            for(int w:litweights){
                litexpw+=expweights.get(w);
            }
            sb.append(litexpw).append(System.lineSeparator());

        }

        return sb.toString();
    }




    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getAsptext() {
        return asptext;
    }

    public void setAsptext(String asptext) {
        this.asptext = asptext;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }


}
