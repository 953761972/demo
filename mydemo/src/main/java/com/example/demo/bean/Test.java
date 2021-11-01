package com.example.demo.bean;

import org.springframework.web.bind.annotation.MatrixVariable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author XZQ
 * @Date 2021/4/10 19:33
 **/
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name="Test")
public class Test {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
