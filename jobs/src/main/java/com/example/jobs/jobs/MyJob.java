package com.example.jobs.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author XZQ
 * @Date 2021/11/28 12:12
 **/
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data=context.getJobDetail().getJobDataMap();
        String value= data.getString("key1").toString();
        System.out.println("value:"+value);
    }
}
