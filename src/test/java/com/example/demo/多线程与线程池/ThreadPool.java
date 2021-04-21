package com.example.demo.多线程与线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author XZQ
 * @Date 2021/4/10 00:53
 **/
public class ThreadPool {
    public static void main(String[] args) {
        BlockingQueue<Runnable> r = new ArrayBlockingQueue<>(100);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("thread-"+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        r.add(runnable);
        ThreadPoolExecutor t=new ThreadPoolExecutor(10,10,1000, TimeUnit.MINUTES,r);
        t.submit(runnable);
        Thread.yield();
    }
}
