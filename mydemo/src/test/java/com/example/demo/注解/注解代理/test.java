package com.example.demo.注解.注解代理;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:01
 **/
public class test {

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
