package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.Clingo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSolver extends Clingo4 {
    private int threadNums;
    private List<String> translatedFiles;
    public AugmentedSolver(){
        setTranslatedFiles(new ArrayList<>());
        setThreadNums(Runtime.getRuntime().availableProcessors());
    }
    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        //不依赖于rulefile，从translatedFiles获取
        List<WeightedAnswerSet> finalWas = new ArrayList<>();
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(threadNums);
            for (String translatedFile : translatedFiles) {
                AugmentedSubsetSolver subsetSolver = new AugmentedSubsetSolver();
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return finalWas;
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
}
