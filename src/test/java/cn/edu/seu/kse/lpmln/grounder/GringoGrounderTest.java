package cn.edu.seu.kse.lpmln.grounder;

import org.junit.Test;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2019/3/9
 */
public class GringoGrounderTest {
    public String filePath = "test.lp";
    private LPMLNGrounder grounder = new GringoGrounder();

    @Test
    public void grounding() {
        String result = grounder.grounding(new File(filePath));
        System.out.println(result);
    }
}