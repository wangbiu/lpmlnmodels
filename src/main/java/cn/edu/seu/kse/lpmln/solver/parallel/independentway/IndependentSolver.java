package cn.edu.seu.kse.lpmln.solver.parallel.independentway;

import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;
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
public class IndependentSolver extends LPMLNBaseSolver{
    private LpmlnThreadPool threadPool;
    private static final String THREAD_POOL_NAME="IndependentSolver";
    private List<LPMLNSolver> solvers;
    private List<List<WeightedAnswerSet>> subWeightedAs;
    private static Logger logger = LogManager.getLogger(IndependentSolver.class.getName());
    private String arch;

    public IndependentSolver() {
        threadPool = new LpmlnThreadPool(THREAD_POOL_NAME);
        solvers = new ArrayList<>();
        this.arch = "";
    }

    public IndependentSolver(String arch){
        threadPool = new LpmlnThreadPool(THREAD_POOL_NAME);
        solvers = new ArrayList<>();
        this.arch = arch;
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program){
        lpmlnProgram = program;

        List<LpmlnProgram> subprograms = IndependentSplitter.split(program);
        logger.info("IndependentSolver into {} subprograms.",subprograms.size());
        subprograms.forEach(subprogram -> {
            //TODO:使用反射创建
            LPMLNSolver solver = chooseSolver(arch);
            solver.setLpmlnProgram(subprogram);
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
        weightedAs = new ArrayList<>();
        solvers.forEach(solver->subWeightedAs.add(solver.getAllWeightedAs()));
        if(subWeightedAs.size()==0){
            WeightedAnswerSet empty = new WeightedAnswerSet();
            empty.getWeights().add(0);
            empty.getWeights().add(0);
            weightedAs.add(empty);
            return;
        }
        int[] permutation = new int[subWeightedAs.size()];
        do {
            if(subWeightedAs.get(0).size()==0){
                throw new SolveException("IndependentSolver no stable model.");
            }
            WeightedAnswerSet realAnswerSet = subWeightedAs.get(0).get(permutation[0]).clone();
            for(int i=1;i<permutation.length;i++){
                if(!merge(realAnswerSet,subWeightedAs.get(i).get(permutation[i]))){
                    throw new SolveException("IndependentSolver merge fail");
                }
            }
            weightedAs.add(realAnswerSet);
        }while(nextPermutation(permutation));
    }

    private boolean merge(WeightedAnswerSet to,WeightedAnswerSet from){
        //factor子程序间一致,probability会重新计算
        //独立分割不会引发不一致
        to.getAnswerSet().getLiterals().addAll(from.getAnswerSet().getLiterals());
        to.getWeights().set(0,to.getWeights().get(0)+from.getWeights().get(0));
        to.getWeights().set(1,to.getWeights().get(1)+from.getWeights().get(1));
        return true;
    }

    private boolean nextPermutation(int[] permutation){
        int idx=permutation.length-1;
        while(idx>=0&&permutation[idx]==subWeightedAs.get(idx).size()-1){
            idx--;
        }
        if(idx<0){
            return false;
        }
        permutation[idx]++;
        for(int i=idx+1;i<permutation.length;i++){
            permutation[i]=0;
        }
        return true;
    }

}
