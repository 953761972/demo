package com.example.demo.语言基础;

/**
 * @Author XZQ
 * @Date 2021/10/21 22:26
 **/
public class 范型<T> {
    private T t;
    public void set(T t){
        this.t=t;
    }
    public T get(){
        return t;
    }
    public static void main(String[] args) {
        test();
        test1();
    }
    public static void test(){
        范型 f=new 范型();
        f.set(11);
        int test= (int) f.get();
        System.out.println(test);
    }
    public static  void test1(){
        范型 f=new 范型<String>();
        f.set("11");
        String test= (String) f.get();
        System.out.println(test);
    }
}
