package com.fc.job.config;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.fc.job.job.StockSimpleJob;
import com.fc.job.listener.ElasticJobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-07 17:15
 **/

@Configuration
@Component
public class StockJobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    /*@Value("${stockJob.cron}")
    private String cron;

    @Value("${stockJob.shardingTotalCount}")
    private int shardingTotalCount;

    @Value("${stockJob.shardingItemParameters}")
    private String shardingItemParameters;*/

    @Autowired
    private ElasticJobListener elasticJobListener;

    public StockJobConfig() {
    }

    @Bean
    public SimpleJob stockJob() {
        return new StockSimpleJob();
    }


    public void simpleJobScheduler(String jobName, String cron, int shardingTotalCount, String shardingItemParameters) throws ClassNotFoundException {
        LiteJobConfiguration liteJobConfiguration = getLiteJobConfiguration(jobName,
                StockSimpleJob.class,
                cron,
                shardingTotalCount,
                shardingItemParameters);

        new SpringJobScheduler(new StockSimpleJob(), regCenter, liteJobConfiguration,elasticJobListener).init();
    }

    /**
     * @Description 任务配置类
     */
    private LiteJobConfiguration getLiteJobConfiguration(final String jobName,
                                                         final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {


        return LiteJobConfiguration
                .newBuilder(
                        new SimpleJobConfiguration(
                                JobCoreConfiguration.newBuilder(
                                        jobName, cron, shardingTotalCount)
                                        .shardingItemParameters(shardingItemParameters)
                                        .build()
                                , jobClass.getCanonicalName()
                        )
                )
                .overwrite(true)
                .build();

    }

}
