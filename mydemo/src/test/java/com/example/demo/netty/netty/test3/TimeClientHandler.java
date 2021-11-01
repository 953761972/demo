package com.example.demo.netty.netty.test3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * @Author XZQ
 * @Date 2021/6/14 23:23
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private static  final Logger logger= Logger.getLogger(TimeClientHandler.class.getName());
    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req=("query time"+System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        String body= (String) msg;
        System.out.println("Now is "+body+",counter is "+ ++counter);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message=null;
        for(int i=0;i<10;i++){
            message= Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    logger.warning("Exception happening:"+cause.getMessage());
    ctx.close();
    }
}
