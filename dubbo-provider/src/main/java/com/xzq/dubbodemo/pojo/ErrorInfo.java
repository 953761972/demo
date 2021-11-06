package com.xzq.dubbodemo.pojo;

/**
 * @Author XZQ
 * @Date 2021/11/6 17:45
 **/
public class ErrorInfo<T>{
    public static final int ok=0;
    public static final int error=500;

    private int code;
    private String message;
    private String url;
    private T data;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
