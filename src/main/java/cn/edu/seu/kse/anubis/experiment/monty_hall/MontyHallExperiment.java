package cn.edu.seu.kse.anubis.experiment.monty_hall;

import cn.edu.seu.kse.anubis.experiment.Experiment;
import cn.edu.seu.kse.anubis.experiment.model.ExperimentStatInfo;
import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class MontyHallExperiment extends Experiment{

    private int round=1;
    private int cores=1;
    private int maxCores=16;
    private int problemN=3;
    private int maxProblemN=3;

    private int taskId;
    private boolean parallel;


    public void test(boolean isParallel, int taskId) throws Exception {
        //TODO: 用于提前装载所有的类？测试
        testSingleMAP();

        initLogFile();
        parallel=isParallel;
        this.taskId=taskId;
        if(isParallel){
            testParallelTask(taskId);
        }else {
            testSingleTask(taskId);
        }

        emailAlert(null,null,email_addr);
    }

    public void testSingleTask(int taskId) throws IOException {
        if(taskId==0){
            testSingleMAP();
        }else if(taskId==1){
            testSingleMPD();
        }else {
            testSingleMAP();
            testSingleMPD();
        }
    }

    public void testParallelTask(int taskId) throws IOException {
        if(taskId==0){
            testParallelMAP();
        }else if(taskId==1){
            testParallelMPD();
        }else {
            testParallelMAP();
            testParallelMPD();
        }
    }


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

    private void initLogFile(){
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        logfile=logfile+sdf.format(now)+".log";
        threadLogFile=threadLogFile+sdf.format(now)+".log";
    }

    @Override
    public void emailAlert(String title, String text, String address) throws Exception {
        String line="<br>";
        StringBuilder sb=new StringBuilder();
        sb.append("实验信息：").append("taskId ").append(taskId).append(", <span style='color:red;'>是否并行</span> ").append(parallel);
        sb.append(line);
        sb.append("问题信息：").append("problemN ").append(problemN).append(", max problem N ").append(maxProblemN).append(line);
        sb.append("并行信息：").append("cores ").append(cores).append(", max cores ").append(maxCores).append(line);
        sb.append("实验结束，日志文件为：").append(line);
        sb.append(logfile).append(", ").append(threadLogFile);
        sb.append(". ").append(line).append(line);
        sb.append("日志内容：").append(line);
        sb.append("<strong>").append(logfile).append("</strong>").append(line);
        sb.append(readFile(logfile)).append(line);
        sb.append("<strong>").append(threadLogFile).append("</strong>").append(line);
        sb.append(readFile(threadLogFile)).append(line);

        super.emailAlert("实验完成!!!",sb.toString(), this.email_addr);
    }

    public void emailWarn(String text) throws Exception {
        super.emailAlert("实验失败!!!",text,email_addr);
    }

    private String readFile(String file) throws IOException {
        File infile=new File(file);
        if(infile.exists()){
            StringBuilder sb=new StringBuilder();
            BufferedReader br=null;
            br=new BufferedReader(new FileReader(infile));
            String line=null;
            while((line=br.readLine())!=null){
                sb.append(line).append("<br>");
            }
            br.close();
            return sb.toString();
        }else {
            return null;
        }
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
