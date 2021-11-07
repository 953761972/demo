package com.xzq.dubbodemo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.xzq.dubbodemo.Interceptor.MyInterceptor;
import com.xzq.dubbodemo.Interceptor.MyInterceptor1;
import com.xzq.dubbodemo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/11/6 10:45
 **/
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
//
//    @Bean
//    public Filter Filter(){
//        return new MyFilter();
//    }
//添加拦截
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyInterceptor())//注册自定义拦截器
            .addPathPatterns("/**")//拦截的请求路径
            .excludePathPatterns("/error")//排除的请求路径
            .excludePathPatterns("/static/*");

//    registry.addInterceptor(new MyInterceptor1())//注册自定义拦截器
//            .addPathPatterns("/**")//拦截的请求路径
//            .excludePathPatterns("/error")//排除的请求路径
//            .excludePathPatterns("/static/*");
    }

//    配置Druid的监控
//    配置管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        bean.setInitParameters(initParams);
        return bean;
    }

}
