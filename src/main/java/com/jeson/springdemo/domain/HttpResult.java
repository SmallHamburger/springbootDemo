package com.jeson.springdemo.domain;

public class HttpResult<T> {


    private int code = Code.SUCCESS.code;
    private String message = Code.SUCCESS.message;
    private long timeStamp = System.currentTimeMillis();
    private T data;

    public HttpResult() {
    }

    public HttpResult(T data) {
        this.data = data;
    }

    public HttpResult(Code code) {
        this.code = code.code;
        this.message = code.message;
    }

    public HttpResult(Code code, T data) {
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public HttpResult(int code, String message) {
        this(code, message, null);
    }

    public HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return toString(0, "");
    }

    public String toString(int indentCount, String firstString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentCount; i++) {
            sb.append("\t");
        }
        String indent = sb.toString();
        return "\n" + firstString + indent + "HttpResult:" +
                "\n" + firstString + "\t" + indent + "Code: \t\t" + code +
                "\n" + firstString + "\t" + indent + "Message: \t" + message +
                "\n" + firstString + "\t" + indent + "TimeStamp: \t" + timeStamp +
                "\n" + firstString + "\t" + indent + "Data: \t\t" + data;
    }

    public enum Code {
        SUCCESS(0, "success"),
        UNKNOWN(1, "unknown");

        private int code;
        private String message;

        Code(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }


}
