package com.wang.springboot.redismessage.model.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    final static long serialVersionUID = -649794470754667710L;
    private String name;

    @Override//重写toString方法 等同于Message toString方法
    public String toString() {
        return JSON.toJSONString(this);
    }
}
