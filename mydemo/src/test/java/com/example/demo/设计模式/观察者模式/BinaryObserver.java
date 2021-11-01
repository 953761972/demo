package com.example.demo.设计模式.观察者模式;

/**
 * @Author XZQ
 * @Date 2021/4/15 01:45
 **/
public class BinaryObserver extends  Observer{
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
