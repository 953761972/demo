package com.xzq.eurekaprovider.service;

import com.xzq.eurekaprovider.service.impl.testServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author XZQ
 * @Date 2021/11/21 14:53
 **/
public interface testService {

    public String feginTest();

    public String Hystrixtest() throws InterruptedException;
}
