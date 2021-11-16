package com.example.demo.bean;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Table(name = "log")
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid", nullable = false)
    private Integer id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}