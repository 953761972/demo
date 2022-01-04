package com.example.activiti.controller;

import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author XZQ
 * @Date 2021/12/5 17:45
 **/
@Controller
public class testController {
    @Autowired
    private TaskRuntime taskRuntime;

    @RequestMapping("/getall")
    @ResponseBody
    public String getall(){
        taskRuntime.create(
                TaskPayloadBuilder.create()
                        .withName("First Team Task")
                        .withDescription("This is something really important")
                        //.withGroup("activitiTeam")
                        .withPriority(10)
                        .build());
        System.out.println("ok");
        return "ok";
    }
}
