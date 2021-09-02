package com.example.demo.语言基础;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/7/10 09:58
 **/
public class TestClone {
    public static void main(String[] args) {
        ArrayList<String> l=new ArrayList<String>();
        l.add("1");
        List<String> l2= (List<String>) l.clone();
        l2.add("2");
        System.out.println(l.toString());
        System.out.println(l2.toString());
    }
}
