package com.xzq.demo.controller;

import com.xzq.demo.domain.Test;
import com.xzq.demo.repository.TestRepository;
import com.xzq.demo.service.IndexService;
import com.xzq.demo.vm.SystemInfo;
import com.xzq.demo.vm.TestDto;
import com.xzq.demo.vm.mapper.TestDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownHostException;

@Controller
@RequestMapping("/test")
@ResponseBody
public class indexController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestDtoMapper testDtoMapper;

    @Autowired
    IndexService indexService;

    @GetMapping("/index")
    public String index(){
        System.out.println("index:" +System.currentTimeMillis());
        return "index";
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<TestDto> get(@PathVariable("id") Long id){
        Test t=testRepository.getOne(id);
        System.out.println("get:" +System.currentTimeMillis());
        return ResponseEntity.ok().body(testDtoMapper.toDto(t));
    }

    @GetMapping("/save")
    public ResponseEntity<TestDto> save(Test test){
        Test t=testRepository.save(test);
        System.out.println("save:" +System.currentTimeMillis());
        return ResponseEntity.ok().body(testDtoMapper.toDto(t)) ;
    }

    @GetMapping("/getSystemInfo")
    public ResponseEntity<SystemInfo> getSystemInfo(Test test) throws UnknownHostException {
        SystemInfo systemInfo=indexService.getSystemInfo();
        System.out.println("getSystemInfo:" +System.currentTimeMillis());
        return ResponseEntity.ok().body(systemInfo);
    }
}
