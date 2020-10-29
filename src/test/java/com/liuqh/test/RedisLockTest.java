package com.liuqh.test;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Slf4j
public class RedisLockTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);

        RLock lock = redissonClient.getLock("test:lock");
        try {
            boolean isLock = lock.tryLock();
//            boolean isLock = lock.tryLock(5, 20, TimeUnit.SECONDS);
            if (isLock) {
                log.info("do something");
                Thread.sleep(100000);
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }
}