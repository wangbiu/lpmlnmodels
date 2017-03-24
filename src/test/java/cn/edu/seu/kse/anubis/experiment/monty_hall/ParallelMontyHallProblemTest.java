package cn.edu.seu.kse.anubis.experiment.monty_hall;

import cn.edu.seu.kse.anubis.experiment.model.ThreadStatInfo;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class ParallelMontyHallProblemTest {

    @Test
    public void testMAP() throws IOException, InterruptedException {
        ParallelMontyHallProblem pmhp=new ParallelMontyHallProblem();
        pmhp.setProblemN(3);
        pmhp.setTaskType(0);
        pmhp.setCores(3);
        pmhp.setRound(10);
        pmhp.runExperiment();

//        Thread.sleep(30000);
    }

    @Test
    public void testMPD() throws IOException, InterruptedException {
        ParallelMontyHallProblem pmhp=new ParallelMontyHallProblem();
        pmhp.setProblemN(3);
        pmhp.setTaskType(1);
        pmhp.setCores(3);
        pmhp.setRound(10);
        pmhp.runExperiment();
    }
}
