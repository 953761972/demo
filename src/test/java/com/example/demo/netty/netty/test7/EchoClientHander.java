package com.example.demo.netty.netty.test7;

import com.example.demo.netty.netty.test7.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author XZQ
 * @Date 2021/6/19 14:34
 **/
public class EchoClientHander extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User[] users=getusers(100);
        for(User u:users){
            ctx.write(u);
        }
        ctx.flush();
    }

    private User[] getusers(int nums) {
        User[] users=new User[nums];
        User user=null;
        for(int i=0;i<nums;i++){
            user=new User();
            user.setName("aa"+i);
            users[i]=user;
        }
        return users;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is "+ ++counter+",received msg is :"+msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
