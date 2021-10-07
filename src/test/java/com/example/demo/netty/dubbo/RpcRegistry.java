package com.example.demo.netty.dubbo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:16
 **/
public class RpcRegistry {
    private int port;

    public RpcRegistry(int port) {
        this.port = port;
    }
    public void start(){
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();

        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            /**
                             * 自定义协议解码器
                             * 入参有5个：
                             * maxFrameLength：框架的最大长度，超过会抛异常
                             * lengthFiledOffset：长度属性的偏移量
                             * lengthFiledLength：长度属性的长度
                             * lengthAdjustment：要添加到长度属性的补偿值
                             * initialBytesToStrip：从解码帧中去除的第一个字节数
                             * **/
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast("encoder",new ObjectEncoder());
                            pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(new RegisterHander());

                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture fultur=b.bind(port).sync();
            System.out.println("GP RPC Registery started on :"+port);
            fultur.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
