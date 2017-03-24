package cn.edu.seu.kse.anubis.experiment;

import org.junit.Test;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class ExperimentTest {

    @Test
    public void testEmailAlert() throws Exception {
        Experiment exp=new Experiment();
        exp.emailAlert("test email","test email ",exp.email_addr);
    }
}
