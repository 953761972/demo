package com.example.demo.netty.dubbo;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author XZQ
 * @Date 2021/9/15 21:09
 **/
public class RpcProxy {
    public static<T>T create(Class<?> clazz){
        MethodProxy proxy=new MethodProxy(clazz);
        Class<?>[] interfaces=clazz.isInterface()?new Class[]{clazz}:clazz.getInterfaces();
        T result= (T) Proxy.newProxyInstance(clazz.getClassLoader(),interfaces,proxy);
        return result;
    }

    private static class MethodProxy implements InvocationHandler {
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz=clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果传进来的是一个具体实现的类
            if(Object.class.equals(method.getDeclaringClass())){
                try{
                    return method.invoke(this,args);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                return rpcInvok(proxy,method,args);
            }
            return null;
        }

        //实现接口的核心方法
        private Object rpcInvok(Object proxy, Method method, Object[] args) {
                //传输协议封装
            InvokerProtocol msg=new InvokerProtocol();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setValues(args);
            msg.setParames(method.getParameterTypes());

            final RpcProxyHandler consumerhandler=new RpcProxyHandler();
            EventLoopGroup group=new NioEventLoopGroup();
            try{
                Bootstrap b=new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline=ch.pipeline();
                                pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                                pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
                                pipeline.addLast("encoder",new ObjectEncoder());
                                pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                pipeline.addLast("handler",consumerhandler);
                            }
                        });
                ChannelFuture future =b.connect("localhost",8082).sync();
                future.channel().writeAndFlush(msg).sync();

                System.out.println(msg.getClassName());
                System.out.println(msg.getMethodName());
                System.out.println(Arrays.toString(msg.getParames()));
                System.out.println(Arrays.toString(msg.getValues()));

                try (//创建一个ObjectOutputStream输出流
                     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
                    //将对象序列化到文件s
                    oos.writeObject(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                future.channel().closeFuture().sync();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
            return consumerhandler.getResponse();
        }
    }
}
