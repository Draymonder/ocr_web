package com.eqy.web.dao;

import com.eqy.web.pojo.UserRoleBean;


public interface UserRoleBeanMapper {

    int deleteByPrimaryKey(String fAccountNumber);

    int insert(UserRoleBean userRoleBean);

    int insertSelective(UserRoleBean userRoleBean);

    UserRoleBean selectByPrimaryKey(String fAccountNumber);

    int updateByPrimaryKeySelective(UserRoleBean userRoleBean);

    int updateByPrimaryKey(UserRoleBean userRoleBean);
}