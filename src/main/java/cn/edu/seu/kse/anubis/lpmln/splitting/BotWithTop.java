package cn.edu.seu.kse.anubis.lpmln.splitting;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.AugmentedSubsetSolver;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 许鸿翔 on 2017/9/10.
 */
public class BotWithTop {
    public static String baseDir = "/home/wangbin/experiments/splitting-bird";
    public static void executeExperiment(int splitCount,int splitMaxCount){
        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, "Spe start range"+splitCount+" to "+splitMaxCount);
        int expCount = splitMaxCount-splitCount+1;
        double[] botTime = new double[expCount];
        double[] topTime = new double[expCount];
        for(int i=0;i<expCount;i++){
            BotWithTopExperiment bwt = new BotWithTopExperiment();
            bwt.getRealAnswerset(i+splitCount);
            botTime[i] = bwt.botTime;
            topTime[i] = bwt.topTime;
        }
        File outFile = new File(baseDir+"/timeCost.txt");
        outFile.getParentFile().mkdir();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            StringBuilder toWrite = new StringBuilder("taskName\tbotTime\ttopTime\n");
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
        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" start.");
        Set<String> realAnswerset = new ConcurrentSkipListSet<>();
        AugmentedSubsetSolver solver = new AugmentedSubsetSolver();

        lastPoint = new Date().getTime();
        List<WeightedAnswerSet> wasList = solver.call(botFile.getAbsolutePath());
        botTime = new Date().getTime()-lastPoint;

        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" done.");
        ExecutorService solverService = Executors.newFixedThreadPool(16);

        lastPoint = new Date().getTime();
        if(wasList.size()==topFile.size()){
            //correct count
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" count matches.");
            for(int i=0;i<wasList.size();i++){
                solverService.execute(new SolveTop(realAnswerset,topFile.get(i),wasList.get(i).toString()));
            }
        }
        else{
            for (File top : topFile) {
                solverService.execute(new SolveTop(realAnswerset,top,""));
            }
        }
        while(((ThreadPoolExecutor)solverService).getActiveCount()!=0){ }
        topTime = new Date().getTime()-lastPoint;
        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" top done.");
        solverService.shutdown();
        return realAnswerset;
    }

    public Set<String> getRealAnswerset(int birdSeq){
        String path = "baseDir/bird"+birdSeq+"/";
        File botFile = new File(path+"bot-trans.txt");
        List<File> topFile = new ArrayList<>();
        int botBird = birdSeq/3;
        int topBird = birdSeq-botBird;
        int topFileCount = (int)Math.pow(2,botBird);
        for(int i=0;i<topFileCount;i++){
            topFile.add(new File(path+"pe-trans-"+i+".txt"));
        }
        return getRealAnswerset(botFile,topFile);
    }

    public Set<String> getRealAnswerset(List<File> topFile){
        lastPoint = new Date().getTime();
        ExecutorService solverService = Executors.newFixedThreadPool(16);
        Set<String> realAnswerset = new ConcurrentSkipListSet<>();
        for (File top : topFile) {
            solverService.execute(new SolveTop(realAnswerset,top,""));
        }
        while(((ThreadPoolExecutor)solverService).getActiveCount()!=0){ }
        topTime = new Date().getTime()-lastPoint;
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
