package com.wang.springboot.redismessage.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZX {
    private static final ZX z = new ZX();
    private Map<Object, List<Observer>> context = new HashMap<>();

    private ZX() {
    }

    public static ZX getInstance() {
        return z;
    }

    public void add(Object key, Observer value) {
        List<Observer> observers = context.get(key);
        if (observers == null) {
            observers = new ArrayList<>();
            observers.add(value);
            context.put(key, observers);
            return;
        }
        observers.add(value);
    }

    public void tz(Object subject, String msg) {
        List<Observer> observers = context.get(subject);
        if (observers == null || observers.isEmpty()) {
            return;
        }
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}
