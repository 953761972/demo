package com.example.demo.AQS;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @Author XZQ
 * @Date 2021/4/11 17:08
 **/
public class LockTest {
     int i=0;
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        A a =new A();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        //查看对象外部信息
        System.out.println(GraphLayout.parseInstance(a).toPrintable());

        //获取对象总大小
        System.out.println("size : " + GraphLayout.parseInstance(a).totalSize());

        //AtomicA a=new AtomicA();
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000000;i++){
                    a.increase();
                }
            }
        };
        Runnable r2=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000000;i++){
                    a.increase();
                }
            }
        };

        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println(a.getNum());
        System.out.println(String.format("%sms",end-start));

    }
}
