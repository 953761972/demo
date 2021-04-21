package com.example.demo.动态代理;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author XZQ
 * @Date 2021/4/10 23:43
 **/
//参考：https://www.codercto.com/a/21613.html
@Aspect
public class Aspectj代理 {
    //表示对实现了Mtrace接口的类，并且参数数Integer 同时方法中有@RequestMapping 注解的方法插入代理
    @Pointcut("execution(* com.example.demo.动态代理.被代理类.test(..))")
    public void test() {

    }
    @Before(value = "test()")//前面植入
    public void doBeforeTask2(JoinPoint point) {
        //方法调用前植入
        System.out.println("=========BEFORE=========");
    }

    @After("test()")//后面植入
    public void after(JoinPoint point) {
        //方法调用后植入
        System.out.println("===========AFTER=======");
    }
    @AfterThrowing("test()")
    public void afterthrowing(JoinPoint point) {
        System.out.println("===========throwing=======");
    }
    @AfterReturning("test()")
    public void afterRutuen(JoinPoint point) {
        System.out.println("===========return=======");
    }
}

