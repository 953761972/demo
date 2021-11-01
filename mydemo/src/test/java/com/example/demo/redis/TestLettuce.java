package com.example.demo.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author XZQ
 * @Date 2021/9/2 00:00
 **/
public class TestLettuce {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        System.out.println("Connected to Redis");
        connection.sync().set("key", "Hello World");
        System.out.println(connection.sync().get("key"));
        connection.sync().hset("hsetkey", "hsetkey","Hello World");
        System.out.println(connection.sync().hget("hsetkey", "hsetkey"));
        connection.close();
        redisClient.shutdown();

    }
}
