package com.xzq.dubbodemo.controller;

import com.xzq.dubbodemo.bean.TUser;
import com.xzq.dubbodemo.dao.TUserMapper;
import com.xzq.dubbodemo.utils.MD5Util;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author XZQ
 * @Date 2021/11/9 19:50
 **/
@Controller
public class Login {

    @Autowired
    TUserMapper tusermapper;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(TUser user,String rememberMe){
        boolean flag=false;
        if(rememberMe !=null && rememberMe.equals("on")){
            flag=true;
        }
        UsernamePasswordToken token =new UsernamePasswordToken(user.getUsername(), MD5Util.encrypt(user.getPasswd()),flag);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return new ResponseEntity("ok",HttpStatus.OK);
        } catch (UnknownAccountException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.UNAUTHORIZED);
        } catch (IncorrectCredentialsException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.UNAUTHORIZED);
        } catch (LockedAccountException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
