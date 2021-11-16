package com.example.demo.bean;

import javax.persistence.*;


public class TRolePermission {

    @Id
    @Column(name = "id", nullable = false,insertable = false,updatable = false)
    private Long id;

    @Column(name = "RID")
    private Integer rid;

    @Column(name = "PID")
    private Integer pid;

    @ManyToOne
    @JoinColumn(name = "ID")
    private TRole role;



    @ManyToOne
    @JoinColumn(name = "ID")
    private TPermission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}