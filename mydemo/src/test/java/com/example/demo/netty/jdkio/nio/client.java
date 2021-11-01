package com.example.demo.netty.jdkio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author XZQ
 * @Date 2021/6/13 15:06
 **/
public class client implements  Runnable{
    private String host;
    private  int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile  boolean stop;

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key=null;
                System.out.println("key size:"+selectedKeys.size());
                while(it.hasNext()){
                    key=it.next();
                    it.remove();
                    try{
                        handInput(key);
                    }catch (Exception e){
                        if(key !=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(selector !=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc= (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ);
                    doWrite(sc);
                }else{
                    System.exit(1);
                }
            }
            if(key.isReadable()){
                ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                int readBytes=sc.read(readBuffer);
                if(readBytes>0){
                    readBuffer.flip();
                    byte[] bytes=new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body=new String(bytes,"utf-8");
                    System.out.println("received body is :"+body);
                    this.stop=true;
                }else if (readBytes<0){
                    key.cancel();
                    sc.close();
                }else{

                }
            }
        }

    }

    private void doWrite(SocketChannel sc) throws IOException {
        byte[] request="thread".getBytes();
        ByteBuffer writerBuffer=ByteBuffer.allocate(request.length);
        writerBuffer.put(request);
        writerBuffer.flip();
        sc.write(writerBuffer);
        if(!writerBuffer.hasRemaining()){
            System.out.println("send succeed");
        }
    }

    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else{
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    public client(String host,int port){
        this.host=host;
        this.port=port;
        try {
            selector=Selector.open();
            socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
