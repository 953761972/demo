package com.example.demo.netty.dubbo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author XZQ
 * @Date 2021/9/15 21:36
 **/
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;
    public Object getResponse() {
        return  response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response=msg;
        System.out.println("msg:");
        System.out.println(msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
