package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * @Author XZQ
 * @Date 2021/10/30 01:11
 **/
@Configuration
@EnableWebMvc
public class SpringMVC implements WebMvcConfigurer { //1、实现WebMvcConfigurer

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/template/**").addResourceLocations("classpath:/template/");

        //Swagger-ui配置(无效)
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    @Bean
    public InternalResourceViewResolver get(){//2、配置视图解析器（缺一不可）
        InternalResourceViewResolver v =new InternalResourceViewResolver();
        v.setSuffix(".html");
        //v.setPrefix("/template/");     //删除之，swagger-ui才能访问
        return v;
    }
}
