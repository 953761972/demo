package com.example.demo.算法.leetcode;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串_003 {
    public static void main(String[] args) {
        String s="qwertyuioplkjhgfdsazxcvbnmiuytrew";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map=new HashMap();
        int maxcount=0;
        int count=0;
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                count++;
                map.put(s.charAt(i),i);
            }else{
                maxcount=count>maxcount?count:maxcount;
                i=map.get(s.charAt(i));
                map.clear();
                count=0;
            }
        }
        return maxcount>count?maxcount:count;
    }
}
