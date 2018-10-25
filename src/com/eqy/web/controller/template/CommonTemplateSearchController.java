package com.eqy.web.controller.template;
import java.util.HashMap;
import java.util.List;
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
import com.eqy.web.service.common_templateSearch.ICommonTemplateSearchService;
import com.eqy.web.service.templatesearch.*;

@Controller
@RequestMapping(value = "/CommonTemplateSearchManage")
public class CommonTemplateSearchController
{
   
    @Autowired
    private ICommonTemplateSearchService CommonTemplateSearchService;//调用持久层
  
    
    /**
     * @Title showtaskSearchManage
     * @Description 展示任务检索页面
     * @param request
     */
    @RequestMapping(value = "/showCommonTemplateSearchManage")
    public String showTaskSearchrList(HttpServletRequest request, Model model,HttpSession session)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	CommonTemplateSearchService.showTemplateSearchManage(request,model,session);
        	
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "CommonTemplateSearch_manage/TemplateSearchManage";
    }
    /**
     * @Title queryRoleByCondition
     * @Description 按照条件查询角色信息并展示
     * @param request
     */
 
    @RequestMapping(value = "/queryByCondition")
    @ResponseBody
    public Map<String, Object> queryUserByCondition(HttpServletRequest request)
    {   
    	Map<String, Object> returnMap = new HashMap<>();
    	 try {
    		 returnMap=CommonTemplateSearchService.executeQueryByCondition(request);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    	 return returnMap;
    }
    
    /**
     * @Title showtaskSearchManage
     * @Description 展示任务检索页面
     * @param request
     */
    @RequestMapping(value = "/showDetail")
    public String showDetail(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	CommonTemplateSearchService.dispalyDetail(request,model);			

		} catch (Exception e) {
			e.printStackTrace();
		}
        return "CommonTemplateSearch_manage/edit";
    }
    /**
     * @Title Edit
     * @Description 用户确认编辑识别结果
     * @param request
     */
    @RequestMapping(value = "/Edit")
    @ResponseBody
    public Map<String, Object> EditDetail(HttpServletRequest request)
    {   
    	Map<String, Object> returnMap = new HashMap<>();
    	 try {
    		 returnMap=CommonTemplateSearchService.executeEditDetail(request);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    	 return returnMap;
    }
    
    
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(HttpServletRequest request)
    {   
    	Map<String, Object> returnMap = new HashMap<>();
    	 try {
    		 returnMap=CommonTemplateSearchService.executeupdateStatus(request);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    	 return returnMap;
    }

}
