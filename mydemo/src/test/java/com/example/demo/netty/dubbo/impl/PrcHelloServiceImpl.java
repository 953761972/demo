package com.example.demo.netty.dubbo.impl;

import com.example.demo.netty.dubbo.IRpcHelloService;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:11
 **/
public class PrcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String Hello(String name) {
        return "Hello "+name;
    }
}
