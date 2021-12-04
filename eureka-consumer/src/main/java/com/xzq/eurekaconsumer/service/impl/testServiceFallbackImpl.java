package com.xzq.eurekaconsumer.service.impl;

import com.xzq.eurekaconsumer.service.testService;
import org.springframework.stereotype.Service;

/**
 * @Author XZQ
 * @Date 2021/11/21 18:22
 **/
@Service
public class testServiceFallbackImpl implements testService {
    @Override
    public String get() {
        return "服务当前不可用";
    }

    @Override
    public String feginTest() {
        return "服务当前不可用";
    }

    @Override
    public String Hystrixtest() {
        return "服务当前不可用";
    }
}
