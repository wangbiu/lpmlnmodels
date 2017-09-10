package cn.edu.seu.kse.anubis.lpmln.splitting;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 许鸿翔 on 2017/9/10.
 */
public class BotWithTop {
    public static Set<String> getRealAnswerset(File botFile, List<File> topFile) {
        Set<String> realAnswerset = new ConcurrentSkipListSet<>();
        AugmentedSubsetSolver solver = new AugmentedSubsetSolver();

        List<WeightedAnswerSet> wasList = solver.call(botFile.getAbsolutePath());
        ExecutorService solverService = Executors.newFixedThreadPool(16);
        assert wasList.size()==topFile.size();
        for(int i=0;i<wasList.size();i++){
            solverService.execute(new SolveTop(realAnswerset,topFile.get(i),wasList.get(i).toString()));
        }
        while(((ThreadPoolExecutor)solverService).getActiveCount()!=0){ }
        solverService.shutdown();
        return realAnswerset;
    }
}

class SolveTop extends Thread{
    protected File topFile;
    protected Set<String> res;
    protected String botWas;
    public SolveTop(Set<String> resSet,File file,String bot){
        res = resSet;
        topFile = file;
        botWas = bot;
    }

    @Override
    public void run(){
        AugmentedSubsetSolver solver = new AugmentedSubsetSolver();
        List<WeightedAnswerSet> wasList = solver.call(topFile.getAbsolutePath());
        for (WeightedAnswerSet topWas: wasList) {
            res.add(botWas+topWas.toString());//结果字符串拼接
        }
    }
}
