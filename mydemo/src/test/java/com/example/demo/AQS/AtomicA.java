package com.example.demo.AQS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author XZQ
 * @Date 2021/4/11 17:48
 **/
public class AtomicA {
    AtomicInteger l=new AtomicInteger();
    public long getNum(){
        return l.get();
    }
    public   void  increase(){
        l.incrementAndGet();
    }
}
