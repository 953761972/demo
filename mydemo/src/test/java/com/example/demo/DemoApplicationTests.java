package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

@SpringBootTest
class DemoApplicationTests {
//    @Autowired
//    JedisPool JedisPool;
@Autowired
private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        int i=9;
        i*=0.1;
        System.out.println(i);
        Map m =new HashMap<>();
        m.put(8,8);
        System.out.println(m);
        float f =3.4f;
        double ff=3.4;
        List<Integer> l=new ArrayList<>();
        for(int j=0;j<4;j++){
            l.add(j);
        }
        String str="sddd";
        System.out.println(l);
        char[] ch=str.toCharArray();
        System.out.println(ch);

        System.out.println(String.format("%sms",System.currentTimeMillis()));
    }

    @Test
    public void testother(){
        Integer i5=128;
        Integer i6=128;
        System.out.println(i5==i6);
        Integer i7=127;
        Integer i8=127;
        System.out.println(i7==i8);
        System.out.println(i7.equals(i8));
    }

    @Test
    public void testclass(){
        A ab=new B();
        ab=new B();
    }

    @Test
    public void testredis(){
//        JedisPool.getResource().set("w","s");
//        System.out.println(JedisPool.getResource().get("w"));
        ValueOperations ops=redisTemplate.opsForValue();
        ops.set("1","2");
    }

}
class A{
    private String str ="父类";
    static{
        System.out.println("1");
    }
    public A(){
        System.out.println("2");
    }
}

class B extends A{
    static{
        System.out.println("a");
    }
    public B(){
        System.out.println("b");
        System.out.println();
    }
}