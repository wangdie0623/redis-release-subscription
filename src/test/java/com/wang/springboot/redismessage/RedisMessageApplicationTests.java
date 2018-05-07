package com.wang.springboot.redismessage;

import com.wang.springboot.redismessage.model.entity.User;
import com.wang.springboot.redismessage.model.service.IRedisService;
import com.wang.springboot.redismessage.demo.*;
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
    @Resource
    Subject subject;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("demo");
        redisService.releaseMsg("user-channel", user);//在主题中发布新内容
        redisService.releaseMsg("user-channel", user.toString());//在主题中发布新内容
    }

    @Test
    public void test() {
        Observer o1 = new ObserverImpl();
        Observer o2 = new ObserverImpl2();
        ZX zx = ZX.getInstance();
        zx.add(subject, o1);
        zx.add(subject, o2);
        subject.publish("ff");
    }
}
