package com.example.demo.netty.netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author XZQ
 * @Date 2021/6/14 10:22
 **/
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
            int port=8080;
            new EchoServer(port).start();
            System.out.println("Server started on "+port);

    }

    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler=new EchoServerHandler();
        EventLoopGroup group=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(group)
        .channel(NioServerSocketChannel.class)
        .localAddress(new InetSocketAddress(port))
        .childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(serverHandler);
            }
        });
        try {
            ChannelFuture f=b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
