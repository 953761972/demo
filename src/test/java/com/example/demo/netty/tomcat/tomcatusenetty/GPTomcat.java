package com.example.demo.netty.tomcat.tomcatusenetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:15
 **/
public class GPTomcat {
    private int port =8081;
    private ServerSocket server;
    private Map<String, GPServlet> servletMapping=new HashMap<String,GPServlet>();
    private Properties webxml=new Properties();

    private void init(){
        //加载web。xml文件
        String WEB_INF=this.getClass().getResource("/").getPath();
        try{
            FileInputStream fis=new FileInputStream(WEB_INF+ "/web.properties");
            webxml.load(fis);
            for(Object k:webxml.keySet()){
                String key=k.toString();
                if(key.endsWith(".url")){
                    String servletName=key.replaceAll("\\.url$","");
                    String url= webxml.getProperty(key);
                    String className=webxml.getProperty(servletName+".className");
                    GPServlet obj= (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url,obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void  start(){
        init();
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap server=new ServerBootstrap();
        try {

            server.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new GPTomcatHanlder(servletMapping));
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture f=server.bind(port).sync();
            System.out.println("服务器已启动，端口："+port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
