package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author XZQ
 * @Date 2021/10/30 00:00
 **/
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
