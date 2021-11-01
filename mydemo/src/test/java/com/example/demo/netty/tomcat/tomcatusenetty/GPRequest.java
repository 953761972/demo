package com.example.demo.netty.tomcat.tomcatusenetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/9/14 21:56
 **/
public class GPRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public HttpRequest getReq() {
        return req;
    }
    public String getUrl(){
        return req.uri();
    }
    public String getMethod(){
        return req.method().name();
    }
    public Map<String, List<String>> getParameters(){
        QueryStringDecoder decoder=new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }
    public String getParameters(String name){
        Map<String, List<String>> params=getParameters();
        List<String> param=params.get(name);
        if(null == param){
            return null;
        }else{
            return param.get(0);
        }
    }
}
