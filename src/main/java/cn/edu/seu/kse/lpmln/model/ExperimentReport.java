package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/3/5
 */
public class ExperimentReport {
    public ExperimentReport() {
        solvers = new ArrayList<>();
        time = new ArrayList<>();
        setStatus("success");
    }

    /**
     * 实验名称
     */
    protected String experimentName;

    /**
     * 实验时间
     */
    protected List<String> time;

    /**
     * 实验是否成功
     */
    protected String status;

    /**
     * 实验中使用的推理机
     * TODO:可能需要修改接口
     */
    protected List<LPMLNBaseSolver> solvers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LPMLNBaseSolver> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<LPMLNBaseSolver> solvers) {
        this.solvers = solvers;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
