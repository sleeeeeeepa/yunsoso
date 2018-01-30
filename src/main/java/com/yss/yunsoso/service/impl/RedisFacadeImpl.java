package com.yss.yunsoso.service.impl;

import com.yss.yunsoso.service.RedisFacade;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

@Service
public class RedisFacadeImpl implements RedisFacade {

    @Resource
    private RedisTemplate redisTemplate;

    //前台请求若为为请求kw，则加入队列
    @Override
    public Boolean addReidisQueue(String key,String value) {

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.incrementScore(key, value,1.0);  //权重+1  如果没有该value则生成

        return true;
    }
}
