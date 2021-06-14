package com.example.demo.netty.jdkio.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author XZQ
 * @Date 2021/6/13 00:03
 **/
public class TimeServerSocketHander implements Runnable{
    private Socket socket;

    public TimeServerSocketHander(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        BufferedReader in=null;
        PrintWriter out=null;

        try {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            String currentTime=null;
            String body=null;
            while(true){
                body=in.readLine();
                if(body==null){
                    break;
                }
                System.out.println("Body:"+body);
                if("time".equalsIgnoreCase(body)){
                    currentTime=new Date().toString();
                }else if("thread".equalsIgnoreCase(body)){
                    currentTime=Thread.currentThread().getName();
                }else {
                    currentTime="bad request";
                }

                out.println(currentTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
