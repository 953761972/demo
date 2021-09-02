package com.example.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @Author XZQ
 * @Date 2021/9/1 21:56
 **/
public class TestJedis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        // jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        System.out.println("set: "+jedis.set("111","333"));
        System.out.println("get: "+jedis.get("111"));
        jedis.expire("111",1000);
        //client.expire("111",5000);

    }
}
