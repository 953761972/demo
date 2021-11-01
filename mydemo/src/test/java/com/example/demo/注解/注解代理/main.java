package com.example.demo.注解.注解代理;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:04
 **/
public class main {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(RequestRequireAspect.class);
        test t= (test) applicationContext.getBean("test");
        t.setTest("ss");
    }
}
