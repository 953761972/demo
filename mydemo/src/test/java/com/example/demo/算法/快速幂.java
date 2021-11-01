package com.example.demo.算法;

/**
 * @Author XZQ
 * @Date 2021/10/6 19:45
 **/
public class 快速幂 {
    public static void main(String[] args) {
        System.out.println(QuickPow(2,10));
        System.out.println(QuickPow1(2,10));

    }
    public static int QuickPow(int x,int N)
    {
        int res = x;
        int ans = 1;
        while(N!=0)
        {
            if((N & 1) >0)//最后1位是1，则是奇数
            {
                ans = ans * res;//因为对奇数做无符号右移会多丢失1个1，所以收集1个当前底数，即res
            }
            res = res*res;//底数平方
            N = N>>1;//指数除2
        }
        return ans;
    }
    public static int QuickPow1(int x,int N)
    {
        int res = x;
        int ans = 1;
        while(N!=0)
        {
            if(N % 2 ==0){
                res*=res;
                N=N/2;
            }else{
                N=N-1;
                ans*=res;
            }
        }

        return ans;
    }
}
