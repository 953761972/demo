package com.example.demo.netty.dubbo;

import java.io.Serializable;

/**
 * @Author XZQ
 * @Date 2021/9/15 20:07
 **/

public class InvokerProtocol implements Serializable {

    private String className;
    private String methodName;
    private Class<?>[] parames;
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParames() {
        return parames;
    }

    public Object[] getValues() {
        return values;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParames(Class<?>[] parames) {
        this.parames = parames;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
