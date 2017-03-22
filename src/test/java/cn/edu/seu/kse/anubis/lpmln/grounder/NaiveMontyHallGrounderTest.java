package cn.edu.seu.kse.anubis.lpmln.grounder;

import org.junit.Test;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class NaiveMontyHallGrounderTest {
    private NaiveMontyHallGrounder grounder=new NaiveMontyHallGrounder(10);

    @Test
    public void test(){
        System.out.println(grounder.ground());

    }

    @Test
    public void testBoxRule(){
        System.out.println(grounder.groundBoxRule());

    }
}
