package com.xzq.dubbodemo.exception;

/**
 * @Author XZQ
 * @Date 2021/11/6 16:00
 **/

public class MyException extends Exception{
    /**
     *  自定义项目内异常
     */
    public MyException(String message){
        super(message);
    }

}
