package com.example.demo.多线程与线程池;

import java.util.concurrent.*;

/**
 * @Author XZQ
 * @Date 2021/4/10 00:53
 **/
public class ThreadPoolTest {
    static final int i=0;
    public static void main(String[] args) {
        //TestThreadPoolExecutor();
        //TestnewCachedThreadPool();
        //TestnewFixedThreadPool();
        //TestnewScheduledThreadPool();
        //TestnewSingleThreadExecutor();
        TestFutureTask();
    }
    public static void TestThreadPoolExecutor(){//ThreadPoolExecutor
        BlockingQueue<Runnable> r = new ArrayBlockingQueue<>(100);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("-->"+Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i=0;i<100;i++){
            r.add(runnable);
        }
        ThreadPoolExecutor t=new ThreadPoolExecutor(10,10,1000, TimeUnit.MINUTES,r);
        t.execute(runnable);
    }

    public static void TestnewCachedThreadPool(){//CachedThreadPool
        ExecutorService cacheThreadPool =Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public  static void TestnewFixedThreadPool(){//FixedThreadPool
        ExecutorService cacheThreadPool =Executors.newFixedThreadPool(6);
        for(int i=0;i<100;i++){
            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public  static void TestnewScheduledThreadPool(){//ScheduledThreadPool
        ScheduledExecutorService executorService =Executors.newScheduledThreadPool(6);
        for(int i=0;i<10;i++){
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },2,TimeUnit.SECONDS);//延时两秒
        }
        for(int i=0;i<10;i++){
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },2,2,TimeUnit.SECONDS);//延时两秒，没2秒执行1次
        }
    }

    public  static void TestnewSingleThreadExecutor(){//SingleThreadExecutor
        ExecutorService singleThreadPool= Executors.newSingleThreadExecutor();
        for(int i=0;i<100;i++){
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public  static void TestFutureTask(){//FutureTask
        ExecutorService singleThreadPool= Executors.newSingleThreadExecutor();
        for(int i=0;i<100;i++){
            Future ft = singleThreadPool.submit(new Callable() {
                public Integer call() {
                    System.out.println("this is " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }
            });
            try {
                System.out.println(ft.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
