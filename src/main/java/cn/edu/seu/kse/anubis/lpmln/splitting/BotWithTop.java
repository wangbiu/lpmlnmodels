package cn.edu.seu.kse.anubis.lpmln.splitting;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public static void executeExperiment(int splitCount,int splitMaxCount){
        int expCount = splitMaxCount-splitCount+1;
        double[] botTime = new double[expCount];
        double[] topTime = new double[expCount];
        for(int i=0;i<expCount;i++){
            BotWithTopExperiment bwt = new BotWithTopExperiment();
            bwt.getRealAnswerset(i+splitCount);
            botTime[i] = bwt.botTime;
            topTime[i] = bwt.topTime;
        }
        File outFile = new File("/home/wangbin/experiments/splitting-bird/timeCost.txt");
        outFile.getParentFile().mkdir();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            StringBuilder toWrite = new StringBuilder("tashName\tbotTime\ttopTime\n");
            bw.write(toWrite.toString());
            for(int i=0;i<expCount;i++){
                toWrite = new StringBuilder("bird");
                toWrite.append(i+splitCount);
                toWrite.append("\t");
                toWrite.append(botTime[i]/1000);
                toWrite.append("\t");
                toWrite.append(topTime[i]/1000);
                toWrite.append("\n");
            }
            bw.write(toWrite.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BotWithTopExperiment{
    protected long lastPoint;
    public long topTime;
    public long botTime;
    public Set<String> getRealAnswerset(File botFile, List<File> topFile) {
        Set<String> realAnswerset = new ConcurrentSkipListSet<>();
        AugmentedSubsetSolver solver = new AugmentedSubsetSolver();

        lastPoint = new Date().getTime();
        List<WeightedAnswerSet> wasList = solver.call(botFile.getAbsolutePath());
        botTime = new Date().getTime()-lastPoint;

        ExecutorService solverService = Executors.newFixedThreadPool(16);
        assert wasList.size()==topFile.size();

        lastPoint = new Date().getTime();
        for(int i=0;i<wasList.size();i++){
            solverService.execute(new SolveTop(realAnswerset,topFile.get(i),wasList.get(i).toString()));
        }
        while(((ThreadPoolExecutor)solverService).getActiveCount()!=0){ }
        topTime = new Date().getTime()-lastPoint;

        solverService.shutdown();
        return realAnswerset;
    }

    public Set<String> getRealAnswerset(int birdSeq){
        String path = "/home/wangbin/experiments/splitting-bird/bird"+birdSeq+"/";
        File botFile = new File(path+"bot-trans.txt");
        List<File> topFile = new ArrayList<>();
        int botBird = birdSeq/3;
        int topBird = birdSeq-botBird;
        int topFileCount = (int)Math.pow(4,botBird);
        for(int i=0;i<topFileCount;i++){
            topFile.add(new File(path+"pe-trans-"+i+".txt"));
        }
        return getRealAnswerset(botFile,topFile);
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
