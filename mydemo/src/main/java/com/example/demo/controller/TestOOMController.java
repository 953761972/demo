package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/11/1 16:35
 **/
@Controller
public class TestOOMController {
    Map map=new HashMap<String,byte[]>();
    int i=0;
    @RequestMapping("/testOOM")
    @ResponseBody
    public String testOOM(@RequestParam("file") MultipartFile file) throws IOException {
        map.put(i++,file.getBytes());
        return "success,size:"+file.getSize();
    }

    @RequestMapping("/testStackOOM/{count}")
    @ResponseBody
    public long testStackOOM(@PathVariable("count") long count) throws IOException {
        return add(count,1);
    }

    public long add(long count,long b){
        long num = 0;
        if(count>0){
          num= add(--count,b++);
        }
        return num;
    }
}
