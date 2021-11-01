package com.example.demo.classfile.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/10/2 00:54
 **/
public class targetClass {
    private String name;
    private boolean is_ok;
    private int age;
    private static long count;

    static{
        count=10;
    }

    public  int getAge(List<Integer> l){
        l.forEach(b->{

        });

        while(l.iterator().hasNext()){

        }
        return age;
    }

    public String getName(){
        return "ssss";
    }

    public String getAll(boolean flag){
        if(flag){
            return  getName();
        }
        else{
            return null;
        }
    }
}
