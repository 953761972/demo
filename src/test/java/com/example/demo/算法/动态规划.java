package com.example.demo.算法;

import org.junit.Test;

/**
 * @Author XZQ
 * @Date 2021/10/7 13:10
 **/
public class 动态规划 {

    //斐波那契数列
    //leetcode 链接：
    //https://leetcode-cn.com/problems/fibonacci-number/solution/dong-tai-gui-hua-tao-lu-xiang-jie-by-labuladong/
    public long fib(int n){
        if(n<=2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
    @Test
    public void 斐波那契数列(){
        int n=50;
        //打印正常斐波那契数列的值
        long s=System.currentTimeMillis();
        System.out.println(fib(n));
        long e=System.currentTimeMillis();
        System.out.println("普通递归，耗时："+(e-s));//27758
        //动态规划,带备忘录的递归解法
        long[] dp=new long[n+1];
        s=System.currentTimeMillis();
        System.out.println(fibWithBWL(dp,n));
        e=System.currentTimeMillis();
        System.out.println("带备忘录的递归解法，耗时："+(e-s));//0
        //for循环
        s=System.currentTimeMillis();
        System.out.println(fibDPCompr(n));
        e=System.currentTimeMillis();
        System.out.println("for循环，耗时："+(e-s));//0

    }

    //斐波那契数列 带备忘录的递归解法:
    public long fibWithBWL(long [] dp,int n){
        if(n<=2){
            dp[n]=1;
        }
        if(dp[n]!=0){
            return dp[n];
        }
        dp[n]= fibWithBWL(dp,n-1)+fibWithBWL(dp,n-2);
        return dp[n];
    }

    //斐波那契数列 带备忘录的递归解法+状态压缩(其实就是for循环):
    public long fibDPCompr(int n){
        if(n<=2){
            return 1;
        }
        long pre=1;long curr=1;
        for(int i=2;i<n;i++){
            long tmp=curr;
            curr=curr+pre;
            pre=tmp;
        }
        return curr;
    }

    @Test
    public void 凑零钱问题(){
        //给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，
        //如果不可能凑出，算法返回 -1 。
        int[] mony={1,2,5};
        int m=40;

        long s=System.currentTimeMillis();
        System.out.println(coinChangeFor(mony,m));
        long e=System.currentTimeMillis();
        System.out.println("递归暴力破解，耗时："+(e-s));//5994

        int result[]=new int[m+1];
        s=System.currentTimeMillis();
        System.out.println(coinChangeWithBWL(result,mony,m));
        e=System.currentTimeMillis();
        System.out.println("带备忘录的递归，耗时："+(e-s));//1

        s=System.currentTimeMillis();
        System.out.println(coinChangeWithDP(mony,m));
        e=System.currentTimeMillis();
        System.out.println("DP，耗时："+(e-s));//0

    }
    public int coinChangeFor(int[] mony,int m){
        //for循环暴力破解
        if(m<=0){
            return -1;
        }
        if(m==1){
            return 1;
        }
        int tmp=Integer.MAX_VALUE;
        for(int i=0;i<mony.length;i++){
            int newm=m-mony[i];
            if(newm==0){
                tmp=1;
                break;
            }
            int res=coinChangeFor(mony,newm);
            if(res==-1){
                break;
            }
            tmp=Math.min(tmp,res+1);
            //System.out.println(m+":"+tmp);
        }
        return tmp==Integer.MAX_VALUE?-1:tmp;
    }
    public int coinChangeWithBWL(int result[],int[] mony,int m){
        //带备忘录的递归
        if(m<=0){
            return -1;
        }
        if(m==1){
            result[1]= 1;
        }
        int tmp=Integer.MAX_VALUE;
        if(result[m]==0){
            for(int i=0;i<mony.length;i++){
                int newm=m-mony[i];
                if(newm==0){
                    tmp=1;
                    break;
                }
                int res=coinChangeWithBWL(result,mony,newm);
                if(res==-1){
                    break;
                }
                tmp=Math.min(tmp,res+1);
            }
            result[m]=tmp==Integer.MAX_VALUE?-1:tmp;
            //System.out.println(m+"的方法数："+result[m]);
        }
        return result[m];
    }

    public int coinChangeWithDP(int[] mony,int m) {
        if(m<=0){
            return -1;
        }
        if(m==1){
           return 1;
        }
        int dp[]=new int[m+1];
        dp[1]=1;
        for(int i=2;i<=m;i++){
            int tmp=m+1;
            for(int j=0;j<mony.length;j++){
                int index=i-mony[j];
                if(index<0){
                    break;
                }
                tmp=Math.min(tmp,dp[index]+1);
            }
            dp[i]=tmp==m+1?-1:tmp;
            //System.out.println(i+":"+dp[i]);
        }
        return dp[m];
    }
}
