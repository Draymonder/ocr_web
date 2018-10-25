package com.eqy.web.dao;

import com.eqy.web.pojo.TNgcMenu;
import java.util.List;


public interface TNgcMenuMapper {
   
    int insert(TNgcMenu record);

    int insertSelective(TNgcMenu record);

    /**
     * @Title: selectAllbyID
     * @Description: 
     * @param @param id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<TNgcMenu> selectAllbyID(int id);
    /**
     * @Title: deleteAllbyroleID
     * @Description: 删除角色和权限映射表t_ngc_menu的数据
     * @param @param 角色id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    int deleteAllbyroleID(int id);
    /**
     * @Title: deleteAllbyAuthorityId
     * @Description: 删除角色和权限映射表t_ngc_menu的数据
     * @param @param 权限表id
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    int deleteAllbyAuthorityId(int id);
   
    
    
}