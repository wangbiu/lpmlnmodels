package cn.edu.seu.kse.anubis.experiment.monty_hall;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class MontyHallExperimentTest {
    MontyHallExperiment mhe=new MontyHallExperiment();

    @Before
    public void init(){
        mhe.setProblemN(3);
        mhe.setCores(3);
        mhe.setMaxCores(5);
        mhe.setMaxProblemN(4);
        mhe.setRound(2);
    }

    @Test
    public void testSingleMAP() throws IOException {
        mhe.testSingleMAP();
    }

    @Test
    public void testSingleMPD() throws IOException {
        mhe.testSingleMPD();
    }

    @Test
    public void testParallelMAP() throws IOException{
        mhe.testParallelMAP();
    }

    @Test
    public void testParallelMPD() throws IOException {
        mhe.testParallelMPD();
    }
}
