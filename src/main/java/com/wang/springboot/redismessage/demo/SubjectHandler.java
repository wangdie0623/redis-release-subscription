package com.wang.springboot.redismessage.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SubjectHandler {
    private ZX zx = ZX.getInstance();

    @Around("this(com.wang.springboot.redismessage.demo.Subject)")
    public Object invoked(ProceedingJoinPoint pjp) throws Throwable {
        Object aThis = pjp.getThis();
        Object[] args = pjp.getArgs();
        if (args != null) {
            zx.tz(aThis, args[0].toString());
        }
        return pjp.proceed();
    }
}
