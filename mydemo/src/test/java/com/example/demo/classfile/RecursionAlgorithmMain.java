package com.example.demo.classfile;

import java.io.IOException;

/**
 * @Author XZQ
 * @Date 2021/10/2 00:29
 **/
public class RecursionAlgorithmMain {
    private static volatile int value = 0;

    static int sigma(int n) {
        value = n;
        System.out.println("current 'n' value is " + n);
        return n + sigma(n + 1);
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> sigma(1)).start();
        System.in.read();
        System.out.println(value);
    }
}
