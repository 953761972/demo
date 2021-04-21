package com.example.demo.语言基础;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

/**
 * @Author XZQ
 * @Date 2021/4/19 15:21
 **/
public class 测试 {
    public static void main(String[] args) throws IOException {
        ArrayList list = new ArrayList(20);
        //new BufferedWriter(new FileWriter("a.txt"));
        //new BufferedReader(new FileInputStream("a.dat"));
       // new GZIPOutputStream(new FileOutputStream("a.zip"));
       // new ObjectInputStream(new FileInputStream("a.dat"));

        String[] s=new String[10];
        System.out.println(s[9]);
        //System.out.println(s[10]);
        System.out.println(s[0]);
        System.out.println(s.length);
    }
    public void modify() {
        int I, j, k;
        I = 100;
         while ( I > 0 ) {
             j = I * 2;
             System.out.println ("The value of j is "+ j );
            //k = k + 1;
             I--;
             }
         }
}
