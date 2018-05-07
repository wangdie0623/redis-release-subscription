package com.wang.springboot.redismessage.demo;

public class ObserverImpl2 implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("想写" + msg);
    }
}
