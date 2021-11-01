package com.example.demo.算法.leetcode;

import org.junit.Test;
import org.redisson.misc.Hash;

import java.util.*;

/**
 * @Author XZQ
 * @Date 2021/10/8 21:40
 **/
public class 重复的DNA序列_187 {

    public static List<String> findRepeatedDnaSequences(String s) {
        Map<String,Integer> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        for(int i=0;i<=s.length()-10;i++){
            String str=s.substring(i,i+10);
            map.put(str,map.getOrDefault(str,0)+1);
        }
        for(String key:map.keySet()){
            if(map.get(key)>=2){
                list.add(key);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
}
