package cn.edu.seu.kse.lpmln.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class LpmlnThreadPool {
    public ExecutorService pool;

    public LpmlnThreadPool(String poolName){
        ThreadFactory namdThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(poolName+"-%d").build();
        pool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Integer.MAX_VALUE,
                0L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(),
                namdThreadFactory,
                new ThreadPoolExecutor.AbortPolicy()
                );
    }

    public void execute(Runnable toRun){
        pool.execute(toRun);
    }

    public void shutdown(){
        pool.shutdown();
    }

    public void awaitTermination(){
        try {
            pool.awaitTermination(Long.MAX_VALUE,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitDone(){
        shutdown();
        awaitTermination();
    }
}
