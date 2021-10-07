package com.example.demo.netty.dubbo.impl;

import com.example.demo.netty.dubbo.IRpcService;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:14
 **/
public class RpcServiceImpl implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a /b;
    }
}
