package com.wang.springboot.redismessage.demo;

import org.springframework.stereotype.Component;

@Component
public class SubjectImpl implements Subject {
    private String msg;

    @Override
    public void publish(String msg) {
        this.msg = msg;
    }
}
