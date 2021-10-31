package com.example.demo.多线程与线程池.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author XZQ
 * @Date 2021/10/26 19:29
 **/
public class ReentryLockTest {
    static int inc=0;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch lock=new CountDownLatch(2);
        ReentrantLock elock =new ReentrantLock();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100000;i++){
                    elock.lock();
                    inc++;
                    elock.unlock();
                }
                try {
                    Thread.sleep(1000);
                    lock.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        t1.start();
        t2.start();
        lock.await();
        System.out.println(inc);
    }
}
