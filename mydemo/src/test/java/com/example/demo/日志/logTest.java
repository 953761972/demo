package com.example.demo.日志;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author XZQ
 * @Date 2021/11/28 23:58
 **/
public class logTest {
    private  static final Logger logger = LoggerFactory.getLogger(logTest.class);

    public static void main(String[] args) {
        logger.info(String.valueOf(logger.isDebugEnabled()));
        logger.debug("ssss");
        logger.info("ssss");
        logger.warn("ssss");
        logger.error("ssss");
        logger.debug("helllo :{},{}","1","2");
    }
}
