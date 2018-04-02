package cn.edu.seu.kse.lpmln.solver.parallel.independentway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class IndependentSolver extends LPMLNBaseSolver implements LPMLNSolver {
    private LpmlnThreadPool threadPool;
    private static final String THREAD_POOL_NAME="IndependentSolver";
    private List<LPMLNSolver> solvers;
    private List<List<WeightedAnswerSet>> subWeightedAs;
    private static Logger logger = LogManager.getLogger(IndependentSolver.class.getName());

    public IndependentSolver(){
        threadPool = new LpmlnThreadPool(THREAD_POOL_NAME);
        solvers = new ArrayList<>();
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program){
        lpmlnProgram = program;

        List<LpmlnProgram> subprograms = IndependentSplitter.split(program);
        logger.info("IndependentSolver into {} subprograms.",subprograms.size());
        subprograms.forEach(program1 -> {
            //TODO:使用反射创建
            LPMLNSolver solver = new LPMLNBaseSolver();
            solver.setLpmlnProgram(program);
            solvers.add(solver);
            threadPool.execute(solver);
        });

        threadPool.waitDone();
        mergeResult();
        weightedAs = calculateProbability(filtWas(weightedAs));
        return weightedAs;
    }

    /**
     * 排列组合生成答案
     */
    private void mergeResult(){
        subWeightedAs = new ArrayList<>();
        solvers.forEach(solver->subWeightedAs.add(solver.getAllWeightedAs()));

        Integer[] permutation = new Integer[subWeightedAs.size()];
        do {

        }while(nextPermutation(permutation));
    }

    private void merge(WeightedAnswerSet to,WeightedAnswerSet from){

    }

    private boolean nextPermutation(Integer[] permutation){
        int idx=permutation.length-1;
        while(idx>=0&&permutation[idx]==subWeightedAs.get(idx).size()-1){
            idx--;
        }
        if(idx<0){
            return false;
        }
        permutation[idx]++;
        for(int i=idx+1;i<permutation.length;i++){
            permutation[idx]=0;
        }
        return true;
    }

}
