package com.example.demo.netty.netty.test4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author XZQ
 * @Date 2021/6/19 14:14
 **/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    int counter=0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body= (String) msg;
        System.out.println("this is "+ ++counter+",received body is :"+body);
        body+="$_";
        ByteBuf echo= Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
