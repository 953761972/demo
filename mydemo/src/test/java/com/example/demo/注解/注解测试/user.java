package com.example.demo.注解.注解测试;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:56
 **/
public class user {
    @test
    public String  name;
    public String value;

    @test(mytest="dd")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
