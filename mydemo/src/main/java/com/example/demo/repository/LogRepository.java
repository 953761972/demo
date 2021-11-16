package com.example.demo.repository;

import com.example.demo.bean.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findByNameLike(String name, Pageable pageable);

    @Query("from Log where name like ?1% ")
    List<Log> findByQuery(String name, Pageable pageable);
}