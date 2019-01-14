package com.fc.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableDubbo
@EnableEurekaClient
@SpringBootApplication
public class FcMicConsumerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicConsumerApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicConsumerApplication.class, args);
        LOGGER.info("Start fcMicConsumerApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}
