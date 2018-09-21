package com.jeson.springdemo.exception;

import com.jeson.springdemo.domain.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public HttpResult<String> handleException(Exception e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.error("\n\n" + e.getMessage(), e);
            return new HttpResult<>(HttpResult.Code.UNKNOWN.code, e.getMessage());
        }
        return new HttpResult<>(HttpResult.Code.UNKNOWN);
    }

}
