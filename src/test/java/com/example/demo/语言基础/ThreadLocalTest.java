package com.example.demo.语言基础;

/**
 * @Author XZQ
 * @Date 2021/10/21 22:54
 **/
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal tl=new ThreadLocal();
        ThreadLocal tl1=new ThreadLocal();

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("ddddd");
                tl.set("aaaaaaaaa");
                tl1.set("dddd");
                System.out.println(tl.get());
                System.out.println(tl1.get());

            }
        });
        t.start();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("111111");
                tl.set("22222222");
                tl1.set("fff");
                System.out.println(tl.get());
                System.out.println(tl1.get());
            }
        });
        t1.start();
    }
}
