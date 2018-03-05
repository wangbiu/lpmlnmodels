package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;

/**
 * @author 许鸿翔
 * @date 2018/3/2
 */
public class TimeCounter {
    public double time;
    protected long startTime;
    protected boolean used = false;

    public TimeCounter(){
        time = 0;
        startTime = 0;
    }

    public void start(){
        if(startTime!=0){
            throw new SolveException("Timer start incorrect");
        }
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        if(used){
            throw new SolveException("Timer stop incorrect");
        }else{
            time += ((double)(System.currentTimeMillis()-startTime))/1000;
        }
    }

    public void pause(){
        if(used){
            throw new SolveException("Timer pause incorrect");
        }else{
            time += ((double)(System.currentTimeMillis()-startTime))/1000;
        }
        startTime = 0;
    }

    public double getTime() {
        return time;
    }
}
