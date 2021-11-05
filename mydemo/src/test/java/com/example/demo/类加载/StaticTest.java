package com.example.demo.类加载;

/**
 * @Author XZQ
 * @Date 2021/11/1 21:20
 **/
public class StaticTest {
    public static int k = 0;
    public static StaticTest t1 = new StaticTest("t1");
    public static StaticTest t2 = new StaticTest("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");//1

    {
        print("构造块");
    }

    static{
        print("静态块");
    }

    public StaticTest(String str) {
        System.out.println(str+"："+"构造方法：:"  + " i=" + i + " n=" + n);
        ++n;
        ++i;
    }

    public static int print(String str) {
        System.out.println(str+"："+"静态方法：:"  + " i=" + i + " n=" + n);
        ++i;
        return ++n;
    }
    public static void main(String[] args) {
        StaticTest t = new StaticTest("init");
    }
}
