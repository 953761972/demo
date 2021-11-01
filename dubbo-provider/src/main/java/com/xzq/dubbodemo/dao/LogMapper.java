package com.xzq.dubbodemo.repository;

import com.xzq.dubbodemo.Log;
import org.apache.ibatis.annotations.Mapper;

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
}