package com.example.demo.AQS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author XZQ
 * @Date 2021/4/11 17:38
 **/
public class A {
    Lock l=new ReentrantLock();
    long num=0;
    public long getNum(){
        return num;
    }
    public   void  increase(){


        try{
            l.lock();
            num++;
        }finally {
            l.unlock();
        }


    }
}
