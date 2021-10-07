package com.example.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Author XZQ
 * @Date 2021/9/1 23:03
 **/
public class TestRedisson {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redisson = Redisson.create(config);
        RBucket<String> bucket = redisson.getBucket("anyObject");
        //bucket.set("set");
        String obj = bucket.get();
        System.out.println(obj.toString());
        bucket.trySet("trySet");
        bucket.compareAndSet("trySet", "compareAndSetAndSet");
        System.out.println(bucket.getAndSet("getAndSet"));
        System.out.println(bucket.get());
    }
}
