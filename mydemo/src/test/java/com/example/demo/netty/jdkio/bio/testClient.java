package com.example.demo.netty.jdkio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author XZQ
 * @Date 2021/6/13 00:31
 **/
public class testClient {
    public static void main(String[] args) throws IOException {
        Socket socket=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            socket=new Socket("127.0.0.1",8080);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out =new PrintWriter(socket.getOutputStream(),true);
            out.println("time");
            System.out.println("asking time send");
            String resp=in.readLine();
            System.out.println(resp);
            out.println("thread");
            resp=in.readLine();
            System.out.println(resp);
            out.println("thread");
            resp=in.readLine();
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
            socket.close();
        }
    }
}
