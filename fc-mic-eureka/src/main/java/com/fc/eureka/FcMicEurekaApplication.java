package com.fc.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-02 10:21
 **/
@EnableEurekaServer
@SpringBootApplication
public class FcMicEurekaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicEurekaApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicEurekaApplication.class, args);
        LOGGER.info("Started fcMicEurekaApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}
