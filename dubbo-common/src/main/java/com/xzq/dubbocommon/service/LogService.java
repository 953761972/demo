package com.xzq.dubbodemo.service;

import com.xzq.dubbodemo.bean.Log;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Author XZQ
 * @Date 2021/11/1 11:59
 **/
@Service
@DubboService
public interface LogService {
    public Log gerLogbyId(int id);
}
