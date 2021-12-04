package com.example.demo.netty.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Author XZQ
 * @Date 2021/12/4 02:09
 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5050);

        // 要发送给服务器的信息
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("客户端发送信息");
        pw.flush();
        socket.shutdownOutput();

        // 从服务器接收的信息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务器返回信息：" + info);

        }
    }
}
