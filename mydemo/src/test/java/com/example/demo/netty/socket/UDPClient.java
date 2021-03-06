package com.example.demo.netty.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author XZQ
 * @Date 2021/11/30 21:29
 **/
public class UDPClient {
    public static void main(String[] args){
        try {
            DatagramSocket client = new DatagramSocket(6070);
            DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
            packet.setPort(6060);
            packet.setAddress(InetAddress.getLocalHost());
            packet.setData("Hello Server".getBytes());
            for(int i=0;i<10;i++){
                client.send(packet);
                client.receive(packet);
                System.out.println(packet.getAddress().getHostName() + "(" + packet.getPort() + "):" + new String(packet.getData()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
