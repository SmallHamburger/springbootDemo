package com.jeson.springdemo.exception;

import com.jeson.springdemo.domain.HttpResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult handleException(Exception e) {
        return HttpResult.create(HttpResult.Type.ERROR, "未知错误", null);
    }


}
