package com.example.demo.注解.注解代理;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author XZQ
 * @Date 2021/4/20 21:57
 **/
@Aspect
@Configuration
public class RequestRequireAspect {



    public RequestRequireAspect() {
    }

    @Pointcut("@annotation(com.example.demo.注解.注解代理.RequestRequire)")
    public void parameterInteceptor() {
    }

    @Around("parameterInteceptor()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法前");
        Object o=    pjp.proceed();
        System.out.println("进入方法后");
            return o;


    }

    @Bean
    public test test(){
        return new test();
    }
}
