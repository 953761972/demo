package com.example.demo.动态代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author XZQ
 * @Date 2021/4/10 23:06
 **/
public class Cglib代理 {
    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(被代理类.class);
        enhancer.setCallback(new MethodInterceptor() {
            //类似invokerhanddler的invoke方法
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("begin");
                Object invoke = methodProxy.invoke(new 被代理类(), objects);
                System.out.println("end");
                return invoke;
            }
        });

        被代理类 proxy =(被代理类) enhancer.create();
        proxy.test();
    }
}
