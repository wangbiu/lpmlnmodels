package cn.edu.seu.kse.anubis.experiment.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ThreadStatInfo extends StatInfo{
    // 实验编号
    public String experimentId;

    public String runId;
    // 盒子数量
    public int problemN;
    // 任务类型 0=MAP 任务， 1=Marginal Distribution 任务
    public int taskType;
    // 该线程求得回答集数
    public int ansNums;
    // clingo 统计时间
    public double solverTime;
    // 线程用时
    public double threadTime;
    // 总线程数
    public int threadNums;
    // 当前线程编号
    public int threadId;

    public String partitionId;

    public ThreadStatInfo(){
        now=new Date();
        runId= UUID.randomUUID().toString();
    }

    @Override
    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(testId).append(",");
        csv.append(runId).append(",");
        csv.append(experimentId).append(",");
        csv.append(problemN).append(",");
        csv.append(taskType).append(",");
        csv.append(ansNums).append(",");
        csv.append(formatDouble(solverTime,precise)).append(",");
        csv.append(formatDouble(threadTime,precise)).append(",");
        csv.append(threadNums).append(",");
        csv.append(threadId).append(",");
        csv.append(partitionId).append(",");
        csv.append(formatDate(now));
        return csv.toString();
    }

    public static String getTitle(){
        StringBuilder sb=new StringBuilder();
        sb.append("testId").append(",");
        sb.append("runId").append(",");
        sb.append("experimentId").append(",");
        sb.append("number of boxes").append(",");
        sb.append("taskType").append(",");
        sb.append("number of stable models").append(",");
        sb.append("solver run time of an augmented subset").append(",");
        sb.append("total run time of an augmented subset").append(",");
        sb.append("number of processors").append(",");
        sb.append("threadId").append(",");
        sb.append("partitionId").append(",");
        sb.append("date").append("");
        return sb.toString();
    }
}
