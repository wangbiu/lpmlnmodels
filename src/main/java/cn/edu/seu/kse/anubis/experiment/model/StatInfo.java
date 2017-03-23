package cn.edu.seu.kse.anubis.experiment.model;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class StatInfo {
    // 实验编号
    String experimentId;
    // 任务类型 0=MAP 任务， 1=Marginal Distribution 任务
    int taskType;
    // 该线程求得回答集数
    int ansNums;
    // clingo 统计时间
    double solverTime;
    // 线程用时
    double threadTime;
    // 总线程数
    int threadNums;
    // 当前线程编号
    int threadId;

    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(experimentId).append(",").append(taskType);
        csv.append(",").append(ansNums).append(",").append(solverTime);
        csv.append(",").append(threadNums).append(",").append(threadId);
        return csv.toString();
    }
}
