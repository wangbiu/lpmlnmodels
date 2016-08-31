package cn.edu.seu.kse.anubis.lpmln.ExampleGenTest;

import cn.edu.seu.kse.anubis.experiment.BirdGen4LPMLN;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class BirdGen4LPMLNTest {

    @Test
    public void test() throws IOException {
        BirdGen4LPMLN bird=new BirdGen4LPMLN();
        bird.generate(100,"origin");
    }
}
