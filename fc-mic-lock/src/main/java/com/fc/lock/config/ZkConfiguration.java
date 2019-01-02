package com.fc.lock.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-16 10:35
 **/

@Component
public class ZkConfiguration implements Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkConfiguration.class);

    @Autowired
    private ZkProperties zkProperties;

    /**
     * 计数器
     */
    private CountDownLatch countDownLatch;

    @Bean
    public ZooKeeper initZookeeper() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(zkProperties.getServer(), zkProperties.getSessionTimeoutMs(), this);
            countDownLatch.await();
            LOGGER.info("Connected to zookeeper");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Create zookeeper client fail, the exception is {}.", e);
        } finally {
            return zk;
        }
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }
}
