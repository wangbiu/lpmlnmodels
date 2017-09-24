package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.Clingo4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSolver extends Clingo4 {
    //通过增强子集 并行推理
    //Wang B, Zhang Z. A Parallel LP^{MLN Solver: Primary Report[C]// Aspocp. 2017.
    private int threadNums;
    private List<String> translatedFiles;
    private List<ExtraWeight> extraWeights;
    private List<AugmentedSubsetSolver> solvers;
    private boolean deleteTranslatedFiles = true;
    public AugmentedSolver(){
        setTranslatedFiles(new ArrayList<>());
        setThreadNums(Runtime.getRuntime().availableProcessors());
        solvers = new ArrayList<>();
        setExtraWeights(new ArrayList<>());
    }
    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        //不依赖于rulefile，从translatedFiles获取
        List<WeightedAnswerSet> finalWas;
        solve();
        finalWas = calculateProbability(filtWas(collectWas()));
        weightedAs = finalWas;
        return finalWas;
    }

    protected void solve(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(threadNums);
            assert getExtraWeights().size()==translatedFiles.size();
            for (int i = 0; i< getExtraWeights().size(); i++) {
                String translatedFile = translatedFiles.get(i);
                ExtraWeight extraWeight = getExtraWeights().get(i);
                AugmentedSubsetSolver subsetSolver = new AugmentedSubsetSolver();
                solvers.add(subsetSolver);
                subsetSolver.setExtraWeight(extraWeight);
                subsetSolver.setRuleFile(translatedFile);
                executorService.submit(subsetSolver);
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(deleteTranslatedFiles){
            translatedFiles.forEach(file->{
                new File(file).delete();
            });
        }
    }

    protected List<WeightedAnswerSet> collectWas(){
        //收集过滤回答集
        List<WeightedAnswerSet> collectedWas = new ArrayList<>();
        for (AugmentedSubsetSolver ass : solvers) {
            ExtraWeight ew = ass.getExtraWeight();
            for (WeightedAnswerSet was : ass.getWeightedAnswerSets()) {
                List<Integer> weight = was.getWeights();
                //weight.set(0,weight.get(0)+ew.softWeight);
                //weight.set(1,weight.get(1)+ew.hardWeight);
                collectedWas.add(was);
            }
        }
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

    public List<ExtraWeight> getExtraWeights() {
        return extraWeights;
    }

    public void setExtraWeights(List<ExtraWeight> extraWeights) {
        this.extraWeights = extraWeights;
    }
}

class ExtraWeight{
    protected int softWeight;
    protected int hardWeight;
    public ExtraWeight(int softWeight,int hardWeight){
        this.softWeight = softWeight;
        this.hardWeight = hardWeight;
    }
}
