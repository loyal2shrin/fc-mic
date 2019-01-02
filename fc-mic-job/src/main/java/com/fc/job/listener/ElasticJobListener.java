package com.fc.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-08 14:09
 **/
@Component
public class ElasticJobListener extends AbstractDistributeOnceElasticJobListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobListener.class);

    public ElasticJobListener() {
        super(2000, 2000);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        LOGGER.info("===========Start the job=============={}", System.currentTimeMillis());
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        LOGGER.info("===========End the job=============={}", System.currentTimeMillis());
    }
}
