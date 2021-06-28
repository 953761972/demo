package com.example.demo.netty.netty.test7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.msgpack.type.Value;

/**
 * @Author XZQ
 * @Date 2021/6/19 14:14
 **/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    int counter=0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Value user= (Value) msg;
        System.out.println("this is "+ ++counter+",received body is :"+new String(user.toString()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("出现异常退出。。。");
        ctx.close();
    }
}
