package cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway;

import cn.edu.seu.kse.lpmln.experiment.util.TimeStatistics;
import cn.edu.seu.kse.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class AugmentedSolver extends LPMLNBaseSolver {
    //通过增强子集 并行推理
    //Wang B, Zhang Z. A Parallel LP^{MLN Solver: Primary Report[C]// Aspocp. 2017.
    private int threadNums;
    private List<String> translatedFiles;
    private List<AugmentedSubsetSolver> subsetSolvers;
    private boolean deleteTranslatedFiles = true;
    private LpmlnThreadPool threadPool;
    private AugmentedSubsetPartitioner partitioner;
    private List<AugmentedSubset> augmentedSubsets;
    private Logger logger = LogManager.getLogger(AugmentedSolver.class.getName());

    public AugmentedSolver(){
        threadPool = new LpmlnThreadPool("AugmentedSolver");
        translatedFiles = new ArrayList<>();
        threadNums = Runtime.getRuntime().availableProcessors();
        subsetSolvers = new ArrayList<>();
        augmentedSubsets = new ArrayList<>();
        //设定划分方式
        partitioner = new AugmentedSubsetPartitioner();
    }

    @Override
    public List<WeightedAnswerSet> solve(File ruleFile){
        times = new TimeStatistics();
        //开始计时
        times.totalTime.start();

        times.solveTime.start();
        //解析LPMLN程序
        lpmlnProgram = parse(ruleFile);

        times.parallelTime.start();
        //拆分为多个增强子集
        augmentedSubsets = partitioner.partition(lpmlnProgram,threadNums);
        times.parallelTime.pause();

        //增强子集求解
        augmentedSubsets.forEach(subset -> {
            AugmentedSubsetSolver subsetSolver = new AugmentedSubsetSolver(subset);
            subsetSolvers.add(subsetSolver);
            threadPool.execute(subsetSolver);
        });

        //等待增强子集求解完成
        threadPool.waitDone();

        times.parallelTime.restart();
        weightedAs =calculateProbability(filtWas(collectWas()));
        times.parallelTime.stop();

        //结束计时
        times.solveTime.stop();
        times.totalTime.stop();
        return weightedAs;
    }

    protected List<WeightedAnswerSet> collectWas(){
        //收集过滤回答集
        List<WeightedAnswerSet> collectedWas = new ArrayList<>();
        subsetSolvers.forEach(solver->{
            collectedWas.addAll(solver.getAllWeightedAs());
            logger.debug(solver.getAllWeightedAs().size()+" was collected");
        });
        return collectedWas;
    }

    public List<String> getTranslatedFiles() {
        return translatedFiles;
    }

    public void setTranslatedFiles(List<String> translatedFiles) {
        this.translatedFiles = translatedFiles;
    }

    public int getThreadNums() {
        return threadNums;
    }

    public void setThreadNums(int threadNums) {
        this.threadNums = threadNums;
    }

    public boolean isDeleteTranslatedFiles() {
        return deleteTranslatedFiles;
    }

    public void setDeleteTranslatedFiles(boolean deleteTranslatedFiles) {
        this.deleteTranslatedFiles = deleteTranslatedFiles;
    }
}
