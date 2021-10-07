package com.example.demo.netty.tomcat.tomcatusenetty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:02
 **/
public class GPResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public GPResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out) throws IOException {
        if(out == null || out.length()==0){
            return;
        }
        try{
            FullHttpResponse response=new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set("Content-Type","text/html");
            ctx.write(response);
        }finally {
            ctx.flush();
            ctx.close();
        }

    }
}
