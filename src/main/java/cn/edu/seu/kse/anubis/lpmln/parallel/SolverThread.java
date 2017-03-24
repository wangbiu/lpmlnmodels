package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class SolverThread extends Thread {
    private AugmentedSubsetSolver assolver=null;
    private File rule=null;
    private int factor=1;
    // 0 MAP 1 MPD
    private int taskType;
    private List<WeightedAnswerSet> weightedAs=null;
    private List<WeightedAnswerSet> map=null;
    private HashMap<String,List<Integer>> marginal=null;
    private int wh;
    private int ws;
    private ThreadStatInfo stat;

    public SolverThread(AugmentedSubsetSolver assolver, File rule, int taskType) {
        this.assolver = assolver;
        this.rule = rule;
        this.taskType=taskType;
        stat=new ThreadStatInfo();
//        System.out.println("taskType: "+taskType);
    }

    @Override
    public void run() {
//        assolver.setWs(ws);
//        assolver.setWh(wh);
        Date begin=new Date();
        weightedAs=assolver.call(rule.getAbsolutePath());
        if(taskType == 0){
            map=assolver.findMaxWeightedAs();
//            System.out.println("map: "+map);
        }else if(taskType == 1){
            marginal=assolver.marginalDistribution(factor);
        }else {
            map=assolver.findMaxWeightedAs();
            marginal=assolver.marginalDistribution(factor);
        }
        Date end=new Date();
        long duration=end.getTime()-begin.getTime();
        stat.threadTime=duration/1000.0;
        stat.solverTime=assolver.getTotalSolverTime();
        stat.ansNums=weightedAs.size();
//        System.out.println(this.getName()+" runtime: "+duration+"ms");
    }

    public AugmentedSubsetSolver getAssolver() {
        return assolver;
    }

    public void setAssolver(AugmentedSubsetSolver assolver) {
        this.assolver = assolver;
    }

    public File getRule() {
        return rule;
    }

    public void setRule(File rule) {
        this.rule = rule;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public List<WeightedAnswerSet> getWeightedAs() {
        return weightedAs;
    }

    public void setWeightedAs(List<WeightedAnswerSet> weightedAs) {
        this.weightedAs = weightedAs;
    }

    public List<WeightedAnswerSet> getMap() {
        return map;
    }

    public void setMap(List<WeightedAnswerSet> map) {
        this.map = map;
    }

    public HashMap<String, List<Integer>> getMarginal() {
        return marginal;
    }

    public void setMarginal(HashMap<String, List<Integer>> marginal) {
        this.marginal = marginal;
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

    public ThreadStatInfo getStat() {
        return stat;
    }

    public void setStat(ThreadStatInfo stat) {
        this.stat = stat;
    }
}
