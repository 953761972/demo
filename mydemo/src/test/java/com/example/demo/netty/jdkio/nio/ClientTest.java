package com.example.demo.netty.jdkio.nio;

/**
 * @Author XZQ
 * @Date 2021/6/13 16:05
 **/
public class ClientTest {
    public static void main(String[] args) {
        Runnable client=new client("127.0.0.1",8080);
        Thread threadClient=new Thread(client);
        threadClient.start(); }
}
