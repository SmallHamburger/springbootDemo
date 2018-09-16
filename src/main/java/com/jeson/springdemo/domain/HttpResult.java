package com.jeson.springdemo.domain;

import org.springframework.util.StringUtils;

public class HttpResult<T> {

    private Integer code;
    private String  message;
    private Long timestamp = System.currentTimeMillis();
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpResult{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    public static <T> HttpResult<T> create(Type type, String msg, T data) {
        HttpResult<T> result = new HttpResult<>();
        result.setCode(type.code);
        result.setMessage(type.message + (StringUtils.isEmpty(msg) ? "" : ", " + msg));
        result.setData(data);
        return result;
    }

    public enum Type {
        SUCCESS(0, "成功"),
        ERROR(1, "失败");

        private Integer code;
        private String  message;

        Type(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
