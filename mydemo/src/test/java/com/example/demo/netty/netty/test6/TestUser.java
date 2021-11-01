package com.example.demo.netty.netty.test6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author XZQ
 * @Date 2021/6/19 19:28
 **/
public class TestUser {
    public static void main(String[] args) throws IOException {
        User user=new User();
        user.setAge("11");
        user.setId(333);
        user.setName("我");
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(user);
        oos.flush();
        oos.close();
        byte[] b=bos.toByteArray();
        System.out.println("jdk serialization length is "+b.length);
        System.out.println(new String(b,"utf-8"));
        bos.close();
        System.out.println("byte array length is "+user.codec().length);
        System.out.println(new String(user.codec(),"utf-8"));
    }
}
