package com.example.demo.算法.leetcode;

public class 寻找两个正序数组的中位数_004 {
    public static void main(String[] args) {
        int[] nums1={1};
        int[] nums2={};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int lengthnums1=nums1.length;
        int lengthnums2=nums2.length;
        int indexnums1=0;
        int indexnums2=0;
        int indexall=0;
        int middle=(lengthnums1+lengthnums2)/2;
        int smaller;
        int larger;
        while(indexall< middle){
            if( indexnums1<lengthnums1 ){
                indexall++;
                indexnums1++;
            }
        }
    return 4;
    }
}
