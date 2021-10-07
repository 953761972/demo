package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
