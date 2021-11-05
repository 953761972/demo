package com.example.demo.Zookeeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author XZQ
 * @Date 2021/11/2 19:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZKLock {
    @Resource
    private LockRegistry lockRegistry;

    @Test
    public void testlock(){
        System.out.println("lock10 start " + System.currentTimeMillis());
        final Lock lock = lockRegistry.obtain("lock");
        try {
            lock.lock();
            System.out.println("lock10 get lock success " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}
