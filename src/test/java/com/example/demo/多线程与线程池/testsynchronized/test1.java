package com.example.demo.多线程与线程池.testsynchronized;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author XZQ
 * @Date 2021/9/22 15:48
 **/
public class test1 {
    private static Integer i=0;
    private static volatile int j;
    //private static final Unsafe unsafe = Unsafe.getUnsafe();  //拿不到实例，报错

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public test1() throws NoSuchFieldException, IllegalAccessException {
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        int k;
        //反射的方式拿到unsafe实例
        Field f1 = Unsafe.class.getDeclaredField("theUnsafe");
        f1.setAccessible(true);
        Unsafe unsafe = (Unsafe)f1.get(null);

        Runnable r=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000000;i++){
                    test1.i=test1.i+1;
                    //j=j+1;
                    //k=k+1;
                }
            }
        };//线程不安全：11ms
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                for(int j=0;j<1000000;j++){
                    synchronized (test1.class){
                        test1.i =test1.i+1;
                        //j=j+1;
                        //k=k+1;
                    }
                }
            }
        };//重量级锁：110ms
        Runnable r2=new Runnable() {
            @Override
            public void run() {
                for(int j=0;j<1000000;j++){
                    atomicInteger.incrementAndGet();
                }
            }
        };//原子自增：37ms
        Runnable r3=new Runnable() {
            @Override
            public void run() {
                for(int f=0;f<1000000;f++){
                    test1.j=test1.j+1;
                }
            }
        };//线程可见性（线程不安全）：42ms
        Runnable r4=new Runnable() {
            @Override
            public void run() {
                for(int f=0;f<1000000;f++){
                    test1.i=test1.i+1;
                    unsafe.getAndAddInt(this,test1.i,1);
                }
            }
        };//unsafe：报错，不能正常执行

        long start=System.currentTimeMillis();
        Thread t1=new Thread(r4);
        Thread t2=new Thread(r4);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end=System.currentTimeMillis();
        System.out.println("耗时："+ (end-start));
        System.out.println(i);
        //System.out.println(atomicInteger.get());

    }
}
