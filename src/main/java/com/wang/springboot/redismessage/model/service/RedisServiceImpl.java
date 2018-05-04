package com.wang.springboot.redismessage.model.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 订阅者
 */
@Service
@Slf4j
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void releaseMsg(String title, Object msg) {
        log.info("{}主题，发布消息:{}",title, JSON.toJSONString(msg));
        if (msg instanceof  String){
            stringRedisTemplate.convertAndSend(title,msg);
            return;
        }
        redisTemplate.convertAndSend(title,msg);
    }
}
