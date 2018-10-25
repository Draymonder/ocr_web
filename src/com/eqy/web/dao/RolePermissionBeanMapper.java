package com.eqy.web.dao;

import java.util.List;

import com.eqy.web.pojo.RolePermissionBean;


public interface RolePermissionBeanMapper {
    int deleteByPrimaryKey(String fId);

    int insert(RolePermissionBean rolePermissionBean);

    int insertSelective(RolePermissionBean record);

    RolePermissionBean selectByPrimaryKey(String fId);
    /**
     * 根据角色编码查询角色拥有的所有权限的编码
     * @param fRoleId
     * @return
     */
    List<String> selectPermissionsByfRoleCode(String fRoleId);

    int updateByPrimaryKeySelective(RolePermissionBean rolePermissionBean);

    int updateByPrimaryKey(RolePermissionBean rolePermissionBean);
}