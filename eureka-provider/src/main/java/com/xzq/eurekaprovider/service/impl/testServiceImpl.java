package com.xzq.eurekaprovider.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.xzq.eurekaprovider.service.testService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author XZQ
 * @Date 2021/11/21 16:20
 **/
@Service
public class testServiceImpl implements testService, ApplicationContextAware {
    ApplicationContext applicationContext;
    @Override
    public String feginTest() {
        String port = applicationContext.getEnvironment().getProperty("server.port");
        String str=applicationContext.getEnvironment().getProperty("eureka.client.serviceUrl.defaultZone");
        return "lalalalalalalaala:"+port+"   "+str;
    }


    @HystrixCommand(fallbackMethod = "HystrixtestFallback",
            commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"), // 信号量隔离，因为业务方法用了ThreadLocal
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"), //超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value="5"),//触发熔断最小请求数量
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="3"),//触发熔断的错误占比阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="3000"),//熔断器回复时间
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value="2"),// 单机最高并发
            @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value="10")// fallback单机最高并发
    })
    @Override
    public String Hystrixtest() throws InterruptedException {
        //int i=1/0;
        Thread.sleep(400);
        System.out.println("返回成功");
        return "Hystrixest";
    }

    public String HystrixtestFallback() {
        System.out.println("触发失败兜底策略");
        return "触发失败兜底策略";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
