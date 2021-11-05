package com.example.demo.aop;


import com.example.demo.bean.TestObj;
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

    @Pointcut("@annotation(com.example.demo.aop.RequestRequire)")
    public void parameterInteceptor() {
    }

    @Around("parameterInteceptor()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("进入方法前");
        try{
            Object o=pjp.proceed();
            System.out.println("进入方法后");
        }catch (Throwable e){

        }
    }
//
//    @Bean
//    public TestObj TestObj(){
//        return new TestObj();
//    }
}
