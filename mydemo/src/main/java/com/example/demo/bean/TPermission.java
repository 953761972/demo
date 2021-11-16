package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_PERMISSION")
@Entity
public class TPermission {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "URL", nullable = false)
    private Integer url;

    @Column(name = "NAME", nullable = false)
    private Integer name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }
}