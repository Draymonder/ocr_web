package com.eqy.web.controller.user;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eqy.web.service.usermanage.IUserManageService;

@Controller
@RequestMapping(value = "/userManage")
public class UserManageController
{
    @Autowired
    private IUserManageService userManageService;
    
    @RequestMapping(value = "/firstpage")
    public String firstpage(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	//userManageService.userManage(request, model);//计算报表的个人已办、待办数量
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return "user_manage/echarts";
    }
    
    @RequestMapping(value = "/showUserManage")
    public String showUserList(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	userManageService.userManage(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return "user_manage/user_manage";
    }
    @RequestMapping(value = "/userDetail")
    public String selectUserBean(HttpServletRequest request, Model model)
    {
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	userManageService.userDetail(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user_manage/user_detail";
    }
    
    @RequestMapping(value = "/showEditMyself")
    public String showEditMyself(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	userManageService.displayEditUser(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user_manage/edit_Myself";
    }
    @RequestMapping(value = "/showCreateUser")
    public String showCreateUser(HttpServletRequest request, Model model)
    {
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	userManageService.displayCreateUser(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user_manage/create_user";
    }
    @RequestMapping(value = "/CreateUser")
    @ResponseBody
    public Map<String, Object> CreateUser(HttpServletRequest request,HttpSession session)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = userManageService.executeCreateUser(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    @RequestMapping(value = "/showEditUser")
    public String showEditUser(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	userManageService.displayEditUser(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user_manage/edit_user";
    }
    @RequestMapping(value = "/editUser")
    @ResponseBody
    public Map<String, Object> EditUser(HttpServletRequest request,HttpSession session)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = userManageService.executeEditUser(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return returnMap;
    }
    @RequestMapping(value = "/removeUser")
    @ResponseBody
    public Map<String, Object> removeUser(HttpServletRequest request)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = userManageService.executeRemoveUser(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
    @RequestMapping(value = "/queryUserByCondition")
    @ResponseBody
    public Map<String, Object> queryUserByCondition(HttpServletRequest request)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = userManageService.executeSelectUserByCondition(request);
		} catch (Exception e) {
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
        	userManageService.displayUserPermission(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user_manage/permission_user";
    }
    @RequestMapping(value = "/rePermission")
    @ResponseBody
    public Map<String, Object> rePermission(HttpServletRequest request)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = userManageService.executeRedefineUserPermission(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return returnMap;
    }
}
