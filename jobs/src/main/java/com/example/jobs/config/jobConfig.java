package com.example.jobs.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @Author XZQ
 * @Date 2021/11/28 10:21
 **/
@Configuration
public class jobConfig {

    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(){
        SchedulerFactoryBean f=new SchedulerFactoryBean();
        Resource r=new ClassPathResource("quartz.properties");
        f.setConfigLocation(r);
        return f;
    }
}
