package com.example.demo.controller;

import com.example.demo.bean.Log;
import com.example.demo.repository.LogRepository;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author XZQ
 * @Date 2021/11/12 18:28
 **/
@Controller
public class LogController {

    @Autowired
    LogRepository logRepository;

    @PersistenceContext
    EntityManager entityManager;

    @ResponseBody
    @GetMapping("/getlog/{logid}")
    public String getLog(@PathVariable("logid") int logid){
       return logRepository.getOne(logid).toString();
    }

    @ResponseBody
    @GetMapping("/getlogByPage/{pagenum}/{num}")
    public String getLogPage(@PathVariable("pagenum") int pagenum,@PathVariable("num") int num){
        Page<Log> logList= logRepository.findAll(PageRequest.of(pagenum, num, Sort.by("id").descending()));
        int totalpages=logList.getTotalPages();
        int pagenumber=logList.getNumber();
        System.out.println("totalpages:"+totalpages+",number:"+pagenumber);
        return Arrays.toString(new List[]{logList.getContent()});
    }

    @ResponseBody
    @GetMapping("/getlogByName/{name}")
    public String getLogByName(@PathVariable("name") String name){
        List<Log> logList= logRepository.findByNameLike(name+"%",PageRequest.of(1, 20, Sort.by("id").descending()));
        System.out.println("size:"+logList.size());
        return Arrays.toString(logList.toArray());
    }

    @ResponseBody
    @GetMapping("/getlogByQuery/{name}")
    public String getLogByQuery(@PathVariable("name") String name){
        List<Log> logList= logRepository.findByQuery(name,PageRequest.of(1, 20, Sort.by("id").descending()));
        System.out.println("size:"+logList.size());
        return Arrays.toString(logList.toArray());
    }

    @ResponseBody
    @GetMapping("/getlogBySQL/{name}")
    public String getLogBySQL(@PathVariable("name") String name){
        String sql="select logid,age,name from log where name like '"+name+"%' ";
        Query query=entityManager.createNativeQuery(sql);
        query.setMaxResults(20);
        query.setFirstResult(1);
        List<Object> listobj=query.getResultList();
        JSONArray jsonarray=new JSONArray();
        listobj.forEach(a->{
            Object[] a1=(Object[])a;
            Log l=new  Log();
            l.setId((Integer) a1[0]);
            l.setAge((Integer) a1[1]);
            l.setName((String) a1[2]);
            jsonarray.add(l);
        });
        return jsonarray.toJSONString();
    }
}
