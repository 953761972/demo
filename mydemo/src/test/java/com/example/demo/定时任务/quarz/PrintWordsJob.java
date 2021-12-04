package com.example.demo.定时任务.quarz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * @Author XZQ
 * @Date 2021/11/23 22:14
 **/
public class PrintWordsJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobExecutionContext.get("aa");

        JobKey key=jobExecutionContext.getJobDetail().getKey();
        System.out.println("运行中："+key.getGroup()+"."+key.getName());

    }
}
