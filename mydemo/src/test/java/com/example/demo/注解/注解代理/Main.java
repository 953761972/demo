package com.example.demo.注解.注解代理;

import com.example.demo.aop.RequestRequireAspect;
import com.example.demo.bean.TestObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:04
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Main {

    @Autowired
    TestObj testObj;

    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(RequestRequireAspect.class);
        TestObj t= (TestObj) applicationContext.getBean("TestObj");
        t.setTest("ss");
    }

    @Test
    public void test() {
        testObj.setTest("dddd");
    }
}
