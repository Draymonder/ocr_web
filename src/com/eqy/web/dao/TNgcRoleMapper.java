package com.eqy.web.dao;

import com.eqy.web.pojo.TNgcRole;
import java.util.List;
import java.util.Map;

public interface TNgcRoleMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(TNgcRole record);

    int insertSelective(TNgcRole record);

    TNgcRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TNgcRole record);

    int updateByPrimaryKey(TNgcRole record);
    
    List<TNgcRole>  selectAll();//手动  展示全部角色
    
    String selectNamebyID(int id);//手动  展示全部角色
    
    /**
     * @Title: countUserBean
     * @Description: 查询用户数量
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int countRoleBean();
    /**
     * @Title: selectUserListByPage
     * @Description: 分页查询用户数据
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
     List<TNgcRole> selectRoleListByPage(Map<String, Object> map);
     
     /**
      * @Title: selectAllbyID
      * @Description: 
      * @param @param id
      * @param @return 设定文件
      * @return List<UserBean> 返回类型
      * @throws
      */
     TNgcRole selectAllbyID(int id);
     
     /**
      * @Title: selectCountByID(role增加修改时使用）
      * @Description: 
      * @param @param id
      * @param @return 查重
      * @return List<UserBean> 返回类型
      * @throws
      */
     int selectCountByID(Map<String, Object> map);
    
     /**
      * @Title: updateRoleByID
      * @Description: 
      * @param @param id
      * @param @return 更新表单
      * @return List<UserBean> 返回类型
      * @throws
      */
     int updateRoleByID(TNgcRole  TNgcRole);
     
     /**
      * @Title: selectCountByNameorNumber
      * @Description: 
      * @param @param id
      * @param @return 数量
      * @return List<UserBean> 返回类型
      * @throws
      */
     int selectCountByNameorNumber(Map<String, Object> map);
     
     /**
      * @Title: selectRoleByConditionPage
      * @Description: 
      * @param @param id
      * @param @return 分页查询
      * @return List<UserBean> 返回类型
      * @throws
      */
     List<TNgcRole> selectRoleByConditionPage(Map<String, Object> map);
}