package com.example.demo.netty.jdkio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Author XZQ
 * @Date 2021/6/13 23:14
 **/
public class aioServer implements  Runnable{
    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
    public aioServer(int port){
        this.port=port;
        try {
            asynchronousServerSocketChannel=AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server started on "+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        latch=new CountDownLatch(1);
        doAccept();
        try {
            latch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
    }
}
