package com.example.demo.bean;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;

@Component
@Api("swaggerSubTest")
public class SwaggerSubTest {
    private Long id;

    private String name;

    private SwaggerTest swaggerTest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SwaggerTest getSwaggerTest() {
        return swaggerTest;
    }

    public void setSwaggerTest(SwaggerTest swaggerTest) {
        this.swaggerTest = swaggerTest;
    }

    @Override
    public String toString() {
        return "SwaggerSubTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", swaggerTest=" + swaggerTest +
                '}';
    }
}
