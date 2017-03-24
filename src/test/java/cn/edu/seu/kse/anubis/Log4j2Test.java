package cn.edu.seu.kse.anubis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class Log4j2Test {
    private static Logger logger= LogManager.getLogger(Log4j2Test.class.getName());

    @Test
    public void test(){
        logger.info("info test");
        logger.info("info test %s %d ","test1", 10);
        logger.error("error {}",10);
        logger.debug("debug: {} {} {}", 10 , "dfasdf", "debug");
    }
}
