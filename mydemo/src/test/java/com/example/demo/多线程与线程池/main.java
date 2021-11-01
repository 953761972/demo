package com.example.demo.多线程与线程池;

import java.util.TreeSet;
import java.util.concurrent.Executors;

/**
 * @Author XZQ
 * @Date 2021/4/11 20:06
 **/
public class main {
    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadExecutor();
        //其实还是调用了ThreadPoolExecutor();
        ThreadLocal threadLocal=new ThreadLocal();
        TreeSet ts=new TreeSet();

        Thread t=new Thread();



    }
}
