package com.example.demo.算法;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author XZQ
 * @Date 2021/4/9 11:07
 **/
public class 排序算法 {
    @Test
    public void 冒泡排序(){
        int arr[]={1,5,3,9,11,25,13,7};

        for(int i=0;i<arr.length;i++){//外循环定义要循环的次数
            for(int j=0;j<arr.length-1-i;j++){//内循环定义每次循环要比较的次数，因为最后面的i位是已经排好序的。
                if(arr[j]>arr[j+1]){
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void 选择排序(){
        int arr[]={1,5,3,9,11,25,13,7};

        for(int i=0;i<arr.length;i++){
            int minIndex=i;//记录每次循环拿到的最小值的下标
            for(int j=i;j<arr.length-1;j++){
                if(arr[j+1]<arr[minIndex]){
                    minIndex=j+1;
                }
            }
                int tmp=arr[i];
                arr[i]=arr[minIndex];
                arr[minIndex]=tmp;

        }
        System.out.println(Arrays.toString(arr))        ;
    }

    @Test
    public void 插入排序(){
        int arr[]={1,5,3,9,11,25,13,7};

        int current;
        for(int i=1;i<arr.length-1;i++){
            current=i-1;
            int tmp=arr[i];
            while(current>0&&arr[current]>tmp){
                arr[current+1]=arr[current];
                current--;
            }
            arr[current]=tmp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
