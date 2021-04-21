package com.example.demo.定时任务;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author XZQ
 * @Date 2021/4/20 21:09
 **/
public class 定时任务线程池 {
    public static void main(String[] args) {
        TestScheduledThresd();
    }

    public static void TestScheduledThresd(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========================");
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+"<><>"+System.nanoTime());
            }
        }, 1000, 3000, TimeUnit.MILLISECONDS);
    }
}
