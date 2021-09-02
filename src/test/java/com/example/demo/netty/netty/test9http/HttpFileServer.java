package com.example.demo.netty.netty.test9http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author XZQ
 * @Date 2021/7/11 20:18
 **/
public class HttpFileServer {
    private static final String DEFAULT_URL="/Volumes/FastSSD/others/projfiles";
    public void run(final int port,final String url){
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup workerGroup =new NioEventLoopGroup();

        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                });
    }
}
