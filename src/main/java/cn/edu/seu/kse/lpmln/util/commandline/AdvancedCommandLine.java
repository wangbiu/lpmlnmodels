package cn.edu.seu.kse.lpmln.util.commandline;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 王彬 on 2017/3/25.
 */
public class AdvancedCommandLine extends BaseCommandLine {

    protected LinkedBlockingDeque<String> progOut;
    protected int ansProcessorNums=2;
    protected List<ClingoResultProcessor> processors;
    protected List<WeightedAnswerSet> was=null;
    final public String killSig="--END of Process--";
    private static Logger logger= LogManager.getLogger(AdvancedCommandLine.class.getName());

    public AdvancedCommandLine(LpmlnThreadPool threadPool) {
        super(threadPool);
        progOut = new LinkedBlockingDeque<String>();
    }

    @Override
    protected void startResultProcess(BufferedReader br) throws IOException, InterruptedException {
        processors=new ArrayList<>();
        ClingoResultProcessor crp;
        for(int i=0;i<ansProcessorNums;i++){
            crp=new ClingoResultProcessor(progOut,killSig);
            processors.add(crp);
            threadPool.execute(crp);
        }

        String line=null;
        String linespe=System.lineSeparator();
        boolean isBegin=true;
        int cnt=1;
        StringBuilder res=null;

        while((line=br.readLine())!=null){
            if(isBegin){
                if(line.startsWith("Answer:")){
                    isBegin=false;
                    res=new StringBuilder();
                }else {
                    continue;
                }
            }
            res.append(line);
            res.append(linespe);
            if(cnt == 300){
                if(res.indexOf("OPTIMUM FOUND")<0){
                    progOut.add(res.toString());
                    res=new StringBuilder();
                    cnt=0;
                }
            }
            cnt++;
        }
        if(!isBegin){
            progOut.add(res.toString());
        }
        int exitVal = cmdProcess.waitFor();
        //进程结束后发出终止信号
        for(int i=0;i<ansProcessorNums;i++){
            progOut.add(killSig);
        }

    }

    @Override
    protected void stopResultProcess(){
        threadPool.waitDone();
        logger.debug("command execution done.");
        was=new ArrayList<>();
        for(ClingoResultProcessor crp:processors){
            if(crp.getWas() != null){
                was.addAll(crp.getWas());
            }

            if(crp.isHascputime()){
                cputime=crp.getCputime();
            }
        }
    }

    @Override
    protected void clearStatus(){
        super.clearStatus();
        processors.clear();
        processors=null;

        progOut.clear();
        progOut=null;
    }


    public List<WeightedAnswerSet> getWas() {
        return was;
    }

    public void setWas(List<WeightedAnswerSet> was) {
        this.was = was;
    }

    public List<ClingoResultProcessor> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ClingoResultProcessor> processors) {
        this.processors = processors;
    }

    public LinkedBlockingDeque<String> getProgOut() {
        return progOut;
    }

    public int getAnsProcessorNums() {
        return ansProcessorNums;
    }

    public void setAnsProcessorNums(int ansProcessorNums) {
        this.ansProcessorNums = ansProcessorNums;
    }

    public double getCputime() {
        return cputime;
    }

    public void setCputime(double cputime) {
        this.cputime = cputime;
    }
}
