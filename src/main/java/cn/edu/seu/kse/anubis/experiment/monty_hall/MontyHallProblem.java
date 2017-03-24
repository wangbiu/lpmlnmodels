package cn.edu.seu.kse.anubis.experiment.monty_hall;

import cn.edu.seu.kse.anubis.experiment.model.ExperimentStatInfo;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.Clingo4;
import cn.edu.seu.kse.anubis.lpmln.syntax.SyntaxModule;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.BaseTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.WeakASPTranslator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class MontyHallProblem {
    final protected String basepath="G:/expriment/parallel_reasoning/monty_hall";
    protected String logfile=basepath+"/monty-hall-single.log";
    final protected String programPrefix="m-";
    protected List<Rule> rules;
    protected int factor;
    protected HashSet<String> herbrandUniverse;
    protected String metaRule;
    protected String aspTranslation;
    protected String translationFilePath;
    protected ASPTranslator translator;
    protected ExperimentStatInfo expStat;
    protected int taskType;
    protected int round;
    protected int problemN=3;

    public MontyHallProblem(){
        expStat=new ExperimentStatInfo();
        translator=new WeakASPTranslator();
    }

    protected void runExperiment() throws IOException {
        expStat.taskType=taskType;
        expStat.experimentId=genExperimentId();
        String progFilePath=getProgramFilePath(problemN);
        Date begin=new Date();
        expStat.now=begin;
        expStat.problemN=problemN;
        for(int i=0;i<round;i++){
            call(progFilePath,taskType);
        }
        Date end=new Date();
//        expStat.time=(end.getTime()-begin.getTime())/1000;
        expStat.time=(end.getTime()-begin.getTime())/1000/round;
        expStat.cpuTime/=round;
        writeLog();
    }

    protected void call(String progFilePath,int taType) throws IOException {
        parse(progFilePath);
        translationFilePath=translation(translator,progFilePath);
        solve(taType);
    }

    protected void parse(String inputFilePath) throws IOException {
        File inputFile=new File(inputFilePath);
        SyntaxModule sm=new SyntaxModule();
        rules=rules= sm.parse(inputFile);
        factor=sm.getFactor();
        herbrandUniverse=sm.getHerbrandUniverse();
        metaRule=sm.getMetarule();
    }

    protected String translation(ASPTranslator translator, String inputFilePath) throws IOException {

//        WeakASPTranslator translator=new WeakASPTranslator();
        translateRules(translator);

        return writeTranslation2File(inputFilePath);
    }

    protected void translateRules(ASPTranslator translator){
        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        translator.setMetarule(metaRule);
        aspTranslation=translator.translate(rules);
    }

    protected String writeTranslation2File(String inputFilePath) throws IOException {
        String transFilePath=inputFilePath+".trans";
        BufferedWriter bw=new BufferedWriter(new FileWriter(transFilePath));
        bw.write(aspTranslation);
        bw.close();
        return transFilePath;
    }

    protected void solveMAP(){
        Clingo4 solver=new Clingo4();
        solver.call(translationFilePath);
        List<WeightedAnswerSet> maxWas=null;
        maxWas=solver.findMaxWeightedAs();
        expStat.cpuTime=solver.getTotalSolverTime();
    }

    protected void solveMarginalProbability(){
        Clingo4 solver=new Clingo4();
        solver.call(translationFilePath);
        String marginal=solver.marginalDistribution(factor);
        expStat.cpuTime=solver.getTotalSolverTime();
    }


    protected void solve(int taskType){
        if(taskType == 0){
            solveMAP();
        }else if(taskType == 1){
            solveMarginalProbability();
        }
    }

    protected String genExperimentId(){
        return UUID.randomUUID().toString();
    }

    protected String getProgramFilePath(int n){
        return basepath+"/"+programPrefix+n+".txt";
    }

    protected void writeLog() throws IOException {
        File logf=new File(logfile);
        BufferedWriter bw=new BufferedWriter(new FileWriter(logf,true));
        bw.write(expStat.toCSVString());
        bw.write(System.lineSeparator());
        bw.close();
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getProblemN() {
        return problemN;
    }

    public void setProblemN(int problemN) {
        this.problemN = problemN;
    }
}
