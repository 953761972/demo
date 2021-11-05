package com.xzq.dubboconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @Author XZQ
 * @Date 2021/11/1 12:16
 **/
@Controller
public class LogController {
//    @NacosInjected
//    LogService rPCLogService;
    private final RestTemplate restTemplate;

    @Autowired
    private LogController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

//    @RequestMapping("getLog")
//    @ResponseBody
//    public String getLog(int logid){
//        return rPCLogService.gerLogbyId(logid).toString();
//    }

    @RequestMapping(value = "/getlog/{str}", method = RequestMethod.GET)
    @ResponseBody
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://provider/getlog/" + str, String.class);
    }
}
