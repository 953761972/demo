package com.example.demo.neo4j;

import com.example.demo.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author XZQ
 * @Date 2021/9/25 15:27
 **/
@SpringBootTest

@RunWith(SpringRunner.class)
public class SpringDataneo4j {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public  void test1() {
        movieRepository.findOneByTitle("");
    }

}
