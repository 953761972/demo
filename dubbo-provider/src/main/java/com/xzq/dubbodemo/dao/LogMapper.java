package com.xzq.dubbodemo.dao;

import com.xzq.dubbocommon.bean.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/11/1 00:39
 *
**/
    
@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    int insertOne(Log record);

    List<Log> selectByCount();
}