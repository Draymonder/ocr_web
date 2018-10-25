package com.eqy.web.dao;
import java.util.List;
import java.util.Map;

import com.eqy.web.pojo.PermissionBean;
import com.eqy.web.pojo.UserPermissionBean;

/**
 * 
 * @ClassName: UserPermissionBeanMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author:  戴光浩
 * @date: 2018-5-11 下午1:41:38
 */
public interface UserPermissionBeanMapper
{
    /**
     * @Title: selectUserPermissionByAccountNum
     * @Description: 查询用户当前所有权限模块
     * @param @param fAccountNumber
     * @param @return 设定文件
     * @return List<UserPermissionBean> 返回类型
     * @throws
     */
    List<UserPermissionBean> selectUserPermissionByAccountNum(String fAccountNumber);
    /**
     * @Title: insertUserPermissionList
     * @Description: 插入用户默认权限
     * @param @param record
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int insertUserPermissionList(List<UserPermissionBean> list);
    /**
     * @Title: updateUserPermissionList
     * @Description: 更新用户权限
     * @param @param userPermissionBean
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int updateUserPermissionList(Map<String, Object> map);
    /**
     * @Title: removeUserPermissionList
     * @Description: 移除用户所有权限
     * @param @param fAccountNumber
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int removeUserPermissionList(String fAccountNumber);
    
    /**
    * @Title: selectPermListByAccount
    * @Description: 根据账号查询用户权限
    * @param @param fAccountNumber
    * @param @return    设定文件
    * @return List<PermissionBean>    返回类型
    * @throws
    */
    List<PermissionBean> selectPermListByAccount(String fAccountNumber);
    /**
     * @Title: deleteByfAccountNumber
     * @Description: 根据用户账号删除用户的权限信息
     * @param @param fAccountNumber
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int deleteByfAccountNumber(String fAccountNumber);
}