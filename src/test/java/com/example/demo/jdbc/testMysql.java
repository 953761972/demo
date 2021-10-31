package com.example.demo.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/10/18 23:57
 **/
@SpringBootTest
public class testMysql {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void createData() throws SQLException {
        // 获取配置的数据源
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        conn.setAutoCommit(false);
        List<?> resultList = jdbcTemplate.queryForList("select * from HR.employees where rownum <10");
        System.out.println("===>>>>>>>>>>>");
        resultList.forEach(a-> System.out.println(a));
    }
}
