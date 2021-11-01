package com.example.demo.netty.dubbo;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:06
 **/
public interface IRpcService {
    public int add(int a,int b);
    public int sub(int a,int b);

    public int mult(int a,int b);

    public int div(int a,int b);

}
