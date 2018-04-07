package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.independentway.IndependentSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class SplittingSolver extends LPMLNBaseSolver {
    private LPMLNSolver bottomSolver;
    private List<LPMLNSolver> topSolvers;
    public static double k=0.5;
    private LpmlnThreadPool threadPool;
    private String arch;

    public SplittingSolver() {
        bottomSolver = new LPMLNBaseSolver();
        topSolvers = new ArrayList<>();
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        this.arch = "";
    }

    public SplittingSolver(String arch) {
        topSolvers = new ArrayList<>();
        threadPool = new LpmlnThreadPool("SplittingSolver!");
        this.arch = arch;
        bottomSolver = chooseSolver(arch);
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program){
        lpmlnProgram = program;

        // 1. 分割程序，需要用到bottom、top、U
        Splitter splitter = new Splitter();
        splitter.split(program, k);
        LpmlnProgram bottom = splitter.getBottom();
        LpmlnProgram top = splitter.getTop();
        Set<String> U = splitter.getU();

        // 2. 求解bottom
        List<WeightedAnswerSet> Xs = bottomSolver.solveProgram(bottom);

        // 3. 并行求Partial Evaluation
        Xs.forEach(AS -> {
            PESolver solver = new PESolver(top, U, AS, arch);
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
}
