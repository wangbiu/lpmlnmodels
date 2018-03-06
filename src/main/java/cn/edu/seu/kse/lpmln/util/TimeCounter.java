package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;

/**
 * @author 许鸿翔
 * @date 2018/3/2
 */
public class TimeCounter {
    public double time;
    protected long startTime;
    protected Status status;
    public enum Status {READY, RUNNING, PAUSED, STOPPED}

    public TimeCounter(){
        time = 0;
        startTime = 0;
        status = Status.READY;
    }

    public void start(){
        if(status!=Status.READY){
            throw new SolveException("Timer start incorrect");
        }
        status = Status.RUNNING;
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        if(status!=Status.RUNNING){
            throw new SolveException("Timer stop incorrect");
        }else{
            time += ((double)(System.currentTimeMillis()-startTime))/1000;
            status = Status.STOPPED;
        }
    }

    public void pause(){
        if(status!=Status.RUNNING){
            throw new SolveException("Timer pause incorrect");
        }else{
            time += ((double)(System.currentTimeMillis()-startTime))/1000;
            status = Status.PAUSED;
            startTime = 0;
        }

    }

    public void restart(){
        if(status!=Status.PAUSED){
            throw new SolveException("Timer stop incorrect");
        }else{
            startTime = System.currentTimeMillis();
            status = Status.RUNNING;
        }
    }

    public Status getStatus() {
        return status;
    }
}
