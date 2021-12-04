package com.example.demo.netty.socket;

import java.io.IOException;
import java.net.*;

/**
 * @Author XZQ
 * @Date 2021/11/30 21:12
 **/
public class testDatagramSocket {
    public static void main(String[] args) {
        DatagramRunning r=new DatagramRunning();
        Thread t=new Thread(r);
        t.start();
    }
}

class DatagramRunning implements  Runnable{

    @Override
    public void run() {
        try {
            DatagramSocket ds=new DatagramSocket(6060);
            DatagramPacket dp=new DatagramPacket(new byte[1024],1024);
            System.out.println("UDP监控中。。。端口："+ds.getPort());
            while(true){
                ds.receive(dp);
                System.out.println(dp.getAddress().getHostName() + "(" + dp.getPort() + "):" + new String(dp.getData()));
                dp.setData("Hello Client".getBytes());
                dp.setPort(6070);
                dp.setAddress(InetAddress.getLocalHost());
                ds.send(dp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}