package com.jeson.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    private ThreadLocal<Long> beforeHandleTimestamp = new ThreadLocal<>();

    @Pointcut("execution(public * com.jeson.springdemo.controller.*.*(..))")
    private void log() {
        LOGGER.info("generate point cut");
    }

    @Before("log()")
    private void before(JoinPoint joinPoint) {
        ServletRequestAttributes attr    = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest       request = attr.getRequest();
        LOGGER.info("request url: " + request.getRequestURL());
        LOGGER.info("request method: " + request.getMethod());
        LOGGER.info("request address: " + request.getRemoteAddr());
        LOGGER.info("class method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("class args: {}", joinPoint.getArgs());
        beforeHandleTimestamp.set(System.currentTimeMillis());
    }

    @After("log()")
    private void after() {
        Long beforeHandleTimestampTime = beforeHandleTimestamp.get();
        if (beforeHandleTimestampTime == null) {
            return;
        }
        LOGGER.info("Handle time: {}", System.currentTimeMillis() - beforeHandleTimestampTime);
    }

    @AfterReturning(returning = "obj", pointcut = "log()")
    private void afterReturning(Object obj){
        LOGGER.info("return obj: {}", obj);
    }


}
