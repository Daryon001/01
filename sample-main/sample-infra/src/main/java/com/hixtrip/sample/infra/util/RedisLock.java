package com.hixtrip.sample.infra.util;


import org.springframework.stereotype.Component;

@Component
public class RedisLock {

    public boolean lock(String lockKey, String lockVal, long expireTimeSeconds) {
        //todo 模拟缓存锁
        return true;
    }

    public boolean unlock(String lockKey) {
        //todo 模拟缓存锁
        return true;
    }

}
