package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.log;
import com.mysql.cj.xdevapi.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author XZQ
 * @Date 2021/4/10 19:39
 **/
@RestController
public class TestController {
    @Autowired
    StringRedisTemplate   stringRedisTemplate;

    @RequestMapping("/test")
    public String Test(){
        stringRedisTemplate.opsForValue().set("111","222");
       String value= stringRedisTemplate.opsForValue().get("111");
        System.out.println(stringRedisTemplate.boundValueOps("1111").append("3333").toString());
        return value;
    }
    @RequestMapping("/testq")
    public int Testq(HttpServletRequest res, HttpServletResponse resp){
        res.getCookies()[0].setValue("11");
        return res.getCookies().length;
    }
}
