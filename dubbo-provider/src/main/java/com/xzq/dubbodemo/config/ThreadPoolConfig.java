package com.xzq.dubbodemo.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author XZQ
 * @Date 2021/11/7 10:53
 **/
@Configuration
public class ThreadPoolConfig implements DisposableBean {

    ThreadPoolExecutor threadPool=null;

    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){
        BlockingDeque deque=new LinkedBlockingDeque();
        threadPool=new ThreadPoolExecutor(5,10,6000, TimeUnit.SECONDS,deque);
        return  threadPool;
    }

    @Override
    public void destroy() throws Exception {
        if(threadPool!=null){
            System.out.println("当前任务队列还剩"+threadPool.getTaskCount()+"个任务，已丢弃。");
            threadPool.shutdown();
        }
    }
}
