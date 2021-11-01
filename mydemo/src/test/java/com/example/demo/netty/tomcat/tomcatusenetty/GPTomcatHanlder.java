package com.example.demo.netty.tomcat.tomcatusenetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:55
 **/
public class GPTomcatHanlder extends ChannelInboundHandlerAdapter {
    private Map<String, GPServlet> servletMapping=new HashMap<String,GPServlet>();

    public GPTomcatHanlder(Map<String,GPServlet> servletMapping) {
        this.servletMapping=servletMapping;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            System.out.println("hello");
            HttpRequest req= (HttpRequest) msg;
            GPRequest request=new GPRequest(ctx,req);
            GPResponse response=new GPResponse(ctx,req);
            String url=request.getUrl();
            if(servletMapping.containsKey(url)){
                servletMapping.get(url).service(request,response);
            }else{
                response.write("404 - Not Found");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
