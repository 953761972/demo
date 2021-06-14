package com.example.demo.netty.jdkio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author XZQ
 * @Date 2021/6/12 23:58
 **/
public class testSeerver {
    public static void main(String[] args) {
        int port=8080;
        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(port);
            Socket socket=null;
            while(true){
                socket=serverSocket.accept();
                new Thread(new TimeServerSocketHander(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
