package com.example.demo.netty.dubbo;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:32
 **/
public class RegisterHander extends ChannelInboundHandlerAdapter {
    //保存所有可用的服务
    public static ConcurrentHashMap<String,Object> registerMap=new ConcurrentHashMap<>();
    //保存所有相关服务类
    private List<String> className=new ArrayList<>();

    public RegisterHander() {
        //完成递归扫描
        scannerClass("com.example.demo.netty.dubbo.impl");
        doRegister();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result =new Object();
        System.out.println("msg:");
        System.out.println(msg.toString());
        InvokerProtocol request= (InvokerProtocol) msg;
        //当用客户端建立连接时，需要从自定义协议中获取信息，以及具体的服务和实参
        //使用反射时调用
        if(registerMap.containsKey(request.getClassName())){
            Object clazz=registerMap.get(request.getClassName());
            Method method=clazz.getClass().getMethod(request.getMethodName(),request.getParames());
            result=method.invoke(clazz,request.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    //完成注册
    private void doRegister() {
        if(className.size()==0){
            return;
        }
        for(String classname:className){
            try{
                Class<?> clazz=Class.forName(classname);
                Class<?> i=clazz.getInterfaces()[0];
                registerMap.put(i.getName(),clazz.newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //递归扫描
    private void scannerClass(String packageName) {
        URL url=this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        System.out.println("URL:"+url.getPath().toString());
        File dir=new File(url.getFile());
        for(File file:dir.listFiles()){
            if(file.isDirectory()){
                scannerClass(packageName+"."+file.getName());
            }else{
                className.add(packageName+"."+file.getName().replace(".class","").trim());
                System.out.println("added:"+packageName+"."+file.getName().replace(".class","").trim());
            }
        }
    }
}
