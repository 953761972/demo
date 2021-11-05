package com.example.demo.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

import java.util.concurrent.CountDownLatch;

/**
 * @Author XZQ
 * @Date 2021/11/1 23:40
 **/
@Configuration
public class ZKConfig {
    private static final Logger logger = LoggerFactory.getLogger(ZKConfig.class);

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int sessionTimeout;


    @Bean(name = "zkClient")
    public ZooKeeper getZookeeper() {
            ZooKeeper zooKeeper = null;
            try {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        // 如果收到了服务端的响应事件，说明连接成功
                        if (Event.KeeperState.SyncConnected == event.getState()) {
                            countDownLatch.countDown();
                        }
                    }
                });
                countDownLatch.await();
                logger.info("  初始化ZooKeeper连接状态: {}", zooKeeper.getState());
            } catch (Exception e) {
                logger.error(" 初始化Zookeeper连接状态异常: {}", e.getMessage());
            }
            return zooKeeper;
        }
    @Bean
    public CuratorFrameworkFactoryBean curatorFrameworkFactoryBean() {
        return new CuratorFrameworkFactoryBean(connectString);
    }

    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework curatorFramework) {

        return new ZookeeperLockRegistry(curatorFramework, "/HG-lock");
    }

}