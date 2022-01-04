package com.example.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/5/16 14:45
 **/
public class test {
    public static void main(String[] args) throws IOException {
            test2();
    }
    public void test1() throws IOException {
        Runtime r=Runtime.getRuntime();

        System.out.println(r.totalMemory()/1024/1024);
        System.out.println(r.maxMemory()/1024/1024);
        System.out.println(r.freeMemory() /1024/1024);

        System.out.println(test.class.getResource("/").getPath());
        InputStream in =r.exec("javac /Volumes/FastSSD/GitHubDesktop/demo/src/test/java/com/example/demo/test.java").getErrorStream();
        BufferedInputStream bufIn = new BufferedInputStream(in);
        String errval="";
        byte[] shuzu = new byte[100];
        int n=0;
        while ((n = bufIn.read(shuzu, 0, shuzu.length)) != -1) {
            String s = null;
            s = new String(shuzu, 0, n);
            errval+=s;
        }
        System.out.println(errval);

        in =r.exec("java /Volumes/FastSSD/GitHubDesktop/demo/src/test/java/com/example/demo/test").getInputStream();
        errval="";
        n=0;
        while ((n = bufIn.read(shuzu, 0, shuzu.length)) != -1) {
            String s = null;
            s = new String(shuzu, 0, n);
            errval+=s;
        }
        System.out.println(errval);
    }

    public  static void test2(){
        List<String> list =null;
        if(list !=null && list.size()>0){
            list.forEach(a->{
                System.out.println(a);
            });
        }
for(String s:list){
    System.out.println(s);
}
    }
}
