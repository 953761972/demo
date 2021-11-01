package com.example.demo.算法.leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author XZQ
 * @Date 2021/10/17 21:45
 **/
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String line1=sc.nextLine();
        String line2=sc.nextLine();
        String[] str=line1.split(" ");
        if(str.length!=3){
            return;
        }
        int n=Integer.valueOf(str[0]);
        int x=Integer.valueOf(str[1]);
        int y=Integer.valueOf(str[2]);

        String record[]=line2.split(" ");
        int[] records=new int[record.length];
        for(int i=0;i< record.length;i++){
            records[i]=Integer.valueOf(record[i]);
        }
        Arrays.sort(records);
        if(6-y<x){
            System.out.println("-1");
            return;
        }
        System.out.println(records[x]);
    }
}
