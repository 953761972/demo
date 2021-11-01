package com.xzq.dubbodemo.controller;

import com.xzq.dubbocommon.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author XZQ
 * @Date 2021/11/1 00:23
 **/
@Controller
@RequestMapping("/")
public class logContorller {
    @Resource
    LogService logService;

    @ResponseBody
    @RequestMapping("/getlog/{logid}")
    public String getlog(@PathVariable int logid){
        return logService.gerLogbyId(logid).toString();
    }
}
