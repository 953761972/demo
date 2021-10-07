package com.example.demo.redis.lock;

import io.netty.handler.codec.http.multipart.AbstractMemoryHttpData;
import org.junit.platform.commons.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @Author XZQ
 * @Date 2021/9/22 17:10
 **/
public class TestJediesLock {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        Jedis jedis1 = new Jedis("localhost");

        System.out.println(jedis.ping());
        Runnable r=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100000;i++){
                    while(jedis.setnx("lock","33")!=1l){

                    }
                    System.out.println(Thread.currentThread().getName()+">>获得锁");
                    jedis.expire("lock",1);
                    String strcount=jedis.get("count");
                    Long count=0l;
                    if(strcount==null || "".equals(strcount)){
                        count=0l;
                    }else{
                        count=Long.parseLong(strcount);
                    }
                    System.out.println(Thread.currentThread().getName()+">>当前："+count);
                    jedis.set("count",String.valueOf(count+1));
                    jedis.del("lock");
                    System.out.println(Thread.currentThread().getName()+">>释放锁");
                }
            }
        };
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100000;i++){
                    while(jedis1.setnx("lock","44")!=1l){

                    }
                    System.out.println(Thread.currentThread().getName()+">>获得锁");
                    jedis1.expire("lock",1);
                    String strcount=jedis1.get("count");
                    Long count=0l;
                    if(strcount==null || "".equals(strcount)){
                        count=0l;
                    }else{
                        count=Long.parseLong(strcount);
                    }
                    System.out.println(Thread.currentThread().getName()+">>当前："+count);
                    jedis1.set("count",String.valueOf(count+1));
                    jedis1.del("lock");
                    System.out.println(Thread.currentThread().getName()+">>释放锁");

                }
            }
        };

        Thread t1=new Thread(r);
        Thread t2=new Thread(r1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终结果："+jedis.get("count"));
    }
}
