package com.example.demo.bean;

import io.swagger.annotations.Api;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.stereotype.Component;

@Component
@Api("swaggerTest")
public class SwaggerTest {

    private Long id;

    private String name;

    private SwaggerSubTest swaggerSubTest;

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

    public SwaggerSubTest getSwaggerSubTest() {
        return swaggerSubTest;
    }

    public void setSwaggerSubTest(SwaggerSubTest swaggerSubTest) {
        this.swaggerSubTest = swaggerSubTest;
    }

    @Override
    public String toString() {
        return "SwaggerTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", swaggerSubTest=" + swaggerSubTest +
                '}';
    }
}
