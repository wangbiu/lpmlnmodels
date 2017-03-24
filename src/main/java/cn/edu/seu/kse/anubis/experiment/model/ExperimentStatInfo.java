package cn.edu.seu.kse.anubis.experiment.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ExperimentStatInfo  extends StatInfo{
    // 实验编号
    public String experimentId;
    // 问题规模 盒子个数
    public int problemN;
    // 任务类型 0=MAP 任务， 1=Marginal Distribution 任务
    public int taskType;
    // 线程数
    public int threadNums=1;
    // 用时
    public double time;

    public double cpuTime;

    @Override
    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(formatDate(now)).append(",");
        csv.append(experimentId).append(",").append(problemN).append(",").append(taskType);
        csv.append(",").append(threadNums);
        csv.append(",").append(formatDouble(cpuTime,precise)).append(",").append(formatDouble(time,precise));
        return csv.toString();
    }

    public static String getTitle(){
        StringBuilder sb=new StringBuilder();
        sb.append("date").append(",");
        sb.append("experimentId").append(",");
        sb.append("problemN").append(",");
        sb.append("taskType").append(",");
        sb.append("threadNums").append(",");
        sb.append("solverTime").append(",");
        sb.append("threadTime").append("");

        return sb.toString();
    }
}
