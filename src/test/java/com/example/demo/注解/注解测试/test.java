package com.example.demo.注解.注解测试;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author XZQ
 * @Date 2021/4/20 22:52
 **/
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface test {
    public String value = "";

     public String mytest() default "";

}
