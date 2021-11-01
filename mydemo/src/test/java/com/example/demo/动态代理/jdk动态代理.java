package com.example.demo.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author XZQ
 * @Date 2021/4/10 22:40
 **/
public class jdk动态代理 implements InvocationHandler {

    private Class<?> target;
    private Object real;
    //委托类class
    public jdk动态代理(Class<?> target){
        this.target=target;
    }
    //实际执行类bind
    public  Object bind(Object real){
        this.real=real;
        //利用JDK提供的Proxy实现动态代理
        return  Proxy.newProxyInstance(target.getClassLoader(),new Class[]{target},this);
    }
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        //代理环绕
        System.out.println("begin");
        //执行实际的方法
        Object invoke = method.invoke(real, args);
        System.out.println("end");
        return invoke;
    }

    public static void main(String[] args) throws Throwable {
        //参考：https://blog.csdn.net/a837199685/article/details/68930987
        //JDK动态代理只能代理有接口的类,并且是能代理接口方法,不能代理一般的类中的方法
        //提供了一个使用InvocationHandler作为参数的构造方法。在代理类中做一层包装,业务逻辑在invoke方法中实现
        //重写了Object类的equals、hashCode、toString，它们都只是简单的调用了InvocationHandler的invoke方法，即可以对其进行特殊的操作，也就是说JDK的动态代理还可以代理上述三个方法
        //在invoke方法中我们甚至可以不用Method.invoke方法调用实现类就返回。这种方式常常用在RPC框架中,在invoke方法中发起通信调用远端的接口等
        被代理类接口 proxy =(被代理类接口) new jdk动态代理(被代理类接口.class).bind(new 被代理类());
        proxy.test();
    }
}
