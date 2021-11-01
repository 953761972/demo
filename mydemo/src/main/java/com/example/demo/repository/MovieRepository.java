package com.example.demo.repository;

import com.example.demo.bean.MovieEntity;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author XZQ
 * @Date 2021/9/25 16:53
 **/
@Repository
public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, Long> {

    Mono<MovieEntity> findOneByTitle(String title);
}
