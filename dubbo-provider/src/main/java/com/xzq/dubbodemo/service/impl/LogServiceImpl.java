package com.xzq.dubbodemo.service.impl;

import com.xzq.dubbocommon.bean.Log;
import com.xzq.dubbocommon.service.LogService;
import com.xzq.dubbodemo.dao.LogMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author XZQ
 * @Date 2021/11/1 00:23
 **/
@Service
public class LogServiceImpl implements LogService {
    @Resource
    LogMapper logMapper;

    @Override
    @DubboService()
    public Log gerLogbyId(int id){
        return logMapper.selectByPrimaryKey(id);
        //return new Log();
    }
}
