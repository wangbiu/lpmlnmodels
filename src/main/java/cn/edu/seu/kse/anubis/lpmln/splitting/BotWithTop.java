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
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 许鸿翔 on 2017/9/10.
 */
public class BotWithTop {
    public static String baseDir = "/home/wangbin/experiments/splitting-bird";
    public static void executeExperiment(int splitCount,int splitMaxCount){
        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, "Spe start range "+splitCount+" to "+splitMaxCount);
        int expCount = splitMaxCount-splitCount+1;
        double[] botTime = new double[expCount];
        double[] topTime = new double[expCount];

        new BotWithTopExperiment().getRealAnswerset(10);//预热class
        new BotWithTopExperiment().getRealAnswerset(10);//预热class


        for(int i=0;i<expCount;i++){
            BotWithTopExperiment bwt = new BotWithTopExperiment();
            System.out.println("exp"+(splitCount+i));
            bwt.getRealAnswerset(i+splitCount);
            botTime[i] = bwt.botTime;
            topTime[i] = bwt.topTime;
        }
        File outFile = new File("timeCost.txt");
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
                bw.write(toWrite.toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeTopExperiment(int splitCount,int splitMaxCount){
        new BotWithTopExperiment().getRealAnswerset(10);//预热class
        new BotWithTopExperiment().getRealAnswerset(10);//预热class

        int expCount = splitMaxCount-splitCount+1;
        double[] topTime = new double[expCount];
        List<File> topFile = new ArrayList<>();
        for(int i=1;i<splitCount;i++){
            topFile.add(new File(baseDir+"/independent/bird-trans-"+i+".txt"));
        }
        for(int i=splitCount;i<=splitMaxCount;i++){
            topFile.add(new File(baseDir+"/independent/bird-trans-"+i+".txt"));
            BotWithTopExperiment bwt = new BotWithTopExperiment();
            bwt.getRealAnswerset(topFile);
            topTime[i-splitCount] = bwt.topTime;
        }

        File outFile = new File("timeCostTop.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            StringBuilder toWrite = new StringBuilder("taskName\ttopTime\n");
            bw.write(toWrite.toString());
            for(int i=0;i<expCount;i++){
                toWrite = new StringBuilder("bird");
                toWrite.append(i+splitCount);
                toWrite.append("\t");
                toWrite.append(topTime[i]/1000);
                toWrite.append("\n");
                bw.write(toWrite.toString());
            }

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
        System.out.println("wasList.size()"+wasList.size());
        System.out.println("topFile.size()"+topFile.size());
        if(wasList.size()==topFile.size()){
            //correct count
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" count matches.");
            for(int i=0;i<wasList.size();i++){
                solverService.execute(new SolveTop(realAnswerset,topFile.get(i),wasList.get(i)));
            }
        }
        else{
            for (File top : topFile) {
                solverService.execute(new SolveTop(realAnswerset,top,null));
            }
        }
        try {
            solverService.shutdown();
            solverService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        topTime = new Date().getTime()-lastPoint;
        Logger.getLogger(CommandLineExecute.class.getName()).log(Level.INFO, botFile.toString()+" top done.");
        solverService.shutdown();
        return realAnswerset;
    }

    //辅助函数，不直接用
    public Set<String> getRealAnswerset(int birdSeq){
        String path = BotWithTop.baseDir+"/bird"+birdSeq+"/";
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
        List<SolveTop> solverList = new ArrayList<>();
        List<WeightedAnswerSet> totalWas = new ArrayList<>();

        for (File top : topFile) {
            SolveTop solver = new SolveTop(realAnswerset,top,null);
            solverService.execute(solver);
            solverList.add(solver);
        }
        try {
            solverService.shutdown();
            solverService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (SolveTop st : solverList) {
            totalWas.addAll(st.topWasList);
        }

        topTime = new Date().getTime()-lastPoint;
        return realAnswerset;
    }
}

class SolveTop extends Thread{
    protected File topFile;
    protected Set<String> res;
    protected WeightedAnswerSet botWas;
    protected List<WeightedAnswerSet> topWasList;
    public SolveTop(Set<String> resSet,File file,WeightedAnswerSet bot){
        res = resSet;
        topFile = file;
        botWas = bot;
    }

    @Override
    public void run(){
        AugmentedSubsetSolver solver = new AugmentedSubsetSolver();
        System.out.println("topFile :"+topFile.getAbsolutePath());
        topWasList = solver.call(topFile.getAbsolutePath());
        if(botWas!=null){
            for (WeightedAnswerSet was: topWasList) {
                was.getAnswerSet().getLiterals().addAll(botWas.getAnswerSet().getLiterals());
            }
        }
        System.out.println(topFile+"done");
    }
}
