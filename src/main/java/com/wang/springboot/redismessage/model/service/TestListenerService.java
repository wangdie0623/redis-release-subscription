package com.wang.springboot.redismessage.model.service;

import com.alibaba.fastjson.JSON;
import com.wang.springboot.redismessage.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Component
@Slf4j
public class TestListenerService implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        User user = deserializeObj(message.getBody(), User.class);
        if (user == null) user = JSON.parseObject(message.toString(), User.class);
        log.info("最终结果:{}", user);
    }

    /**
     * 反序列化
     *
     * @param byteArr 待序列化值
     * @param clazz   class对象
     * @param <T>     序列化后类型
     * @return
     */
    public static <T> T deserializeObj(byte[] byteArr, Class<T> clazz) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArr))) {
            Object obj = ois.readObject();
            return clazz.cast(obj);
        } catch (Exception e) {
            log.error("反序列化异常", e);
        }
        return null;
    }
}
