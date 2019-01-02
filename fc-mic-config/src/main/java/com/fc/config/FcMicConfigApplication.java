package com.fc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 * @author fucheng.zou
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class FcMicConfigApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicConfigApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicConfigApplication.class, args);
        LOGGER.info("Start fcMicConfigApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}
