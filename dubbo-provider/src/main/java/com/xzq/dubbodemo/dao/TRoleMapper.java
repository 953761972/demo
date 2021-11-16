package com.xzq.dubbodemo.dao;

import com.xzq.dubbodemo.bean.TRole;

import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/11/9 23:39
 *
**/
    
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> findByUserName(String userName);
}