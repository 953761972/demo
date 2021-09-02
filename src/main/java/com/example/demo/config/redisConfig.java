package com.example.demo.config;

import com.example.demo.webservice.WebserviceImpl;
import com.example.demo.webservice.webserviceTest;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

/**
 * @Author XZQ
 * @Date 2021/4/1 15:03
 **/
@Configuration
public class redisConfig {
    @Bean
    public JedisPool JedisPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        return new JedisPool(config,"localhost",6379,1000);
    }


    @Bean
    public ServletRegistrationBean ServletRegister() {
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public webserviceTest demoService() {
        return new WebserviceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),demoService());
        endpoint.publish("/api");
        return endpoint;
    }
}
