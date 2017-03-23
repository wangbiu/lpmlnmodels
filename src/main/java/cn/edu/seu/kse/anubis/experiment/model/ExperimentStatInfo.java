package cn.edu.seu.kse.anubis.experiment.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ExperimentStatInfo {
    // 时间
    public Date now;
    // 实验编号
    public String experimentId;
    // 问题规模 盒子个数
    public int problemN;
    // 任务类型 0=MAP 任务， 1=Marginal Distribution 任务
    public int taskType;
    // 用时
    public double time;

    public double cpuTime;

    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        csv.append(sdf.format(now)).append(",");
        csv.append(experimentId).append(",").append(problemN).append(",").append(taskType);
        csv.append(",").append(cpuTime).append(",").append(time);
        return csv.toString();
    }
}
