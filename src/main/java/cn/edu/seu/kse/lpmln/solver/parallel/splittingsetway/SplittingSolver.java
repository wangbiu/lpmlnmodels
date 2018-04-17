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
    public enum SPLIT_TYPE{SPLIT_ORIGINAL, SPLIT_LIT, SPLIT_BOT}
    private String arch;
    private SPLIT_TYPE policy = SPLIT_TYPE.SPLIT_LIT;

    public void run() {
        solveProgram(lpmlnProgram);
    }

    public SplittingSolver() {
        bottomSolver = new LPMLNBaseSolver();
        topSolvers = new ArrayList<>();
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        this.arch = "";
    }

    public SplittingSolver(String arch) {
        topSolvers = new ArrayList<>();
        this.arch = arch;
        bottomSolver = chooseSolver(arch);
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program){
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        lpmlnProgram = program;

        // 1. 分割程序，需要用到bottom、top、U
        Splitter splitter;
        switch (policy){
            case SPLIT_ORIGINAL:
                splitter = new Splitter();
                break;
            case SPLIT_LIT:
                splitter = new KSplitter(SPLIT_TYPE.SPLIT_LIT);
                break;
            case SPLIT_BOT:
                splitter = new KSplitter(SPLIT_TYPE.SPLIT_BOT);
                break;
            default:
                splitter = new Splitter();
                break;
        }
        splitter.split(program, k);
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
        weightedAs = calculateProbability(filtWas(collectWas()));

        return weightedAs;
    }

    protected List<WeightedAnswerSet> collectWas(){
        //收集过滤回答集
        List<WeightedAnswerSet> collectedWas = new ArrayList<>();
        topSolvers.forEach(solver -> collectedWas.addAll(solver.getAllWeightedAs()));
        return collectedWas;
    }

    public static Set<String> getSplittingSet(LpmlnProgram program){
//        Map<String,Set<String>> dependency = LpmlnProgramHelper.getDependency(program);
//        Map<String,Set<String>> reachable = new HashMap<>();
//        Set<String> u;
//        int minDiff=program.getRules().size();
//        int kpie = k*program.getRules().set();
//        dependency.keySet().forEach(from->{
//            Set<String> visited = new HashSet<>();
//            LinkedList<String> togo = new LinkedList<>();
//            visited.add(from);
//            togo.offer(from);
//            while(togo.size()>0){
//                String next = togo.poll();
//                if(!visited.contains(next)){
//                    dependency.get(next).forEach(tovisit->togo.offer(tovisit));
//                    visited.add(next);
//                }
//            }
//            reachable.put(from,visited);
//        });
//        reachable.values().forEach(tempu->{
//        });
        //TODO:next
        return null;
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
