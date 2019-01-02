package com.fc.lock.service;

public interface DistributedLockService {

    /**
     * 设置默认超时时间
     */
    public static final int LOCK_EXPIRE_TIME = 10 * 1000;

    /**
     * 获取锁
     * @param key 值为默认超时时间
     * @return
     */
    public boolean acquire(String key) throws Exception;

    /**
     * 获取锁
     * @param key
     * @param expire 设置超时时间（并作为value进行存储，便于后续超时比较）
     * @return
     * @throws Exception
     */
    public boolean acquire(String key, long expire) throws Exception;

    /**
     * 释放锁
     * @param key 值为默认超时时间
     */
    public void release(String key) throws Exception;

    /**
     * 释放锁
     * @param key
     * @param expire 获取锁时设置的超时时间
     * @throws Exception
     */
    public void release(String key, long expire) throws Exception;
}
