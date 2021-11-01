package com.example.demo.注解.注解测试;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:59
 **/
public class main {
    public static void main(String[] args) throws NoSuchMethodException {
        user u=new user();
        test a=u.getClass().getAnnotation(test.class);
        Method mSomebody = user.class.getMethod("getName");
        test a1= mSomebody.getAnnotation(test.class);
        Annotation[] aa=u.getClass().getAnnotations();
        System.out.println(aa.length);
        //System.out.println(a.mytest());
        System.out.println(a1.mytest());
    }
}
