package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class SplittingSolver extends LPMLNBaseSolver implements Runnable {
    private LPMLNSolver bottomSolver;
    private List<PESolver> topSolvers;
    public double k=0.5;
    private LpmlnThreadPool threadPool;
    public enum SPLIT_TYPE{SPLIT_ORIGINAL, SPLIT_LIT, SPLIT_BOT,SPLIT_DYNAMIC}
    private String arch;
    private SPLIT_TYPE policy = SPLIT_TYPE.SPLIT_DYNAMIC;

    @Override
    public void run() {
        solveProgram(lpmlnProgram);
    }

    public SplittingSolver() {
        bottomSolver = new LPMLNBaseSolver();
        topSolvers = new ArrayList<>();
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        bottomSolver = chooseSolver(arch);
        bottomSolver.setCalculatePossibility(false);
        bottomSolver.setFiltResult(false);
        this.arch = "";
    }

    public SplittingSolver(String arch) {
        topSolvers = new ArrayList<>();
        this.arch = arch;
        bottomSolver = chooseSolver(arch);
        bottomSolver.setCalculatePossibility(false);
        bottomSolver.setFiltResult(false);
    }

    @Override
    public void executeSolving(){
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        // 1. 分割程序，需要用到bottom、top、U
        Splitter splitter;
        switch (policy){
            case SPLIT_ORIGINAL:
                splitter = new Splitter();
                break;
            default:
                splitter = new KSplitter(this.policy);
                break;
        }
        splitter.split(lpmlnProgram, k);
        LpmlnProgram bottom = splitter.getBottom();
        LpmlnProgram top = splitter.getTop();
        Set<String> U = splitter.getU();

        // 2. 求解bottom
        List<WeightedAnswerSet> Xs = bottomSolver.solveProgram(bottom);

        System.out.println("Bottom:"+Xs.size());
        topSolvers.clear();
        // 3. 并行求Partial Evaluation
        Xs.forEach(AS -> {
            PESolver solver = new PESolver(top.clone(), U, AS,arch);
            topSolvers.add(solver);
            threadPool.execute(solver);
        });

        // 4. 等待求解完成
        threadPool.waitDone();

        // 5. 产生结果
        weightedAs = collectWas();
    }

    protected List<WeightedAnswerSet> collectWas(){
        //收集过滤回答集
        List<WeightedAnswerSet> collectedWas = new ArrayList<>();
        topSolvers.forEach(solver -> {
            System.out.println(solver.getAllWeightedAs().size()+"spl was collected");
            collectedWas.addAll(solver.getAllWeightedAs());
        });
        return collectedWas;
    }

    public SPLIT_TYPE getPolicy() {
        return policy;
    }

    public void setPolicy(SPLIT_TYPE policy) {
        this.policy = policy;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }
}
