package com.fc.lock.zk;

import com.fc.lock.service.DistributedLockService;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-16 13:59
 **/
public class ZkDistributedLock implements DistributedLockService , InitializingBean {

    /**
     * 根节点
     */
    private String ROOT_LOCK = "/locks";

    @Autowired
    private ZooKeeper zooKeeper;


    /**
     * 获取锁
     *
     * @param key 值为默认超时时间
     * @return
     */
    @Override
    public boolean acquire(String key) throws Exception {
        return false;
    }

    /**
     * 获取锁
     *
     * @param key
     * @param expire 设置超时时间（并作为value进行存储，便于后续超时比较）
     * @return
     * @throws Exception
     */
    @Override
    public boolean acquire(String key, long expire) throws Exception {
        return false;
    }

    /**
     * 释放锁
     *
     * @param key 值为默认超时时间
     */
    @Override
    public void release(String key) throws Exception {

    }

    /**
     * 释放锁
     *
     * @param key
     * @param expire 获取锁时设置的超时时间
     * @throws Exception
     */
    @Override
    public void release(String key, long expire) throws Exception {

    }

    private void createRootLock() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK, false);
        if (stat == null) {
            // 如果根节点不存在，则创建根节点
            zooKeeper.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    // 等待锁
    private boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK + "/" + prev, true);

        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + "等待锁 " + ROOT_LOCK + "/" + prev);
            /*this.countDownLatch = new CountDownLatch(1);
            // 计数等待，若等到前一个节点消失，则precess中进行countDown，停止等待，获取锁
            this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
            this.countDownLatch = null;*/
            System.out.println(Thread.currentThread().getName() + " 等到了锁");
        }
        return true;
    }


    public void unlock(String loclKey) {
        try {
            System.out.println("释放锁 " + loclKey);
            zooKeeper.delete(loclKey, -1);
            loclKey = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createRootLock();
    }
}
