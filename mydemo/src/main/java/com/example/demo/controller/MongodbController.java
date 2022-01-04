package com.example.demo.controller;

import com.example.demo.repository.MongoRepository;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author XZQ
 * @Date 2021/10/30 09:18
 **/
@Controller
public class MongodbController {

    @Autowired
    MongoRepository repos;

    @GetMapping("/getFileFromMongo")
    @ResponseBody
    public String get(@RequestParam("fileid")String fileid){
        System.out.println("get:"+fileid);
        return repos.get("test","test",fileid);
    }

    @PostMapping("/putFileToMongo")
    @ResponseBody
    public String put(@RequestParam("file") MultipartFile file) throws IOException {
        return repos.save("test","test",file);
    }
}
