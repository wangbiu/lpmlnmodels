package cn.edu.seu.kse.anubis.experiment.model;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class StatInfo {
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

    public String toCSVString(){
        StringBuilder csv=new StringBuilder();
        csv.append(experimentId).append(",").append(taskType);
        csv.append(",").append(ansNums).append(",").append(solverTime);
        csv.append(",").append(threadNums).append(",").append(threadId);
        return csv.toString();
    }
}
