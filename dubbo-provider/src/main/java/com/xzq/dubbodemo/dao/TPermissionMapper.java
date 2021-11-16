package com.xzq.dubbodemo.dao;

import com.xzq.dubbodemo.bean.TPermission;

import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/11/9 23:39
 *
**/
    
public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<TPermission> findByUserName(String userName);
}