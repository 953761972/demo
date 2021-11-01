package com.example.demo.netty.jdkio.nio;

/**
 * @Author XZQ
 * @Date 2021/6/13 14:02
 **/
public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable server=new nioServer(8080);
        Thread threadServer=new Thread(server);
        threadServer.start();
        Thread.sleep(1000);

    }
}
