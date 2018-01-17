package cn.edu.seu.kse.lpmln.util.commandLine;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.ErrorStreamLogger;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 王彬 on 2017/3/25.
 */
public class AdvancedCommandLine {
    final public static int WINDOWS_NT=1;
    final public static int UNIX_LIKE=0;
    protected LinkedBlockingDeque<String> progOut;
    protected int ansProcessorNums=2;
    protected List<ClingoResultProcessor> processors;
    protected List<WeightedAnswerSet> was=null;
    protected double cputime=0;
    final public String killSig="--END of Process--";
    final private static Logger logger= LogManager.getLogger(AdvancedCommandLine.class.getName());
    private LpmlnThreadPool threadPool;


    public AdvancedCommandLine(LpmlnThreadPool threadPool) {
        this.setThreadPool(threadPool);
        progOut = new LinkedBlockingDeque<String>();
    }

    protected int getOS(){
        int os;
        if(SystemUtils.IS_OS_WINDOWS){
            os=WINDOWS_NT;
        }else if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_UNIX){
            os=UNIX_LIKE;
        }else{
            throw new RuntimeException("不支持的操作系统!");
        }

        return os;
    }

    protected String[] getCommandInterperter(){
        int osType=getOS();
        String[] cmd =new String [3];
        if(osType==WINDOWS_NT){
            cmd[0]="cmd.exe";
            cmd[1]="/c";
        }
        else if(osType==UNIX_LIKE){
            cmd[0]="sh";
            cmd[1]="-c";
        }
        return cmd;
    }

    public void call(String command){
        String[] cmd = getCommandInterperter();
        cmd[2]=command;
        BufferedReader br=null;
        Process p= null;
        StringBuilder res=null;
        String as=null;

        try {
//            System.out.println(Arrays.asList(cmd));
            p=Runtime.getRuntime().exec(cmd);

            //标准错误流
            final InputStream stderr=p.getErrorStream();

            //单独的读取错误流的进程
            threadPool.execute(new ErrorStreamLogger(stderr));

            String line=null;
            //标准输出
            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            boolean isBegin=true;
            int cnt=1;

            //启动回答集处理线程
            startAnswerSetProcess();

            String linespe=System.lineSeparator();

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
            int exitVal = p.waitFor();
            //进程结束后发出终止信号
            for(int i=0;i<ansProcessorNums;i++){
                progOut.add(killSig);
            }

        } catch (IOException|InterruptedException ex) {
            logger.error(ex.getMessage());
        }
        finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        stopAnswerSetProcess();
        clearStatus();
    }

    protected void startAnswerSetProcess(){
        processors=new ArrayList<>();
        ClingoResultProcessor crp;
        for(int i=0;i<ansProcessorNums;i++){
            crp=new ClingoResultProcessor(progOut,killSig);
            processors.add(crp);
            threadPool.execute(crp);
        }

    }

    protected void stopAnswerSetProcess(){
        threadPool.waitDone();

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

    protected void clearStatus(){
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

    public LpmlnThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(LpmlnThreadPool threadPool) {
        this.threadPool = threadPool;
    }
}
