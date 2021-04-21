package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author XZQ
 * @Date 2021/4/10 19:39
 **/
@RestController
public class TestController {

    @RequestMapping("/test")
    public String Test(){
        return "Test";
    }

}
