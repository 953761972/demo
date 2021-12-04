package com.example.demo.定时任务.quarz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author XZQ
 * @Date 2021/11/23 22:16
 **/
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();

        JobDetail jobDetail= JobBuilder.newJob(PrintWordsJob.class).
                withIdentity("name","group").build();

        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("name","group").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(1))
                .withPriority(1000)
                .build();

        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("启动定时任务。。。");
        scheduler.start();

        JobDetail jobDetail1= JobBuilder.newJob(PrintWordsJob.class).
                withIdentity("name1","group").build();
        Trigger trigger1=TriggerBuilder.newTrigger().withIdentity("name1","group").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(2))
                .withPriority(1000)
                .build();

        scheduler.scheduleJob(jobDetail1,trigger1);
    }
}
