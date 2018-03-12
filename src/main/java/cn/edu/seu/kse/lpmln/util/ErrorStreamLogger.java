package cn.edu.seu.kse.lpmln.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class ErrorStreamLogger implements Runnable {
    private static Logger logger= LogManager.getLogger(ErrorStreamLogger.class.getName());
    private InputStream stderr;

    public ErrorStreamLogger(InputStream stderr){
        this.stderr = stderr;
    }

    @Override
    public void run() {
        BufferedReader br=null;
        try {
            StringBuilder errres=new StringBuilder();
            br=new BufferedReader(new InputStreamReader(stderr));
            String line;
            while((line=br.readLine()) != null){
                errres.append(line).append(System.lineSeparator());
            }
            logger.trace(errres);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
