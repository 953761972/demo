package com.xzq.dubbodemo.controller;

import com.xzq.dubbocommon.bean.Log;
import com.xzq.dubbocommon.service.LogService;
import com.xzq.dubbodemo.config.log;
import com.xzq.dubbodemo.exception.MyException;
import com.xzq.dubbodemo.pojo.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @log
    public String getlog(@PathVariable int logid){
        return logService.gerLogbyId(logid).toString();
    }

    @ResponseBody
    @RequestMapping("/getlog1/{logid}")
    @log
    public ResponseEntity<?> getlog1(@PathVariable int logid) throws  MyException {
        throw new MyException("error happends");
    }

    @ResponseBody
    @RequestMapping("/insert")
    @log
    @Transactional
    public ResponseEntity<?> insertone()  {
        try{
            Log log1=new Log();
            log1.setAge(100);
            log1.setName("dddd");
            logService.Insert(log1);
            Log log2=new Log();
            log2.setLogid(100);
            log2.setAge(100);
            log2.setName("dddd");
            logService.Insert(log2);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("succedd");
        return new ResponseEntity<>(new ErrorInfo<>(), HttpStatus.OK);
    }
}
