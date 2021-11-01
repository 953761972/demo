package com.example.demo.webservice;

import com.example.demo.bean.Test;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Author XZQ
 * @Date 2021/4/10 19:33
 **/
@Component
@WebService(name="MyWebService",endpointInterface = "com.example.demo.webservice.webserviceTest")
public class WebserviceImpl implements webserviceTest{

    @Override
    public Test TestWebservice(Test t) {
        t.setName("我");
        t.setSex("14");
        return t;
    }
}
