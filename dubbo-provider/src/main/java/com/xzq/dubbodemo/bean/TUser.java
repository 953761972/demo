package com.xzq.dubbodemo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author XZQ
 * @Date 2021/11/9 19:41
 *
**/
    
public class TUser implements Serializable {

    private static final long serialVersionUID = -5440372534300871944L;

    private Integer id;

    private String username;

    private String passwd;

    private Date createTime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}