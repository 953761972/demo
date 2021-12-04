package com.example.jobs.controller;

import com.example.jobs.jobs.MyJob;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author XZQ
 * @Date 2021/11/28 02:33
 **/
@Controller
public class jobController {

    @Autowired
    SchedulerFactoryBean schedulerFactory;

    @RequestMapping("/getScheduler/{Scheduler}")
    @ResponseBody
    public String getinfo(@PathVariable String Scheduler) throws SchedulerException {
        return schedulerFactory.getScheduler().getSchedulerName();
    }

    @RequestMapping("/addJob/{jobname}")
    @ResponseBody
    public Date addJob(@PathVariable String jobname) throws SchedulerException {
        //自动保存到数据库
        Scheduler s=schedulerFactory.getScheduler();
        JobDetail jobDetail= JobBuilder.newJob(MyJob.class).withIdentity(jobname,jobname).
                usingJobData("key1","value1").build();
        ScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.repeatHourlyForever().withIntervalInSeconds(2);
        Trigger t=TriggerBuilder.newTrigger().startNow().withSchedule(scheduleBuilder).build();
        return s.scheduleJob(jobDetail,t);
    }
    @RequestMapping("/deleteJob/{jobname}")
    @ResponseBody
    public boolean deleteJob(@PathVariable String jobname) throws SchedulerException {
        //自动保存到数据库
        Scheduler s=schedulerFactory.getScheduler();
        JobKey key=new JobKey(jobname,jobname);
        return s.deleteJob(key);
    }
}
