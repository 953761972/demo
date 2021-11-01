package com.xzq.dubbocommon.service;

import com.xzq.dubbocommon.bean.Log;

/**
 * @Author XZQ
 * @Date 2021/11/1 11:59
 **/
@javax.annotation.Generated(
        value = "by Dubbo generator",
        comments = "Source: DemoService.proto")
public interface LogService {
    static final String JAVA_SERVICE_NAME = "com.xzq.dubbocommon.service.LogService";
    static final String SERVICE_NAME = "dubbocommon.LogService";

    public Log gerLogbyId(int id);
}
