package cn.edu.seu.kse.anubis.experiment.monty_hall;

import cn.edu.seu.kse.anubis.experiment.Experiment;
import cn.edu.seu.kse.anubis.experiment.model.ExperimentStatInfo;
import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class MontyHallExperiment extends Experiment{
    private String basepath="G:/expriment/parallel_reasoning/monty_hall";
    private String logfile=basepath+"/monty-hall-single.log";
    private String threadLogFile=basepath+"/thread-log.log";

    private int round=1;
    private int cores=1;
    private int maxCores=16;
    private int problemN=3;
    private int maxProblemN=3;


    public void testSingleMAP() throws IOException {
        writeTitle(logfile, ExperimentStatInfo.getTitle());
        for(int i=problemN;i<=maxProblemN;i++){
            startSingle(i,round,0);
        }
    }


    public void testParallelMAP() throws IOException{
        writeTitle(logfile, ExperimentStatInfo.getTitle());
        writeTitle(threadLogFile, ThreadStatInfo.getTitle());
        for(int c=cores;c<=maxCores;c++){
            for(int p=problemN;p<=maxProblemN;p++){
                startParallel(p,round,c,0);
            }
        }

    }

    public void testSingleMPD() throws IOException {
        writeTitle(logfile, ExperimentStatInfo.getTitle());
        for(int i=problemN;i<=maxProblemN;i++){
            startSingle(i,round,1);
        }
    }

    public void testParallelMPD() throws IOException{
        writeTitle(logfile, ExperimentStatInfo.getTitle());
        writeTitle(threadLogFile, ThreadStatInfo.getTitle());
        for(int p=problemN;p<=maxProblemN;p++){
            for(int c=cores;c<=maxCores;c++){
                startParallel(p,round,c,1);
            }
        }

    }

    private void startSingle(int problemN, int round, int taskType) throws IOException {
        MontyHallProblem mhp=new MontyHallProblem();
        initSingle(mhp);
        mhp.setProblemN(problemN);
        mhp.setRound(round);
        mhp.setTaskType(taskType);
        mhp.runExperiment();
    }

    private void startParallel(int problemN, int round, int cores, int taskType) throws IOException {
        ParallelMontyHallProblem pmhp=new ParallelMontyHallProblem();
        initParallel(pmhp);
        pmhp.setProblemN(problemN);
        pmhp.setTaskType(taskType);
        pmhp.setCores(cores);
        pmhp.setRound(round);
        pmhp.runExperiment();
    }

    private void initSingle(MontyHallProblem mhp) throws IOException {
        mhp.setBasepath(basepath);
        mhp.setLogfile(logfile);
    }

    private void initParallel(ParallelMontyHallProblem pmhp) throws IOException {
        pmhp.setBasepath(basepath);
        pmhp.setLogfile(logfile);
        pmhp.setThreadlogfile(threadLogFile);
    }

    private void writeTitle(String filePath, String text) throws IOException {
        BufferedWriter bw=new BufferedWriter(new FileWriter(new File(filePath),true));
        bw.write(text);
        bw.write(System.lineSeparator());
        bw.close();
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public String getLogfile() {
        return logfile;
    }

    public void setLogfile(String logfile) {
        this.logfile = logfile;
    }

    public String getThreadLogFile() {
        return threadLogFile;
    }

    public void setThreadLogFile(String threadLogFile) {
        this.threadLogFile = threadLogFile;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getMaxCores() {
        return maxCores;
    }

    public void setMaxCores(int maxCores) {
        this.maxCores = maxCores;
    }

    public int getProblemN() {
        return problemN;
    }

    public void setProblemN(int problemN) {
        this.problemN = problemN;
    }

    public int getMaxProblemN() {
        return maxProblemN;
    }

    public void setMaxProblemN(int maxProblemN) {
        this.maxProblemN = maxProblemN;
    }
}
