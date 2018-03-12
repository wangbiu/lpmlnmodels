package cn.edu.seu.kse.lpmln.util.commandLine;

import cn.edu.seu.kse.lpmln.util.ErrorStreamLogger;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 许鸿翔
 * @date 2018/3/12
 */
public class BaseCommandLine {
    final public static int WINDOWS_NT=1;
    final public static int UNIX_LIKE=0;
    protected double cputime=0;
    protected LpmlnThreadPool threadPool;
    protected Process cmdProcess;
    private static Logger logger= LogManager.getLogger(BaseCommandLine.class.getName());

    public BaseCommandLine(LpmlnThreadPool threadPool){
        this.setThreadPool(threadPool);
    }

    protected int getOS(){
        int os;
        if(SystemUtils.IS_OS_WINDOWS){
            os=WINDOWS_NT;
        }else if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_UNIX){
            os=UNIX_LIKE;
        }else{
            throw new RuntimeException("不支持的操作系统!");
        }

        return os;
    }

    protected String[] getCommandInterperter(){
        int osType=getOS();
        String[] cmd =new String [3];
        if(osType==WINDOWS_NT){
            cmd[0]="cmd.exe";
            cmd[1]="/c";
        }
        else if(osType==UNIX_LIKE){
            cmd[0]="sh";
            cmd[1]="-c";
        }
        return cmd;
    }

    public void call(String command){
        String[] cmd = getCommandInterperter();
        cmd[2]=command;
        BufferedReader br=null;

        String as=null;

        try {
            cmdProcess=Runtime.getRuntime().exec(cmd);

            //标准错误流
            final InputStream stderr=cmdProcess.getErrorStream();

            //单独的读取错误流的进程
            threadPool.execute(new ErrorStreamLogger(stderr));


            //标准输出
            br=new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));

            //启动回答集处理线程
            startResultProcess(br);

        } catch (IOException |InterruptedException ex) {
            logger.error(ex.getMessage());
        }
        finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        stopResultProcess();
        clearStatus();
    }

    protected void startResultProcess(BufferedReader br) throws IOException, InterruptedException {

    }

    protected void stopResultProcess(){

    }

    protected void clearStatus(){

    }

    public LpmlnThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(LpmlnThreadPool threadPool) {
        this.threadPool = threadPool;
    }
}
