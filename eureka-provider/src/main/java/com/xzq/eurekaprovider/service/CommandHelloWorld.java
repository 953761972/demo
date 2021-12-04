package com.xzq.eurekaprovider.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author XZQ
 * @Date 2021/11/21 19:38
 **/
public class CommandHelloWorld extends HystrixCommand<String> {
    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Hello " + name + "!";
    }
    //调用实例
    public static void main(String[] args) throws Exception{
        //每个Command对象只能调用一次,不可以重复调用,
        //重复调用对应异常信息
        CommandHelloWorld helloWorldCommand = new CommandHelloWorld("sync-hystrix");
        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        String result = helloWorldCommand.execute();
        System.out.println("result=" + result);

        helloWorldCommand = new CommandHelloWorld("async-hystrix");
        //异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());
    }
}
