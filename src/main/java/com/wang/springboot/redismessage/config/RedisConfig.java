package com.wang.springboot.redismessage.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wang.springboot.redismessage.model.service.TestListenerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisConfig {




    /**
     * 主题
     *
     * @return
     */
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("user-channel");
    }



    /**
     * 调度中心
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory, TestListenerService listener) {
        //调度中心任务线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("redis-container-pool-%d").build();
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(5, 5, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), threadFactory);
        //调度中心
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //调度中心依赖连接工厂
        container.setConnectionFactory(redisConnectionFactory);
        //添加主题/订阅组合
        container.addMessageListener(listener, topic());
        //指定调度中心线程池
        container.setTaskExecutor(poolExecutor);
        return container;
    }


}  