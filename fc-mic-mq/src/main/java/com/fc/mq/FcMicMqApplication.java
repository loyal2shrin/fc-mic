package com.fc.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 * @author fucheng.zou
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FcMicMqApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicMqApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicMqApplication.class, args);
        LOGGER.info("Start fcMicMqApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}
