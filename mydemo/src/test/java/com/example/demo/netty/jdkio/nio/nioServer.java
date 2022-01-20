package com.example.demo.netty.jdkio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author XZQ
 * @Date 2021/6/13 14:11
 **/
public class nioServer implements  Runnable{
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    public nioServer(int port){
        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("socket started on "+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void stop(){
        this.stop=true;
    }
    @Override
    public void run() {
        while(!stop){
            try {
                selector.select();
                Set<SelectionKey> selectdkeys = selector.selectedKeys();
                Iterator<SelectionKey> it=selectdkeys.iterator();
                SelectionKey key=null;
                System.out.println("key size:"+selectdkeys.size());
                while(it.hasNext()){
                    key=it.next();
                    it.remove();
                    try{
                        if(key.isAcceptable()){
                            System.out.println("isAcceptable");
                        }
                        if(key.isReadable()){
                            System.out.println("isReadable");
                        }
                        if(key.isValid()){
                            //System.out.println("isValid");
                        }
                        if(key.isConnectable()){
                            System.out.println("isConnectable");
                        }
                        if(key.isWritable()){
                            System.out.println("isWritable");
                        }
                        handInput(key);
                    }catch (Exception e){
                        e.printStackTrace();
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc= (ServerSocketChannel) key.channel();
                SocketChannel sc=ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer bf=ByteBuffer.allocate(1024);
                int readBytes=sc.read(bf);
                if(readBytes>0){
                    bf.flip();
                    byte[] bytes=new byte[bf.remaining()];
                    bf.get(bytes);
                    String body=new String(bytes,"utf-8");
                    System.out.println("received body is :"+body);
                    String responStr="";
                    if("time".equalsIgnoreCase(body)){
                        responStr=new Date().toString();
                    }else if("thread".equalsIgnoreCase(body)){
                        responStr=Thread.currentThread().getName();
                    }else {
                        responStr="bad request";
                    }
                    System.out.println("response:"+responStr);
                    doWrite(sc,responStr);
                }else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }else{
                    ;
                }
            }
        }

    }

    private void doWrite(SocketChannel sc,String responStr) throws IOException {
        byte[] bytes=responStr.getBytes();
        ByteBuffer wirteBuffer=ByteBuffer.allocate(1024);
        wirteBuffer.put(bytes);
        wirteBuffer.flip();
        sc.write(wirteBuffer);
        sc.shutdownOutput(); //要加这句jemeter客户端才会认为结束了
        System.out.println("返回完成");
    }
}
