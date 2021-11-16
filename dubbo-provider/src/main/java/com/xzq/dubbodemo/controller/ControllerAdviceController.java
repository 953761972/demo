package com.xzq.dubbodemo.controller;

import com.xzq.dubbodemo.exception.MyException;
import com.xzq.dubbodemo.pojo.ErrorInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author XZQ
 * @Date 2021/11/6 17:43
 **/
@ControllerAdvice
public class ControllerAdviceController {

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> hander(HttpServletRequest req,HttpServletResponse resp,MyException e){
        e.printStackTrace();
        ErrorInfo<String> error=new ErrorInfo<>();
        error.setCode(0);
        error.setMessage("error happends");
        error.setData("data");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handerAll(HttpServletRequest req,HttpServletResponse resp,Exception e){
        e.printStackTrace();
        ErrorInfo<String> error=new ErrorInfo<>();
        error.setCode(0);
        error.setMessage("unknow error happends");
        error.setData("data");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "403";
    }
}
