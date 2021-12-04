package com.xzq.eurekaprovider.controller;

import com.xzq.eurekaprovider.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @Author XZQ
 * @Date 2021/11/21 14:30
 **/
@Controller
public class testController {
    @Autowired
    EurekaDiscoveryClient client;

    @Autowired
    com.xzq.eurekaprovider.service.testService testService;

    @RequestMapping("getInfo")
    @ResponseBody
    public String getInfo(){
        String result="";
        result += Arrays.toString(client.getServices().toArray());
        result+="\n";
        ServiceInstance instance=client.getInstances("testeureakprovider").get(0);
        result+=instance.getInstanceId();
        result+=" uri:"+instance.getUri();
        return result;
    }

    @RequestMapping("feginTest")
    @ResponseBody
    public String feginTest(){
        return testService.feginTest();
    }

    @RequestMapping("Hystrixtest")
    @ResponseBody
    public ResponseEntity Hystrixtest() throws InterruptedException {
        return new ResponseEntity(testService.Hystrixtest(),HttpStatus.OK);
    }
}
