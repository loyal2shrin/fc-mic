package com.fc.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-16 10:21
 **/

@SpringBootApplication
public class FcMicLockApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicLockApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicLockApplication.class, args);
        LOGGER.info("Start fcMicLockApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }

}
