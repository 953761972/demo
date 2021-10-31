package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * @Author XZQ
 * @Date 2021/10/30 01:11
 **/
@Configuration
public class SpringMVC implements WebMvcConfigurer { //1、实现WebMvcConfigurer

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/template/**").addResourceLocations("classpath:/template/");
    }

    @Bean
    public InternalResourceViewResolver get(){//2、配置视图解析器（缺一不可）
        InternalResourceViewResolver v =new InternalResourceViewResolver();
        v.setSuffix(".html");
        v.setPrefix("/template/");
        return v;
    }
}
