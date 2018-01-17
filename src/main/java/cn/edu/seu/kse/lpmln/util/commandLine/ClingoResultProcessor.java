package cn.edu.seu.kse.lpmln.util.commandLine;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 王彬 on 2017/3/26.
 */
public class ClingoResultProcessor extends Thread {
    private LinkedBlockingDeque<String> answers=null;
    private boolean isStop;
    private List<WeightedAnswerSet> was=null;
    private double cputime=0;
    private boolean hascputime=false;
    private String killSig=null;
    private Logger logger= LogManager.getLogger(ClingoResultProcessor.class.getName());


    public ClingoResultProcessor(LinkedBlockingDeque<String> ans,String killSig){
        this.answers=ans;
        this.killSig=killSig;
        isStop=false;
        was=new ArrayList<>();
    }

    @Override
    public void run() {
        String as=null;
        SyntaxModule sm=null;
        List<WeightedAnswerSet> tmpwas=null;
        String opt=null;
        String kill=null;
        int killlen=killSig.length();
        while (!isStop){
            try {
                sm=new SyntaxModule();
                as=answers.take();
//                logger.debug(as);
//                System.out.println(as);

                //线程终止逻辑，收到终止信号即可停止
                if(as.length()>=killlen){
                    kill=as.substring(0,killlen);
                    if(kill.equals(killSig)){
                        isStop=true;
                        break;
                    }

                }

                int optpos=as.indexOf("OPTIMUM FOUND");
                if(optpos>=0){
                    extractSolverTime(as.substring(optpos));
                    as=as.substring(0,optpos);
//                    System.out.println(as);
                }

                try{
                    tmpwas=sm.parseClingoResult(as);
                }catch (RuntimeException re){
//                    logger.debug(as);
                    logger.error(re.getMessage());
                }

                was.addAll(tmpwas);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }


    public void extractSolverTime(String statinfo){
        String[] stats=statinfo.split(System.lineSeparator());
        statinfo=stats[stats.length-2];
        int posstart=statinfo.indexOf(":")+1;
        int posend=statinfo.indexOf("s");
        String time=statinfo.substring(posstart,posend).trim();
        cputime=Double.valueOf(time);
        hascputime=true;
    }


    public LinkedBlockingDeque<String> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedBlockingDeque<String> answers) {
        this.answers = answers;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public List<WeightedAnswerSet> getWas() {
        return was;
    }

    public void setWas(List<WeightedAnswerSet> was) {
        this.was = was;
    }

    public double getCputime() {
        return cputime;
    }

    public void setCputime(double cputime) {
        this.cputime = cputime;
    }

    public boolean isHascputime() {
        return hascputime;
    }

}
