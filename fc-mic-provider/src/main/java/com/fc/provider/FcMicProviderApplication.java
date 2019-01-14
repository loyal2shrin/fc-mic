package com.fc.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 * @author fucheng.zou
 */
@EnableDubbo
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.fc.provider.service.mapper")
public class FcMicProviderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicProviderApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(FcMicProviderApplication.class, args);
        LOGGER.info("Start fcMicProviderApplication success, cost time is {}.", System.currentTimeMillis() - startTime);
    }
}