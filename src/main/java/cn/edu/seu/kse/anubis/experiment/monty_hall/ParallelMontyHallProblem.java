package cn.edu.seu.kse.anubis.experiment.monty_hall;

import cn.edu.seu.kse.anubis.experiment.model.ExperimentStatInfo;
import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;
import cn.edu.seu.kse.anubis.lpmln.parallel.ParallelSolver;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPGround4ParallelTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.WeakASPTranslator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ParallelMontyHallProblem extends MontyHallProblem {
    private final String threadlogfile=basepath+"/thread-log.log";
    private int cores;
    private ParallelSolver psolver=null;
    private List<ThreadStatInfo> tstatInfos;

    public ParallelMontyHallProblem(){
        expStat=new ExperimentStatInfo();
        translator=new ASPGround4ParallelTranslator();
    }

    @Override
    protected void runExperiment() throws IOException {
        super.runExperiment();
        processThreadStat();
    }

    public void processThreadStat() throws IOException {
        tstatInfos=psolver.getStatInfos();
        for(ThreadStatInfo ts:tstatInfos){
            ts.experimentId=expStat.experimentId;
            ts.taskType=taskType;
            ts.threadNums=cores;
            ts.threadTime/=round;
            ts.solverTime/=round;
        }
        File logf=new File(threadlogfile);
        BufferedWriter bw=new BufferedWriter(new FileWriter(logf,true));
        for(ThreadStatInfo ts:tstatInfos){
            bw.write(ts.toCSVString());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

    @Override
    protected void solveMAP() {
        try {
            if(psolver == null){
                psolver=new ParallelSolver(rules,aspTranslation,cores,factor,0);
                psolver.setTmpfilepath(basepath);
                psolver.call();
            }else {
                psolver.callWithLastPartition();
            }

            while (!psolver.isStop()){}
            System.out.println(psolver.findMaxWeightedAs());
//            psolver.findMaxWeightedAs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.solveMAP();
    }

    @Override
    protected void solveMarginalProbability() {
        try {
            if(psolver == null){
                psolver=new ParallelSolver(rules,aspTranslation,cores,factor,1);
                psolver.setTmpfilepath(basepath);
                psolver.call();
            }else{
                psolver.callWithLastPartition();
            }

            while (!psolver.isStop()){}
            System.out.println(psolver.marginalDistribution(factor));
//            psolver.marginalDistribution(factor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.solveMarginalProbability();
    }

    @Override
    protected String translation(ASPTranslator translator, String inputFilePath) throws IOException {
        translateRules(translator);
        return  null;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }
}
