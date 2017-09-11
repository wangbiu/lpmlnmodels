package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.SolverStats;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class BaseSolver {
    protected List<WeightedAnswerSet> weightedAs=null;
    protected List<WeightedAnswerSet> maxWeightAs=null;
    protected String maxWeight;
    protected SolverStats stats;
    protected String executeProfile;
    protected String marginalTime;
    protected String maximalTime;
    protected List<Long> executeTime=new ArrayList<>();
    protected SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
    final protected int WIN_NT=1;
    final protected int UNIX=0;
    protected double totalSolverTime;
    protected SolverStats sta = new SolverStats();

    public List<WeightedAnswerSet> call(String cmd){
        Date enter=new Date();
        String[] cmdres= CommandLineExecute.callShellwithReturn(cmd,WIN_NT);
        Date cmdExit=new Date();

//        System.out.println("result "+cmdres[0]);
//        System.out.println("error: "+cmdres[1]);
        List<WeightedAnswerSet> was=solverResultProcess(cmdres[0]);
        weightedAs=was;
        stats=genSolverStatisticsInfo();
        Date exit=new Date();

        StringBuilder sb=new StringBuilder();

        sb.append("进入推理核心时间：").append(sdf.format(enter)).append(System.lineSeparator());
        sb.append("推理机核心调用结束：").append(sdf.format(cmdExit)).append(System.lineSeparator());
        sb.append("推理结果预处理完成：").append(sdf.format(exit)).append(System.lineSeparator());


        executeTime.add(exit.getTime()-enter.getTime());
//        executeTime.add(cmdExit.getTime()-enter.getTime());
//        executeTime.add(exit.getTime()-cmdExit.getTime());
        sb.append("推理核心用时：").append(executeTime.get(0)).append(" ms").append(System.lineSeparator());
        executeProfile=sb.toString();

        return was;
    }

    public List<WeightedAnswerSet> findMaxWeightedAs(){
        Date enter=new Date();
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

        if(level2!=0){
            maxWeight=""+level2+"*alpha + "+maxlevel1;
        }else {
            maxWeight=String.valueOf(maxlevel1);
        }

//        System.out.println(maxWeight);
//        System.out.println("maxWeightAS "+maxWeightAs);

        Date exit=new Date();
        StringBuilder sb=new StringBuilder();
        sb.append("求最大权重可能世界用时：").append(exit.getTime()-enter.getTime()).append(" ms");
        sb.append(System.lineSeparator());
        maximalTime=sb.toString();
        return maxWeightAs;
    }

    public Object marginalDistribution(int factor){
        return null;
    }



    public SolverStats genSolverStatisticsInfo(){
        sta.setTotal(totalSolverTime);
        return sta;
    }

    public String getExecuteProfile() {
        return executeProfile;
    }

    public void setExecuteProfile(String executeProfile) {
        this.executeProfile = executeProfile;
    }

    public List<Long> getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(List<Long> executeTime) {
        this.executeTime = executeTime;
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

    public SolverStats getStats() {
        return stats;
    }

    public void setStats(SolverStats stats) {
        this.stats = stats;
    }

    public String getMarginalTime() {
        return marginalTime;
    }

    public void setMarginalTime(String marginalTime) {
        this.marginalTime = marginalTime;
    }

    public String getMaximalTime() {
        return maximalTime;
    }

    public void setMaximalTime(String maximalTime) {
        this.maximalTime = maximalTime;
    }

    public double getTotalSolverTime() {
        return totalSolverTime;
    }

    public void setTotalSolverTime(double totalSolverTime) {
        this.totalSolverTime = totalSolverTime;
    }
}
