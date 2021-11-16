package com.xzq.dubbodemo.dao;

import com.xzq.dubbodemo.bean.TUser;

/**
 * @Author XZQ
 * @Date 2021/11/9 19:41
 *
**/
    
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    TUser select(String username, String passwd);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}