package com.example.demo.webservice;

import com.example.demo.bean.Test;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author XZQ
 * @Date 2021/4/10 19:32
 **/
@WebService(name="MyWebService")
public interface webserviceTest {

    public Test TestWebservice(@WebParam(name ="Test")Test t);
}
