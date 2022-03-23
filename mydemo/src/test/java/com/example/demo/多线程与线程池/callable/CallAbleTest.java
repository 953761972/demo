package com.example.demo.多线程与线程池.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @className: com.example.demo.多线程与线程池.callable-> CallAbleTest
 * @description:
 * @author: xiongzq
 * @createDate: 2022-03-21 10:05
 * @version: 1.0
 * @todo:
 */
public class CallAbleTest {
    public static void main(String[] args) {
        //FutureTaskForMultiCompute inst = new FutureTaskForMultiCompute();
        // 建立任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
        // 建立线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象建立FutureTask对象
            FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i));
            taskList.add(ft);
            // 提交给线程池执行任务，也能够经过exec.invokeAll(taskList)一次性提交全部任务;
            exec.submit(ft);
        }

        System.out.println("全部计算任务提交完毕, 主线程接着干其余事情！");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("多任务计算后的总结果是:" + totalResult);
    }
}

class ComputeTask implements Callable<Integer> {
    private int a=0;

    public ComputeTask(int a) {
        this.a = a;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return a*a;
    }
}