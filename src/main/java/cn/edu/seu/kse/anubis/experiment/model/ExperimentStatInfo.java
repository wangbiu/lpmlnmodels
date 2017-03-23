package cn.edu.seu.kse.anubis.experiment.model;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ExperimentStatInfo {
    // 实验编号
    String experimentId;
    // 任务类型 0=MAP 任务， 1=Marginal Distribution 任务
    int taskType;
    // 用时
    double time;

    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(experimentId).append(",").append(taskType).append(",").append(time);
        return csv.toString();
    }
}
