package com.example.demo.bean;

import com.example.demo.aop.RequestRequire;
import org.springframework.stereotype.Component;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:01
 **/
@Component
public class TestObj {

    private String test;

    @RequestRequire
    public String getTest() {
        System.out.println("get:"+test);
        return test;
    }

    @RequestRequire
    public void setTest(String test) {
        System.out.println("set:"+test);
        this.test = test;
    }
}
