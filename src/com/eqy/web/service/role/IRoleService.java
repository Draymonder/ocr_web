package com.eqy.web.service.role;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;


public interface IRoleService {

    /**
     * @Title: displayRoleManage
     * @Description: 
     * @param @param id
     * @param @return 展示全部角色
     * @return List<UserBean> 返回类型
     * @throws
     */
    void  displayRoleManage(HttpServletRequest request,Model model);
    
    /**
     * @Title:displayRoleDetail
     * @Description: 
     * @param @param id
     * @param @return 展示莫个角色具体信息
     * @return List<UserBean> 返回类型
     * @throws
     */
    void  displayRoleDetail(HttpServletRequest request,Model model);
    
    
    
    /**
     * @Title: executeCreateRole
     * @Description: 
     * @param @param id
     * @param @return 展示所有权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeCreateRole(HttpServletRequest request);
    
    
    /**
     * @Title: displayEditRole
     * @Description: 
     * @param @param id
     * @param @return 展示角色编辑页面
     * @return List<UserBean> 返回类型
     * @throws
     */
    void displayEditRole(HttpServletRequest request,Model model);
    
    
    /**
     * @Title: executeEditRole
     * @Description: 角色编辑页面
     * @param @param id
     * @param @return 
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeEditRole(HttpServletRequest request);
    
    /**
     * @Title: executeRemoveRole
     * @Description: 去除角色
     * @param @param id
     * @param @return 
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeRemoveRole(HttpServletRequest request);
    
    /**
     * @Title: displayQueryRoleByCondition
     * @Description: 根据条件查询展示
     * @param @param id
     * @param @return 
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> displayQueryRoleByCondition(HttpServletRequest request);
    
    
    /**
     * @Title: displayPermission
     * @Description: 根据条件查询展示
     * @param @param id
     * @param @return 
     * @return List<UserBean> 返回类型
     * @throws
     */
    void displayPermission(HttpServletRequest request,Model model);

    /**
     * @Title: displayPermission
     * @Description: 根据条件查询展示
     * @param @param id
     * @param @return 
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executePermission(HttpServletRequest request);
    
}
