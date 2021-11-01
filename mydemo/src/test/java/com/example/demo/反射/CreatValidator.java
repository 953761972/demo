package com.example.demo.反射;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author XZQ
 * @Date 2021/4/13 11:34
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatValidator {
    String[] param() default {};
}
