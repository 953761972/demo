package com.example.demo.多线程与线程池.callback;

/**
 * @Author XZQ
 * @Date 2021/10/5 18:09
 **/
public class Main {
    public static void main(String[] args) {
        new TestCallBack().compute(1000, new ComputeCallBack() {

            @Override
            public void onComputeEnd() {
                System.out.println("end back!!!");
            }
        });
        System.out.println("end main !!!");
    }
}
