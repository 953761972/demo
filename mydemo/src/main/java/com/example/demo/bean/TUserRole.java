package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class TUserRole {
    @Id
    @Column(name = "id", nullable = false,insertable = false,updatable = false)
    private Long id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "RID")
    private Integer rid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}