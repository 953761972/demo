package com.example.demo.语言基础;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/9/20 19:26
 **/
public class TestObjectSize {
    public static void main(String[] args) throws  IOException {
        int [] i=new int[1<<11];
        String s="11111";
        Map<String,String> map=new HashMap<>();
        map.put("11","22");
        //查看对象内部信息：
//        System.out.println(ClassLayout.parseInstance(i).toPrintable());
//        System.out.println(ClassLayout.parseInstance(s).toPrintable());
//        System.out.println(ClassLayout.parseInstance(map).toPrintable());

        //查看对象外部信息：包括引用的对象：
        //System.out.println(GraphLayout.parseInstance(i).toPrintable());
        //查看对象占用空间总大小：
//        System.out.println(GraphLayout.parseInstance(i).totalSize());
//        System.out.println(GraphLayout.parseInstance(i).totalSize()% (1<<11));
        System.out.println(1<<10);
        System.out.println(2<<10);
        System.out.println(1024>>>11);

    }
}
