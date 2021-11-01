package com.example.demo.hbase;


import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author XZQ
 * @Date 2021/8/28 21:37
 **/
public class phoenixTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");

        //这里配置zookeeper的地址 可以是域名或者ip 可单个或者多个(用","分隔)
        String url = "jdbc:phoenix:localhost:2181";
        Properties prop=new Properties();
        prop.setProperty("phoenix.schema.isNamespaceMappingEnabled","true");
        Connection conn = DriverManager.getConnection(url,prop);
        Statement statement = conn.createStatement();

        conn.createStatement().execute("UPSERT INTO myschem.myTable VALUES ('c','d')");
        conn.commit();

        ResultSet rs = statement.executeQuery("select * from myschem.myTable");
        while (rs.next()) {
            String pk = rs.getString("myid");
            String col1 = rs.getString("myname");

            System.out.println("myname=" + pk + ", myname=" + col1);
        }
        // 关闭连接
        rs.close();

        statement.close();
        conn.close();
    }
}
