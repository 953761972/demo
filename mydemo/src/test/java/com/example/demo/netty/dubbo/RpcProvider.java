package com.example.demo.netty.dubbo;

/**
 * @Author XZQ
 * @Date 2021/9/15 22:01
 **/
public class RpcProvider {
    public static void main(String[] args) {
        RpcRegistry rpcRegistry=new RpcRegistry(8082);
        rpcRegistry.start();
    }
}
