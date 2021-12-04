package com.xzq.eurekaconsumer.controller;

import com.xzq.eurekaconsumer.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @Author XZQ
 * @Date 2021/11/21 16:48
 **/
@Controller
public class testController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    com.xzq.eurekaconsumer.service.testService testService;

    @RequestMapping("/restGet")
    @ResponseBody
    public String gettest(){
        return restTemplate.getForEntity("http://localhost:8080/getInfo",String.class).getBody();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        return testService.get();
    }

    @RequestMapping("/feginTest")
    @ResponseBody
    public String feginTest(){
        return testService.feginTest();
    }

    @RequestMapping("/Hystrixtest")
    @ResponseBody
    public String Hystrixtest(){
        return testService.Hystrixtest();
    }
}
