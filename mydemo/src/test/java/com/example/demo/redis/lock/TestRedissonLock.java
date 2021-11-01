package com.example.demo.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Author XZQ
 * @Date 2021/9/22 18:38
 **/
public class TestRedissonLock {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");

        Runnable r1=new Runnable() {
            RedissonClient redisson = Redisson.create(config);
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    RLock lock = redisson.getLock("Lock");
//                    while(!lock.tryLock()){
//
//                    }
                    lock.lock();
                    RBucket<String> b = redisson.getBucket("reddsioncount");
                    //要先删除reddsioncount 否则报：Caused by: java.io.IOException: Unsupported protocol version 51
                    String strCount=b.get();
                    Long count=0l;
                    if(strCount==null || "".equals(strCount)){
                        count=0l;
                    }else{
                        count=Long.parseLong(strCount);
                    }
                    System.out.println(Thread.currentThread().getName()+">>当前："+count);
                    b.set(String.valueOf(count+1));
                    lock.unlock();
                }
            }
        };
        Runnable r2=new Runnable() {
            RedissonClient redisson = Redisson.create(config);
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    RLock lock = redisson.getLock("Lock");
//                    while(!lock.tryLock()){
//
//                    }
                    lock.lock();
                    RBucket<String> b = redisson.getBucket("reddsioncount");
                    String strCount=b.get();
                    Long count=0l;
                    if(strCount==null || "".equals(strCount)){
                        count=0l;
                    }else{
                        count=Long.parseLong(strCount);
                    }
                    System.out.println(Thread.currentThread().getName()+">>当前："+count);
                    b.set(String.valueOf(count+1));
                    lock.unlock();
                }
            }
        };
        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();


        RedissonClient redisson = Redisson.create(config);
        System.out.println("结果："+redisson.getBucket("reddsioncount").get().toString());
    }
}
