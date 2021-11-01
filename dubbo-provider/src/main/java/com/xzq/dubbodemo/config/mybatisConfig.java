package com.xzq.dubbodemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author XZQ
 * @Date 2021/11/1 01:38
 **/
@Configuration
//@NacosConfigurationProperties(dataId = "local", autoRefreshed = true)
@MapperScan(basePackages = "com.xzq.dubbodemo.dao",annotationClass = Mapper.class)
public class mybatisConfig {
    //@NacosValue(value="${spring.datasource.driver-class-name}",autoRefreshed = true)
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;
    //@NacosValue(value="${spring.datasource.url}",autoRefreshed = true)
    @Value("${spring.datasource.url}")
    String jdbcUrl;
    //@NacosValue(value="${spring.datasource.username}",autoRefreshed = true)
    @Value("${spring.datasource.username}")
    String username;
    //@NacosValue(value="${spring.datasource.password}",autoRefreshed = true)
    @Value("${spring.datasource.password}")
    String password;

    @Bean
    public DataSource getDataSource(){
        HikariConfig config=new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setPassword(password);
        config.setUsername(username);
        DataSource source=new HikariDataSource(config);
        return source;
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource) throws IOException {
        String resource = "classpath:/mapper/*Mapper.xml";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources1 = resolver.getResources(resource);

        SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(resources1);
        return sqlSessionFactory;
    }
}
