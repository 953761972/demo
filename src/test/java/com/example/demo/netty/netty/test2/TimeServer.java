package com.example.demo.netty.netty.test2;

import io.netty.bootstrap.ServerBootstrap;
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
 * timeServer模拟粘包拆包
 **/
public class TimeServer {
    private final int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
            int port=8080;
            new TimeServer(port).start();
            System.out.println("Server started on "+port);

    }

    private void start() throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(group)
        .channel(NioServerSocketChannel.class)
        .localAddress(new InetSocketAddress(port))
        .childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new TimeServerHandler());
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
