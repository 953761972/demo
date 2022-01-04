package com.example.demo.controller;

import com.example.demo.bean.TestObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author XZQ
 * @Date 2021/10/30 00:00
 **/
@Controller
public class indexController {
    @Autowired
    TestObj testObj;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/testaop")
    @ResponseBody
    public String testaop(){
        testObj.setTest("ss");
        return "testaop";
    }
}
