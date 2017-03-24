package cn.edu.seu.kse.anubis.experiment.monty_hall;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class MontyHallProblemTest {

    @Test
    public void testMAP() throws IOException {
        MontyHallProblem mhp=new MontyHallProblem();
        mhp.setProblemN(3);
        mhp.setRound(10);
        mhp.setTaskType(0);
        mhp.runExperiment();
    }

    @Test
    public void testMPD() throws IOException {
        MontyHallProblem mhp=new MontyHallProblem();
        mhp.setProblemN(3);
        mhp.setRound(100);
        mhp.setTaskType(1);
        mhp.runExperiment();
    }
}
