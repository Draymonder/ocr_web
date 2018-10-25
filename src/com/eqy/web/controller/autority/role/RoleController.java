package com.eqy.web.controller.autority.role;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eqy.web.service.role.IRoleService;


@Controller
@RequestMapping(value = "/RoleManage")
public class RoleController
{
    @Autowired
    private  IRoleService  roleService ;//ssw add 服务层
    
    /**
     * @Title showRoleManage
     * @Description 展示角色管理页面
     * @param request
     */
    @RequestMapping(value = "/showRoleManage")
    public String showRoleManage(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	roleService.displayRoleManage(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "role_manage/role_manage";
    }
    
    /**
     * @Title roleDetail
     * @Description 展示角色管理页面
     * @param request
     */
    @RequestMapping(value = "/roleDetail")
    public String roleDetail(HttpServletRequest request, Model model)
    {
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	roleService.displayRoleDetail(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "role_manage/role_detail";
    }
    
    /**
     * @Title showCreateRole
     * @Description 创建角色页面
     * @param request
     */
    @RequestMapping(value = "/showCreateRole")
    public String showCreateRole(HttpServletRequest request, Model model)
    {    String pageNum=request.getParameter("pageNum");
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	//userManageService.displayCreateUser(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        model.addAttribute("pageNum", pageNum);
        return "role_manage/create_role";
    }
  
    /**
     * @Title CreateRole
     * @Description 创建角色
     * @param request
     */
	@RequestMapping(value = "/CreateRole")
    @ResponseBody
    public Map<String, Object> CreateRole(HttpServletRequest request)
    {  Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=roleService.executeCreateRole(request);
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
	
	 /**
     * @Title showEditRole
     * @Description 创建角色
     * @param request
     */
    @RequestMapping(value = "/showEditRole")
    public String showEditRole(HttpServletRequest request, Model model)
    {    
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	
        	roleService.displayEditRole(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "role_manage/edit_role";
    }
    
    /**
     * @Title editRole
     * @Description 编辑角色动作
     * @param request
     */
    @RequestMapping(value = "/editRole")
    @ResponseBody
    public Map<String, Object> editRole(HttpServletRequest request)
        
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=roleService.executeEditRole(request);
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
    
    /**
     * @Title removeRole
     * @Description 删除角色
     * @param request
     */
    @RequestMapping(value = "/removeRole")
    @ResponseBody
    public Map<String, Object> removeRole(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=roleService.executeRemoveRole(request);
		} catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }
    
    /**
     * @Title queryRoleByCondition
     * @Description 按照条件查询角色信息并展示
     * @param request
     */
    @RequestMapping(value = "/queryRoleByCondition")
    @ResponseBody
    public Map<String, Object> queryRoleByCondition(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try { 
    		returnMap=roleService.displayQueryRoleByCondition(request);
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
    
    @RequestMapping(value = "/showPermission")
    public String showPermission(HttpServletRequest request, Model model)
    {  
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	roleService.displayPermission(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "role_manage/permission_role";
    }
    @RequestMapping(value = "/rePermission")
    @ResponseBody
    public Map<String, Object> rePermission(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=roleService.executePermission(request);
		}catch (Exception e)
        {
            e.printStackTrace();
           
        }
        return returnMap;
    }
}
