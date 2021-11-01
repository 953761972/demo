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
        RedissonClient redisson = Redisson.create(config);

        Runnable r1=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    RLock lock = redisson.getLock("Lock");
                    while(!lock.tryLock()){

                    }
                    RBucket<String> b = redisson.getBucket("count");
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
        Thread t2=new Thread(r1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();


        t2.interrupt();
        System.out.println("结果："+redisson.getBucket("count").get().toString());
    }
}
