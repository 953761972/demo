package com.example.demo.netty.netty.test6;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/6/19 19:21
 **/
public class User implements Serializable {
    private int id;
    private String name;
    private String age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] codec(){
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        byte[] value=this.name.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.id);
        buffer.flip();
        value=null;
        byte[] result=new byte[buffer.remaining()];
        buffer.get(result);
        return  result;
    }
}
