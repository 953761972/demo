package com.example.demo.多线程与线程池.callback;

/**
 * @Author XZQ
 * @Date 2021/10/5 18:06
 **/
public class TestCallBack {
    public void compute(int n, ComputeCallBack callback) {
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        callback.onComputeEnd();
    }
}
