package com.example.demo.netty.netty.test2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * @Author XZQ
 * @Date 2021/6/14 23:23
 **/
public class TimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static  final Logger logger= Logger.getLogger(TimeClientHandler.class.getName());
    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req=("query time"+System.getProperty("line.separator")).getBytes();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        ByteBuf buf=msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"utf-8");
        System.out.println("Now is "+body+",counter is "+ ++counter);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message=null;
        for(int i=0;i<1000;i++){
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
