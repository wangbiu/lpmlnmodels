package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;
import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;

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
    // 0 MAP  1 MPD
    private int taskType=2;
    private String tmpfilepath="";
    private String partitionId;

    List<ThreadStatInfo> statInfos=null;

    public ParallelSolver(){
        solvers=new ArrayList<>();
        factor=1;
    }

    public ParallelSolver(List<Rule> rules, String asptext, int cores, int factor, int taskType) {
        this();
        this.rules = rules;
        this.asptext = asptext;
        this.cores = cores;
        this.factor=factor;
        this.taskType=taskType;
        statInfos=new ArrayList<>();
    }

    public void partition() throws IOException {
        ASPStochasticPartition partition = new ASPStochasticPartition(rules, asptext,1);
        partitionId=partition.getPartitionId();
        partition.setBasepath(tmpfilepath);
        partition.setWeakPartition(true);
        partition.partition(cores);
        List<AugmentedSubset> asub=partition.getSplit();
        splits=partition.genSplitFiles();
        for(int i=0;i<cores;i++){
            AugmentedSubsetSolver assolver=new AugmentedSubsetSolver();
            assolver.setWh(asub.get(i).getWh());
            assolver.setWs(asub.get(i).getWs());
            solvers.add(assolver);
        }
    }

    public void call() throws IOException {
        partition();
        callWithLastPartition();
    }

    public void callWithLastPartition(){
        threads=new ArrayList<>();
        ThreadStatInfo stat=null;
        for(int i=0;i<cores;i++){

            SolverThread t=new SolverThread(solvers.get(i),splits.get(i),taskType);
            if(statInfos.size() <= i){
                stat=new ThreadStatInfo();
                statInfos.add(stat);
                stat.threadId=i;
                stat.partitionId=partitionId;
            }

            t.setName("solver #"+i);
            t.setDaemon(false);
            threads.add(t);
            t.start();
        }
    }

    public boolean isStop(){
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
//            System.out.println("map "+map);
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
        extractStatInfo(threads);

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
                    expweights.put(w,expw);
                }
                sum+=expw;
            }
//            System.out.println("asweight "+asweights);
        }

//        System.out.println("sum: "+sum);
        double expwvalue=0;
        Set<Integer> expwkeyset= expweights.keySet();
        for(int key:expwkeyset){
            expwvalue=expweights.get(key);
            expwvalue/=sum;
            expweights.put(key,expwvalue);
        }

//        System.out.println("expweight: "+expweights);

        HashMap<String,List<Integer>> margs=new HashMap<>();
        HashMap<String,List<Integer>> tmpmargs=null;
        List<Integer> litweights=null;
        for(SolverThread st:threads){
            tmpmargs=st.getMarginal();
            for(Map.Entry<String,List<Integer>> entry:tmpmargs.entrySet()){
                if(margs.containsKey(entry.getKey())){
                    litweights=margs.get(entry.getKey());
                }else {
                    litweights=new ArrayList<>();
                    margs.put(entry.getKey(),litweights);
                }
                litweights.addAll(entry.getValue());
            }
//            System.out.println("tmpargs "+tmpmargs);
        }

//        System.out.println("marginal: "+margs);
        String litkey=null;
        double litexpw=0;
        for(Map.Entry<String,List<Integer>> entry:margs.entrySet()){
            litexpw=0;
            litkey=entry.getKey();
            litweights=entry.getValue();
            sb.append(litkey).append(" ");
            for(int w:litweights){
                litexpw+=expweights.get(w);
            }
            sb.append(litexpw).append(System.lineSeparator());

        }
        extractStatInfo(threads);
        return sb.toString();
    }

    public void extractStatInfo(List<SolverThread> threads){
        ThreadStatInfo recordstat=null;
        ThreadStatInfo thstat=null;

        for(int i=0;i<cores;i++){
            recordstat=statInfos.get(i);
            thstat=threads.get(i).getStat();
            recordstat.ansNums=thstat.ansNums;
            recordstat.solverTime+=thstat.solverTime;
            recordstat.threadTime+=thstat.threadTime;
//            System.out.printf("thread #%d: solvertime: %f, threadtime: %f%n",i,recordstat.solverTime,recordstat.threadTime);
        }
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

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public List<ThreadStatInfo> getStatInfos() {
        return statInfos;
    }

    public void setStatInfos(List<ThreadStatInfo> statInfos) {
        this.statInfos = statInfos;
    }

    public String getTmpfilepath() {
        return tmpfilepath;
    }

    public void setTmpfilepath(String tmpfilepath) {
        this.tmpfilepath = tmpfilepath;
    }
}
