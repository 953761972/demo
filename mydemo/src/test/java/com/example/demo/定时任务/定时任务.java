package com.example.demo.定时任务;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author XZQ
 * @Date 2021/4/20 16:22
 **/
public class 定时任务 {
    public static void main(String[] args) {
        testTimer();
    }

    public static void testTimer(){
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),5000,5000);
    }
}

class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        System.out.println("dddd");
    }
}