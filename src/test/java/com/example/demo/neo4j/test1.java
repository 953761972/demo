package com.example.demo.neo4j;

import org.neo4j.driver.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.neo4j.driver.Values.parameters;

/**
 * @Author XZQ
 * @Date 2021/9/25 11:57
 **/
public class test1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


//注意，使用服务器开发的方式，Neo4j必须是开启的状态
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "root"));
        Session session = driver.session();
        session.run("CREATE (a:Person {name{$name}, titl {$title}})",
                parameters("name", "Arthur001", "title", "King001"));
        Result result = session.run("MATCH (a:Person) WHERE a.name = {$name} " +
                        "RETURN a.name AS name, a.title AS title",
                parameters("$name", "Arthur001"));
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("title").asString() + " " + record.get("name").asString());
        }
        session.close();
        driver.close();

    }

}
