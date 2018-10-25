package com.eqy.web.controller.autority.rule;
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
import com.eqy.web.service.rule.*;

@Controller
@RequestMapping(value = "/ruleManage")
public class RuleController
{
   
    @Autowired
    private IRuleService RuleService;//调用持久层
  
    
    /**
     * @Title showtaskSearchManage
     * @Description 展示任务检索页面
     * @param request
     */
    @RequestMapping(value = "/showRuleManage")
    public String showRulerList(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	RuleService.showRuleListManage(request,model);
        	
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return "rule_manage/ruleManage";
    }
    
    /**
     * @Title queryRuleByCondition
     * @Description 查询
     * @param request
     */

    @RequestMapping(value = "/removeRule")
    @ResponseBody
    public Map<String,Object> RemoveRule(HttpServletRequest request, Model model)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
        	returnMap=RuleService.executeRemoveRule(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
    
    @RequestMapping(value = "/UpdateRuleStatus")
    @ResponseBody
    public Map<String,Object> UpdateRuleStatus(HttpServletRequest request, Model model)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
        	returnMap=RuleService.ExecuteUpdateRuleStatus(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
    @RequestMapping(value = "/queryRuleByCondition")
    @ResponseBody
    public Map<String,Object> queryRuleByCondition(HttpServletRequest request, Model model)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
        	returnMap=RuleService.queryRuleListByCondition(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
    @RequestMapping(value = "/showCreateRule")
    public String showCreateRule(HttpServletRequest request,Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	RuleService.displayCreateRule(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "rule_manage/ruleCreate";
    }
    
    @RequestMapping(value = "/CreateRule")
    @ResponseBody
    public Map<String,Object> CreateRule(HttpServletRequest request,HttpSession session)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
        	returnMap=RuleService.executeCreateRule(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
    @RequestMapping(value = "/showEditRule")
    public String showEditRule(HttpServletRequest request,Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	RuleService.displayEditRule(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "rule_manage/ruleEdit";
    }
    @RequestMapping(value = "/EditRule")
    @ResponseBody
    public Map<String,Object> EditRule(HttpServletRequest request,HttpSession session)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
        	returnMap=RuleService.executeEditRule(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
   
    
}
