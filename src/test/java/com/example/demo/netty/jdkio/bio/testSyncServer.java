package com.example.demo.netty.jdkio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author XZQ
 * @Date 2021/6/13 13:11
 **/
public class testSyncServer {
    public static void main(String[] args) {
        int port=8080;
        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(port);
            Socket socket=null;
            syncServerThreadPool threadPool=new syncServerThreadPool(50,100);
            while(true){
                socket=serverSocket.accept();
                threadPool.execute(new TimeServerSocketHander(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
