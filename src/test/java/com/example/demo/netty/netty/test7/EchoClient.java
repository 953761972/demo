package com.example.demo.netty.netty.test7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author XZQ
 * @Date 2021/6/19 14:24
 * echoServer使用MessagePack
 **/
public class EchoClient {
    public void connect(int port,String host){
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("maspack decoder",new MsgpackDecoder());
                        socketChannel.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
                        socketChannel.pipeline().addLast(new EchoClientHander());
                    }
                });
        try {
            ChannelFuture f =b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port=8080;
        new EchoClient().connect(port,"127.0.0.1");
    }
}
