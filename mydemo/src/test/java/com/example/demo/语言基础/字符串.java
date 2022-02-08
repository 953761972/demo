package com.example.demo.语言基础;

public class 字符串 {
    public static void main(String[] args) {
        String s=new String("a")+new String("b");
        //System.out.println(s=="ab");
        String s2=s.intern();//如果常量池已经有了，则不会放入。
        System.out.println(s2=="ab");
        System.out.println(s=="ab");
        System.out.println(s==s2);
        //
        String b=new String("c")+new String("d");
        b.intern();
        String c="cd";
        System.out.println(b==c);

    }
}
