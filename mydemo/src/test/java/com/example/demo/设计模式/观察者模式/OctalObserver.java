package com.example.demo.设计模式.观察者模式;

/**
 * @Author XZQ
 * @Date 2021/4/15 01:46
 **/
public class OctalObserver extends  Observer{
    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
