package com.example.demo.redis;

import io.rebloom.client.Client;

/**
 * @Author XZQ
 * @Date 2021/9/1 23:01
 **/
public class TestRebloom {
    public static void main(String[] args) {

        Client client1 = new Client("localhost", 6379);
        System.out.println("add:"+client1.add("1","2"));
        System.out.println("add:"+client1.add("1","8"));
        System.out.println("exists:"+client1.exists("1","8"));
        System.out.println(client1.topkAdd("2","3","4","5"));
        System.out.println(client1.topkList("2").toString());
    }
}
