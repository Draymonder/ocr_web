package com.eqy.web.service.authority;





import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;


public interface IAuthorityService {

    /**
     * @Title: displayAuthorityManage 
     * @Description: 
     * @param @param id
     * @param @return 展示所有权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    void displayAuthorityManage(HttpServletRequest request,Model model);
    
    
    /**
     * @Title: executeCreateAuthority
     * @Description: 
     * @param @param id
     * @param @return 展示所有权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeCreateAuthority(HttpServletRequest request);
    
    /**
     * @Title: displayEditAuthority
     * @Description: 
     * @param @param id
     * @param @return 展示权限编辑页面
     * @return List<UserBean> 返回类型
     * @throws
     */
    void displayEditAuthority(HttpServletRequest request,Model model);
    
    /**
     * @Title: executeEditAuthority
     * @Description: 
     * @param @param id
     * @param @return 编辑权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeEditAuthority(HttpServletRequest request);
    
    /**
     * @Title: executeEditAuthority
     * @Description: 
     * @param @param id
     * @param @return 删除权限
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> executeRemoveAuthority(HttpServletRequest request);
    
    
    /**
     * @Title: displayQueryAuthorityByCondition
     * @Description: 
     * @param @param id
     * @param @return 根据条件查询展示
     * @return List<UserBean> 返回类型
     * @throws
     */
    Map<String,Object> displayQueryAuthorityByCondition(HttpServletRequest request);

}