package com.fc.job;

import com.fc.job.config.StockJobConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 */
@SpringBootApplication
public class FcMicJobApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcMicJobApplication.class);

    public static void main(String[] args) throws ClassNotFoundException {
        long startTime = System.currentTimeMillis();
        ApplicationContext cxt = SpringApplication.run(FcMicJobApplication.class, args);
        LOGGER.info("Started fcMicJobApplication success, cost time is {}.", System.currentTimeMillis() - startTime);

        StockJobConfig stockJobConfig = (StockJobConfig) cxt.getBean("stockJobConfig");
        stockJobConfig.simpleJobScheduler("test","0/5 * * * * ?", 2, "0=shenzheng,1=guangzhou");
    }
}
