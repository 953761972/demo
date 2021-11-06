package com.xzq.dubbodemo.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author XZQ
 * @Date 2021/11/6 11:01
 **/
//@Component
public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getTimestamp());
        System.out.println();
    }
}
