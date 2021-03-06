package com.xzq.dubbodemo.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author XZQ
 * @Date 2021/11/1 01:38
 **/
@Configuration
@NacosConfigurationProperties(dataId = "local", autoRefreshed = true)
@MapperScan(basePackages = "com.xzq.dubbodemo.dao")
public class mybatisConfig {
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;
    @Value("${spring.datasource.url}")
    String jdbcUrl;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

//    @Bean
//    public DataSource getDataSource(){
//        HikariConfig config=new HikariConfig();
//        config.setDriverClassName(driverClassName);
//        config.setJdbcUrl(jdbcUrl);
//        config.setPassword(password);
//        config.setUsername(username);
//        DataSource source=new HikariDataSource(config);
//        return source;
//    }

    @Bean(initMethod = "init",destroyMethod = "close") //??????spring???????????????????????????
    public DataSource getDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        //druidDataSource.setFilters("stat,wall");//??????sql???????????????
        //??????????????????????????????sql???????????????
        List<com.alibaba.druid.filter.Filter> l=new ArrayList<>();
        l.add(statFilter());
        l.add(wallFilter());
        druidDataSource.setProxyFilters(l);
        return druidDataSource;
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

    //????????????
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> map = new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");
        //??????????????????
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setInitParameters(map);
        bean.setEnabled(true);
        return bean;
    }

    //??????3???????????????spring????????????????????????????????????
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("com.xzq.dubbodemo.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

    //alibaba????????????????????????sql
    @Bean
    public StatFilter statFilter(){

        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(3);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        //??????????????????????????????
        wallConfig.setNoneBaseStatementAllow(true);
        //????????????????????????????????????????????????
        wallConfig.setStrictSyntaxCheck(false);
        //?????????????????????????????????
        return wallConfig;
    }
}
