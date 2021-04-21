package com.example.demo.动态代理;

/**
 * @Author XZQ
 * @Date 2021/4/10 22:43
 **/
public class 被代理类  implements 被代理类接口{
    public void  test(){
        System.out.println("被代理类");
    }

    public static void main(String[] args) {
        被代理类 n=new 被代理类();
        n.test();
    }
}
