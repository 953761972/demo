package com.example.demo.反射;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author XZQ
 * @Date 2021/4/13 11:37
 **/

@Retention(RetentionPolicy.RUNTIME)
public @interface Convert {
    String title();

    String typeInfo() default "";

    Class<? extends Converter> convert() default Converter.class;
}
