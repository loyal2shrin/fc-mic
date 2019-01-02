package com.fc.lock.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-16 13:59
 **/
public class ZkDistributedLock implements Lock, Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkDistributedLock.class);

    /**
     * 根节点
     */
    private String ROOT_LOCK = "/locks";

    @Autowired
    private ZooKeeper zooKeeper = null;

    /**
     * 竞争的资源
     */
    private String lockName;

    /**
     * 等待的前一个锁
     */
    private String WAIT_LOCK;

    /**
     * 当前锁
     */
    private String CURRENT_LOCK;

    /**
     * 计数器
     */
    private CountDownLatch countDownLatch;

    private int sessionTimeout = 30000;

    private List<Exception> exceptionList = new ArrayList<Exception>();

    public ZkDistributedLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            /**
             * 连接zookeeper
             */
            zooKeeper = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (stat == null) {
                /**
                 * 如果根节点不存在，则创建根节点
                 */
                zooKeeper.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    @Override
    public void lock() {
        if (exceptionList.size() > 0) {
            throw new LockException(exceptionList.get(0));
        }
        try {
            if (this.tryLock()) {
                LOGGER.info(Thread.currentThread().getName() + " " + lockName + "获得了锁");
                return;
            } else {
                /**
                 * 等待锁
                 */
                waitForLock(WAIT_LOCK, sessionTimeout);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean tryLock() {
        /**
         * 创建临时有序节点
         */
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)) {
                throw new LockException("锁名有误");
            }
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids
                    .OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.info(CURRENT_LOCK + " 已经创建。");
            /**
             * 获取所有的子节点
             */
            List<String> subNodes = zooKeeper.getChildren(ROOT_LOCK, false);

            /**
             * 取出所有lockName的锁
             */
            List<String> lockObjects = new ArrayList<String>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if (_node.equals(lockName)) {
                    lockObjects.add(node);
                }
            }
            Collections.sort(lockObjects);
            LOGGER.info(Thread.currentThread().getName() + " 的锁是 " + CURRENT_LOCK);
            /**
             * 若当前节点为最小节点，则获取锁成功
             */
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
                return true;
            }
            String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, prevNode) - 1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long timeOut, TimeUnit unit) throws InterruptedException {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(WAIT_LOCK, timeOut);
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock() {
        try {
            LOGGER.info("释放锁 " + CURRENT_LOCK);
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK + "/" + prev, true);
        if (stat != null) {
            LOGGER.info(Thread.currentThread().getName() + "等待锁 " + ROOT_LOCK + "/" + prev);
            this.countDownLatch = new CountDownLatch(1);
            /**
             * 计数等待，若等到前一个节点消失，则precess中进行countDown，停止等待，获取锁
             */
            this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
            this.countDownLatch = null;
            LOGGER.info(Thread.currentThread().getName() + " 等到了锁");
        }
        return true;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    public class LockException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public LockException(String e) {
            super(e);
        }

        public LockException(Exception e) {
            super(e);
        }
    }
}
