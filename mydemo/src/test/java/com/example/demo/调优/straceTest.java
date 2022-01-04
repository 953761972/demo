package com.example.demo.调优;

public class straceTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
        for(;;){
            Thread.sleep(1000);
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread:"+Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }
}
