package com.wang.springboot.redismessage.demo;

public class ObserverImpl implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("不想写"+msg);
    }
}
