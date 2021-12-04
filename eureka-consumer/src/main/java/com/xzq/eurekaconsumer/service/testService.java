package com.xzq.eurekaconsumer.service;

import com.xzq.eurekaconsumer.service.impl.testServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author XZQ
 * @Date 2021/11/21 17:17
 **/
@FeignClient(name="testeureakprovider",fallback = testServiceFallbackImpl.class)
public interface testService {

    @GetMapping(value = "/getInfo")
    public String get();

    @GetMapping(value = "/feginTest")
    public String feginTest();

    @GetMapping(value = "/Hystrixtest")
    public String Hystrixtest();
}
