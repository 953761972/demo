package com.example.demo.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/4/11 01:50
 **/
@SpringBootTest
public class jdbctemplate {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {

    }

    @Test
    public void testjdbc() throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("select * from HR.employees where employee_id=?");
        statement.setString(1,"100");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getArray(1));
        }

    }
    @Test
    public void contextLoads() throws SQLException {
        // 获取配置的数据源
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        conn.setAutoCommit(false);
        List<?> resultList = jdbcTemplate.queryForList("select * from HR.employees where rownum <10");
        System.out.println("===>>>>>>>>>>>");
        resultList.forEach(a-> System.out.println(a));
    }
}
