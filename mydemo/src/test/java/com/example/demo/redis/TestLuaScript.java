package com.example.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

/**
 * @Author XZQ
 * @Date 2021/9/22 19:19
 **/
public class TestLuaScript {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");

        String script="local list = {};\n" +
                "local item = false;\n" +
                "local num = tonumber(KEYS[2]);\n" +
                "while (num > 0)\n" +
                "do\n" +
                "    item = redis.call('LPOP', KEYS[1]);\n" +
                "    if item == false then\n" +
                "        break;\n" +
                "    end;\n" +
                "    table.insert(list, item);\n" +
                "    num = num - 1;\n" +
                "end;\n" +
                "return list;";
        System.out.println(jedis.eval("return 'hello'"));
        System.out.println(jedis.eval(script));
    }
}
