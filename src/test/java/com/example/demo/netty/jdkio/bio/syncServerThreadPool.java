package com.example.demo.netty.jdkio.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author XZQ
 * @Date 2021/6/13 13:13
 **/
public class syncServerThreadPool {
    private ExecutorService executorService;

    public  syncServerThreadPool(int maxPoolSize,int queueSize) {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void execute(Runnable task){
        executorService.execute(task);
    }
}
