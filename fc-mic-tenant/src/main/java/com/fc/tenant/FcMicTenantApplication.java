package com.fc.tenant;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 * @author fucheng.zou
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.fc.tenant.service.mapper")
public class FcMicTenantApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicTenantApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicTenantApplication.class, args);
        LOGGER.info("Start fcMicTenantApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}
