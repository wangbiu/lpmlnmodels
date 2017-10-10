package cn.edu.seu.kse.lpmln.solver.parallel;

import cn.edu.seu.kse.lpmln.solver.LPMLNBaseSolver;

/**
 * Created by 许鸿翔 on 2017/10/10.
 */
public class BaseParallelSolver extends LPMLNBaseSolver {
    protected int threadNums;
    public BaseParallelSolver(){

    }

    public BaseParallelSolver(int threadNums){
        this.threadNums = threadNums;
    }

    public void call(){}

    public void prepare(){}

    public void collectAs(){}
}
