package cn.edu.seu.kse.lpmln.model;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class SolverStats {
    private double total;
    private double solve;
    private double model;
    private double unsat;
    private double cpu;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSolve() {
        return solve;
    }

    public void setSolve(double solve) {
        this.solve = solve;
    }

    public double getModel() {
        return model;
    }

    public void setModel(double model) {
        this.model = model;
    }

    public double getUnsat() {
        return unsat;
    }

    public void setUnsat(double unsat) {
        this.unsat = unsat;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("core solver statistics info").append(System.lineSeparator());
        sb.append("Total : ").append(total).append(System.lineSeparator());
        sb.append("Solve : ").append(solve).append(System.lineSeparator());
        sb.append("Model : ").append(model).append(System.lineSeparator());
        sb.append("Unsat : ").append(unsat).append(System.lineSeparator());
        sb.append("CPU : ").append(cpu).append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
