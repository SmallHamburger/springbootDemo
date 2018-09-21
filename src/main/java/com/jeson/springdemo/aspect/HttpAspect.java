package com.jeson.springdemo.aspect;

import com.jeson.springdemo.domain.HttpResult;
import com.jeson.springdemo.util.CollectionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class HttpAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    private static final ThreadLocal<Long> BEFORE_HANDLE_TIMESTAMP = new ThreadLocal<>();

    @Pointcut("execution(public * com.jeson.springdemo.controller.*.*(..))")
    private void httpRequestLog() {
        LOGGER.info("Generate httpRequestLog() pointcut");
    }

    @Before("httpRequestLog()")
    private void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = requestAttributes.getRequest();
        String msg = "" +
                "\n┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐" +
                "\n│\tRequest Start:" +
                "\n│\t\tHttp:" +
                "\n│\t\t\tIP:\t\t\t" + request.getRemoteAddr() +
                "\n│\t\t\tUrl:\t\t" + request.getRequestURL() +
                "\n│\t\t\tMethod:\t\t" + request.getMethod() +
                "\n│\t\t\tParameters:\t" + CollectionUtil.toString(request.getParameterMap()) +
                "\n│\t\tClass:" +
                "\n│\t\t\tMethod:\t\t" + signature.getDeclaringTypeName() + "." + signature.getName() +
                "\n│\t\t\tArguments:\t" + (args == null ? "" : Arrays.toString(args)) +
                "\n└──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘";
        LOGGER.info(msg);
        BEFORE_HANDLE_TIMESTAMP.set(System.currentTimeMillis());
    }

    @After("httpRequestLog()")
    private void after() {
        Long lastHandleTimestamp = BEFORE_HANDLE_TIMESTAMP.get();
        if (lastHandleTimestamp == null) {
            return;
        }
        String msg = "" +
                "\n┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐" +
                "\n│\tRequest End:" +
                "\n│\t\tTime:" +
                "\n│\t\t\tUsed:\t\t" + (System.currentTimeMillis() - lastHandleTimestamp) + "ms" +
                "\n└──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘";
        LOGGER.info(msg);
    }

    @AfterReturning(pointcut = "httpRequestLog()", returning = "obj")
    private void returning(Object obj) {
        String msg = "" +
                "\n┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐" +
                "\n│\tRequest Return:" +
                "\n│\t\tReturn:" +
                "\n│\t\t\tObject:\t\t" + ((obj instanceof HttpResult) ? ((HttpResult) obj).toString(5, "│") : obj) +
                "\n└──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘";
        LOGGER.info(msg);
    }

}
