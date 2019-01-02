package com.fc.lock.config;

import org.springframework.context.annotation.Configuration;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-16 10:54
 **/
@Configuration(value = "zookeeper")
public class ZkProperties {

    private String server;

    private int sessionTimeoutMs;

    private long connectionTimeoutMs;

    private int maxRetries;

    private long baseSleepTimeMs;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public long getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(long connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public long getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(long baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }
}
