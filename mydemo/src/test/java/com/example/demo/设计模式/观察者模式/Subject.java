package com.example.demo.设计模式.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/4/15 01:43
 **/
public class Subject {

    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}