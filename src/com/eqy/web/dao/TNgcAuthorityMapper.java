package com.eqy.web.dao;

import com.eqy.web.pojo.TNgcAuthority;


import java.util.List;
import java.util.Map;


public interface TNgcAuthorityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TNgcAuthority record);

    int insertSelective(TNgcAuthority record);

    TNgcAuthority selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TNgcAuthority record);

    int updateByPrimaryKey(TNgcAuthority record);
    
    /**
     * @Title: countAuthorityBean
     * @Description: 查询权限数量
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int countAuthorityBean();
    /**
     * @Title: selectAuthorityListByPage
     * @Description: 分页查询权限数据
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
     List<TNgcAuthority> selectAuthorityListByPage(Map<String, Object> map);
     
     /**
      * @Title: selectAllbyID
      * @Description: 
      * @param @param id
      * @param @return 设定文件
      * @return List<UserBean> 返回类型
      * @throws
      */
    /**
     * @Title: selectAll
     * @Description: 
     * @param @param id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcAuthority> selectAll();
    /**
     * @Title: selectAllByID
     * @Description: 角色id得到权限数据
     * @param @param id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcAuthority> selectAllByID(int id);
    
    /**
     * @Title: selectAuthorityByID
     * @Description: 权限id得到权限数据
     * @param @param id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcAuthority> selectAuthorityByID(int id);
    
    /**
     * @Title: selectCountByID(authority增加修改时使用）
     * @Description: 
     * @param @param id
     * @param @return 查重
     * @return List<UserBean> 返回类型
     * @throws
     */
    int selectCountByID(Map<String, Object> map);
   
    /**
     * @Title: updateAuthorityByID
     * @Description: 
     * @param @param id
     * @param @return 更新表单
     * @return List<UserBean> 返回类型
     * @throws
     */
    int updateAuthorityByID(TNgcAuthority  TNgcAuthority);
    
    
    /**
     * @Title: selectCountByName
     * @Description: 
     * @param @param id
     * @param @return 数量
     * @return List<UserBean> 返回类型
     * @throws
     */
    int selectCountByName(Map<String, Object> map);
    
    /**
     * @Title: selectAuthorityByConditionPage
     * @Description: 
     * @param @param id
     * @param @return 分页查询
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcAuthority> selectAuthorityByConditionPage(Map<String, Object> map);
    
    /**
     * @Title: selectAuthorityByAccount  !!!!!!!!!!
     * @Description: 
     * @param @param id
     * @param @return 根据当前人员账号去获取权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcAuthority> selectAuthorityByAccount(String Account);
}