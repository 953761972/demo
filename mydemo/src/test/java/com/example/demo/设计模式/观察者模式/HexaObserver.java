package com.example.demo.设计模式.观察者模式;

/**
 * @Author XZQ
 * @Date 2021/4/15 01:46
 **/
public class HexaObserver extends Observer{
    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
