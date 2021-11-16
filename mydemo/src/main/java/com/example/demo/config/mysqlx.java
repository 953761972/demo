package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Log;
import com.mysql.cj.xdevapi.Schema;
import org.springframework.context.annotation.Configuration;

/**
 * @Author XZQ
 * @Date 2021/10/18 22:37
 **/
@Configuration
public class mysqlx {
    //@Resource
    private Schema schema;

    public Log save(Log formLog) {
            schema
                    .createCollection("logs", true)
                    .add(JSON.toJSONString(formLog))
                    .executeAsync();
        return formLog;
    }
}
