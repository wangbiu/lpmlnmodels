package cn.edu.seu.kse.anubis.lpmln.misc;

import org.junit.Test;

/**
 * Created by 王彬 on 2017/3/28.
 */
public class ObjectClassTest {

    @Test
    public void test(){
        String str="";
        Object ostr=str;

        System.out.println("str "+str.getClass().getName());
        System.out.println("ostr "+ostr.getClass().getSimpleName());
        System.out.println(ostr.getClass().getCanonicalName());
        System.out.println("ostr "+ostr.getClass().getName());
    }
}
