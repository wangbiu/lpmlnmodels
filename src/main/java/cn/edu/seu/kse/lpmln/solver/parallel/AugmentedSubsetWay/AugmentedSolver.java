package cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.parallel.ConcurrentSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.BaseParallelSolver;
import cn.edu.seu.kse.lpmln.translator.BaseTranslator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSolver extends BaseParallelSolver {
    //通过增强子集 并行推理
    //Wang B, Zhang Z. A Parallel LP^{MLN Solver: Primary Report[C]// Aspocp. 2017.
    private int threadNums;
    private List<String> translatedFiles;
    private List<ConcurrentSolver> solvers;
    private boolean deleteTranslatedFiles = true;
    public AugmentedSolver(){

    }

    public AugmentedSolver(BaseTranslator translator, List<Rule> rules){
        super(translator,rules);
    }

    public void init(){
        setTranslatedFiles(new ArrayList<>());
        setThreadNums(Runtime.getRuntime().availableProcessors());
        solvers = new ArrayList<>();
    }

    @Override
    public void prepare(){
        super.prepare();
        translator.translate(rules);
        AugmentedSubsetPartitioner partitioner = new AugmentedSubsetPartitioner(this);
        partitioner.partition(rules,translator);
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
            for (int i = 0; i< translatedFiles.size(); i++) {
                String translatedFile = translatedFiles.get(i);
                ConcurrentSolver subsetSolver = new ConcurrentSolver();
                solvers.add(subsetSolver);
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
        for (ConcurrentSolver ass : solvers) {
            for (WeightedAnswerSet was : ass.getWeightedAnswerSets()) {
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
}
