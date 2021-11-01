package com.example.demo.类加载;

import com.example.demo.AQS.A;
import com.example.demo.AQS.AtomicA;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author XZQ
 * @Date 2021/4/12 11:50
 **/
public class test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        test t=new test();//可以
        A a =new A();//可以
        String str=new String();//NullPointerException
        ClassLoader classLoader = a.getClass().getClassLoader();//

        Class<?> aClass = classLoader.loadClass("com.example.demo.AQS.AtomicA");
        AtomicA a1= (AtomicA) aClass.newInstance();
        //System.out.println(a1.getNum());

        Class<?> aClass1 = Class.forName("com.example.demo.AQS.AtomicA");
        AtomicA o = (AtomicA) aClass.newInstance();
        Method[] declaredMethods = aClass1.getDeclaredMethods();
        for(int i=0;i<declaredMethods.length;i++){
            System.out.println(declaredMethods[i].getName());
            //declaredMethods[i].setAccessible(true);
            System.out.println(declaredMethods[i].invoke(o));
        }

        Method method = AtomicA.class.getDeclaredMethod("getNum");
        //method.setAccessible(true);
        System.out.println(method.invoke(o));
    }

}
