package com.example.demo.netty.netty.test3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author XZQ
 * @Date 2021/6/14 10:41
 * timeServer使用LineBasedFrameDecoder解决粘包拆包
 **/
public class TimeClient {

    public void connect(int port,String host){

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        try {
        b.group(group).channel( NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new TimeClientHandler());
                    }
                });
            ChannelFuture f =b.connect(host,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port=8080;
        new TimeClient().connect(port,"127.0.0.1");
    }
}
