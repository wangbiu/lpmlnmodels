package cn.edu.seu.kse.anubis.experiment;

import cn.edu.seu.kse.anubis.experiment.monty_hall.MontyHallExperiment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by 王彬 on 2017/3/27.
 */
public class ScheduleExperiment {
    private MontyHallExperiment experiment=null;
    private Logger logger= LogManager.getLogger(ScheduleExperiment.class.getName());



    public void startTest(){
        try {
            testParallelBirdSto();
            testParallelMHSto();
            testParallelBirdHeu();
            testParallelMHHeu();
        } catch (Exception e) {
            StringBuilder sb=new StringBuilder();
            try {
                for(StackTraceElement ste:e.getStackTrace()){
                    sb.append(ste.toString()).append("<br>");
                }
                logger.error(e.getMessage());
                experiment.emailWarn(sb.toString());
            } catch (Exception e1) {
                logger.error(e1.getMessage());
            }
        }


    }

    public void testParallelBirdSto() throws Exception {
        experiment=null;
        experiment=new MontyHallExperiment();
        experiment.setProblemN(13);
        experiment.setCores(1);
        experiment.setMaxCores(32);
        experiment.setMaxProblemN(13);
        experiment.setRound(5);
        experiment.setExperimentName("bird_sto");
        experiment.setProgramPrefix("b-");
        experiment.test(true,2);
    }

    public void testParallelBirdHeu() throws Exception {
        experiment=null;
        experiment=new MontyHallExperiment();
        experiment.setProblemN(13);
        experiment.setCores(1);
        experiment.setMaxCores(32);
        experiment.setMaxProblemN(13);
        experiment.setRound(5);
        experiment.setExperimentName("bird");
        experiment.setProgramPrefix("b-");
        experiment.test(true,2);
    }

    public void testParallelMHSto() throws Exception {
        experiment=null;
        experiment=new MontyHallExperiment();
        experiment.setProblemN(40);
        experiment.setCores(1);
        experiment.setMaxCores(32);
        experiment.setMaxProblemN(40);
        experiment.setRound(5);
        experiment.setExperimentName("monty_hall_sto");
        experiment.setProgramPrefix("m-");
        experiment.test(true,2);
    }

    public void testParallelMHHeu() throws Exception {
        experiment=null;
        experiment=new MontyHallExperiment();
        experiment.setProblemN(40);
        experiment.setCores(1);
        experiment.setMaxCores(32);
        experiment.setMaxProblemN(40);
        experiment.setRound(5);
        experiment.setExperimentName("monty_hall");
        experiment.setProgramPrefix("m-");
        experiment.test(true,2);
    }

}
