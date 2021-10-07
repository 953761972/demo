package com.example.demo.bean;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/9/25 16:47
 **/
@Node("Movie")
public class MovieEntity {

    @Id @GeneratedValue
    private Long id;

    private final String title;

    @Property("tagline")
    private final String description;

    public MovieEntity(String title, String description) {
        this.id = null;
        this.title = title;
        this.description = description;
    }

    public MovieEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            MovieEntity newObject = new MovieEntity(this.title, this.description);
            newObject.id = id;
            return newObject;
        }
    }
}