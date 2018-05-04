package com.wang.springboot.redismessage;

import com.alibaba.fastjson.JSON;
import com.wang.springboot.redismessage.model.entity.User;
import com.wang.springboot.redismessage.model.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMessageApplicationTests {

    @Resource
    IRedisService redisService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("xx");
        redisService.releaseMsg("user-channel", user);
        redisService.releaseMsg("user-channel", user.toString());
    }

}
