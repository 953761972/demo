package com.example.demo.netty.dubbo;

/**
 * @Author XZQ
 * @Date 2021/9/15 21:44
 **/
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpchello=RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpchello.Hello("xzq"));
        IRpcService service=RpcProxy.create(IRpcService.class);

        System.out.println(service.add(8,2));
    }
}
