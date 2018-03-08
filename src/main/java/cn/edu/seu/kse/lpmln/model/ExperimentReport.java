package cn.edu.seu.kse.lpmln.model;

import net.sf.json.JSONObject;

/**
 * @author 许鸿翔
 * @date 2018/3/5
 */
public class ExperimentReport {
    protected String experimentName;
    protected String solver;
    protected String totalTime;
    protected String solveTime;
    protected String date;
    protected String processors;

    public String tojson(){
        return JSONObject.fromObject(this).toString();
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProcessors() {
        return processors;
    }

    public void setProcessors(String processors) {
        this.processors = processors;
    }
}
