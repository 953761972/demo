package com.example.demo.netty.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author XZQ
 * @Date 2021/11/29 23:43
 **/
public class testSocket {
    public static void main(String[] args) throws IOException {

        r r=new r();
        Thread t=new Thread(r);
        t.start();
    }


}
class r implements Runnable{

    @Override
    public void run() {
        ServerSocket s= null;
        InputStream in = null;
        BufferedReader bin = null;
        try {
            int i=0;
            s = new ServerSocket(5050);
            while(true){
                System.out.println("====================="+i++);
                Socket socket=s.accept();
                in=socket.getInputStream();
                bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str=null;
                while((str=bin.readLine())!=null){
                    if("\\n\\r".equals(str)){
                        System.out.println("break:"+str);
                        break;
                    }
                    System.out.println(str);
                }
                // 从服务器接收的信息
                OutputStream os = socket.getOutputStream();
                os.write("ok".getBytes());
                String info = null;
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(s!=null){
                try {
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if(bin!=null){
                try {
                    bin.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
