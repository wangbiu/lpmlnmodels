package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public double k=Runtime.getRuntime().availableProcessors();
    private LpmlnThreadPool threadPool;
    public enum SPLIT_TYPE{SPLIT_ORIGINAL, SPLIT_LIT, SPLIT_BOT,SPLIT_DYNAMIC}
    private String arch;
    private SPLIT_TYPE policy = SPLIT_TYPE.SPLIT_DYNAMIC;
    private Splitter outSplitter;
    private Logger logger = LogManager.getLogger(SplittingSolver.class.getName());

    @Override
    public void run() {
        solveProgram(lpmlnProgram);
    }

    public SplittingSolver() {
        topSolvers = new ArrayList<>();
        this.arch = "";
    }

    public SplittingSolver(Splitter outSplitter) {
        topSolvers = new ArrayList<>();
        this.arch = "";
        this.outSplitter = outSplitter;
    }

    public SplittingSolver(String arch) {
        topSolvers = new ArrayList<>();
        this.arch = arch;
    }

    public SplittingSolver(String arch,Splitter outSplitter) {
        topSolvers = new ArrayList<>();
        this.arch = arch;
        this.outSplitter = outSplitter;
    }

    @Override
    public void executeSolving(){
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        // 1. 分割程序，需要用到bottom、top、U
        Splitter splitter;
        if(outSplitter!=null){
            splitter = outSplitter;
            outSplitter = null;
        }else{
            switch (policy){
                case SPLIT_ORIGINAL:
                    splitter = new Splitter();
                    break;
                default:
                    splitter = new KSplitter(this.policy);
                    break;
            }
        }
        splitter.split(lpmlnProgram, k);
        LpmlnProgram bottom = splitter.getBottom();
        LpmlnProgram top = splitter.getTop();
        Set<String> U = splitter.getU();

        // 2. 求解bottom
        bottomSolver = chooseSolver(arch,bottom);
        bottomSolver.setCalculatePossibility(false);
        bottomSolver.setFiltResult(false);
        List<WeightedAnswerSet> Xs = bottomSolver.solveProgram(bottom);

        System.out.println("Bottom:"+Xs.size());
        topSolvers.clear();
        // 3. 并行求Partial Evaluation
        Xs.forEach(AS -> {
            PESolver solver = new PESolver(top.clone(), U,splitter, AS,arch);
            topSolvers.add(solver);
            solver.setFiltResult(false);
            solver.setCalculatePossibility(false);
//            if(solver.getSolver() instanceof AugmentedSolver){
//                ((AugmentedSolver) solver.getSolver()).setPolicy(AugmentedSubsetPartitioner.SPLIT_TYPE.DIVIDE_RANDOM);
//            }
            threadPool.execute(solver);
        });

        // 4. 等待求解完成
        threadPool.waitDone();

        // 5. 产生结果
        weightedAs = collectWas();
    }

    private int countRule(List<Rule> rules){
        int count=0;
        for (Rule rule : rules) {
            if (!rule.isUnWeighted()&&rule.isSoft()){
                count++;
            }
        }
        return count;
    }

    protected List<WeightedAnswerSet> collectWas(){
        //收集过滤回答集
        List<WeightedAnswerSet> collectedWas = new ArrayList<>();
        topSolvers.forEach(solver -> {
            if(LPMLNApp.isDebugging()){
                logger.debug(solver.getAllWeightedAs().size()+" aug was collected");
            }
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
