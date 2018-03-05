package cn.edu.seu.kse.lpmln.experiment;

import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.junit.After;
import org.junit.Test;

/**
 * @author 许鸿翔
 * @date 2018/3/5
 */
public class LpmlnExperimentTest {
    protected LpmlnExperiment experiment = new LpmlnExperiment();

    @Test
    public void testAll() {
        experiment.testAll();
    }

    @Test
    public void simpletest() {
        experiment.simpletest();
    }

    @After
    public void clean(){
        FileHelper.cleanFiles();
        System.out.println("file cleaned.");
    }
}