package com.eqy.web.controller.autority.factory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcFactory;
import com.eqy.web.service.factory.IFactoryService;
import com.eqy.web.service.factory.imp.*;


@Controller
@RequestMapping(value = "/factoryManage")
public class FactoryController
{
   
    @Autowired
    private  IFactoryService  factoryService ;//ssw add 服务
    
    /**
     * @Title showfactoryManage
     * @Description 工厂数据展示界面
     * @param request
     */
    @RequestMapping(value = "/showfactoryManage")
    public String showfactoryManage(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	factoryService.displayFactoryManage(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return "factory_manage/factory_manage";
    }
      
    /**
     * @Title showCreatefactory
     * @Description 工厂创建界面
     * @param request
     */
    @RequestMapping(value = "/showCreatefactory")
    public String showCreatefactory(HttpServletRequest request, Model model)
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
        return "factory_manage/create_factory";
    }
  
    /**
     * @Title Createfactory
     * @Description 工厂创建动作
     * @param request
     */
	@RequestMapping(value = "/Createfactory")
    @ResponseBody
    public Map<String, Object> Createfactory(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=factoryService.executeCreateFactory(request);
    		
		} catch (Exception e)
        {
            e.printStackTrace();
        }
       
        return returnMap;
    }
	
	 /**
     * @Title showEditfactory
     * @Description 工厂信息编辑界面
     * @param request
     */
    @RequestMapping(value = "/showEditfactory")
    public String showEditfactory(HttpServletRequest request, Model model)
    {   
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
           factoryService.displayEditFactory(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "factory_manage/edit_factory";
    }
    
    /**
     * @Title editfactory
     * @Description 工厂信息编辑动作
     * @param request
     */
    @RequestMapping(value = "/editfactory")
    @ResponseBody
    public Map<String, Object> editfactory(HttpServletRequest request)
        
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=factoryService.executeEditFactory(request);
    		
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
    
    /**
     * @Title removefactory
     * @Description 删除工厂信息
     * @param request
     */
    @RequestMapping(value = "/removefactory")
    @ResponseBody
    public Map<String, Object> removefactory(HttpServletRequest request)
    { 
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = factoryService.executeRemovefactory(request);
		} catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }
    
    /**
     * @Title queryfactoryByCondition
     * @Description 删除工厂信息
     * @param request
     */
    @RequestMapping(value = "/queryfactoryByCondition")
    @ResponseBody
    public Map<String, Object> queryfactoryByCondition(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	
    	try { 
    		returnMap = factoryService.executeQueryfactoryByCondition(request);
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }

}
