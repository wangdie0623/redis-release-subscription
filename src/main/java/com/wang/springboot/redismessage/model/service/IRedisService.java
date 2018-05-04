package com.wang.springboot.redismessage.model.service;

public interface IRedisService {
    /**
     * 发布新消息到对应主题
     * @param title 主题名
     * @param msg 新消息
     */
    void releaseMsg(String title,Object msg);
}
