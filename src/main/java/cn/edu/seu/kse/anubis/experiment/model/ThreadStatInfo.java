package cn.edu.seu.kse.anubis.experiment.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ThreadStatInfo extends StatInfo{
    // 实验编号
    public String experimentId;
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
    }

    @Override
    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(formatDate(now)).append(",");
        csv.append(experimentId).append(",").append(taskType);
        csv.append(",").append(ansNums).append(",").append(formatDouble(solverTime,precise));
        csv.append(",").append(formatDouble(threadTime,precise));
        csv.append(",").append(threadNums).append(",").append(threadId);
        csv.append(",").append(partitionId);
        return csv.toString();
    }

    @Override
    public String getTitle(){
        StringBuilder sb=new StringBuilder();
        sb.append("date").append(",");
        sb.append("experimentId").append(",");
        sb.append("taskType").append(",");
        sb.append("ansNums").append(",");
        sb.append("solverTime").append(",");
        sb.append("threadTime").append(",");
        sb.append("threadNums").append(",");
        sb.append("threadId").append(",");
        sb.append("partitionId").append(",");
        return sb.toString();
    }
}
