package com.xzq.dubbodemo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

/**
 * @Author XZQ
 * @Date 2021/11/6 14:28
 **/
@Aspect
@Configuration
public class Aopconfig {
    @Pointcut("@annotation(com.xzq.dubbodemo.config.log)")
    public void point(){}

    @Around("point()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("pre log");
        try{
            return point.proceed();
        }finally {
            System.out.println("after log");
        }

    }
    @AfterThrowing("point()")
    public void afterthrowing(){
        System.out.println("exception");
    }
}
