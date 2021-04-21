package com.example.demo.注解.注解代理;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author XZQ
 * @Date 2021/4/20 21:59
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestRequire {

    /**
     * 接口所需要验证参数,小写逗号隔开
     */
    String require() default "";

    /**
     *传递参数的对象类型
     */
    Class<?> parameter() default Object.class;
}
