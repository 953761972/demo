package com.example.demo.设计模式.观察者模式;

/**
 * @Author XZQ
 * @Date 2021/4/15 01:44
 **/
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
