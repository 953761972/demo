package com.example.demo.bean;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "T_USER")
@Entity
public class TUser {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "USERNAME", length = 20)
    private String username;

    @Column(name = "PASSWD", length = 128)
    private String passwd;

    @Column(name = "CREATE_TIME")
    private LocalDate createTime;

    @Column(name = "STATUS")
    private Character status;

    @OneToMany
    private List<TRole> roles;

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}