package com.xzq.dubbodemo.Interceptor;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author XZQ
 * @Date 2021/11/6 11:06
 **/
@Component
public class MyInterceptor implements HandlerInterceptor , ApplicationContextAware {

    private static ApplicationContext applicationContext;

    ThreadPoolExecutor threadPoolExecutor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(threadPoolExecutor==null){
            threadPoolExecutor= (ThreadPoolExecutor) applicationContext.getBean("getThreadPoolExecutor");
        }
        threadPoolExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+":Myinterceptor->preHandle");
            }
        });
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        threadPoolExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+":Myinterceptor->postHandle");
            }
        });
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":Myinterceptor->afterCompletion");
            }
        });
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyInterceptor.applicationContext = applicationContext;
    }
}
