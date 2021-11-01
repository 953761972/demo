package com.xzq.dubbodemo.bean;

import org.springframework.stereotype.Component;

/**
 * @Author XZQ
 * @Date 2021/11/1 00:39
 *
**/

@Component
public class Log {
    private Integer logid;

    private Integer age;

    private String name;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logid=" + logid +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}