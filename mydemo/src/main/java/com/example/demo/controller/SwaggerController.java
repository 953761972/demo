package com.example.demo.controller;

import com.example.demo.bean.SwaggerSubTest;
import com.example.demo.bean.SwaggerTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/swagger")
public class SwaggerController {

    @PostMapping("/test")
    @ResponseBody
    public String Test(@RequestBody SwaggerTest swaggerTest ){
        System.out.println(swaggerTest.toString());
        return swaggerTest.toString();
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity Test1(){
        SwaggerTest swaggerTest=new SwaggerTest();
        swaggerTest.setId(1l);
        SwaggerSubTest swaggerSubTest=new SwaggerSubTest();
        swaggerTest.setSwaggerSubTest(swaggerSubTest);
        return ResponseEntity.ok(swaggerTest);
    }
}
