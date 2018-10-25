package com.eqy.web.controller.autority;
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
import com.eqy.web.service.authority.IAuthorityService;


@Controller
@RequestMapping(value = "/authorityManage")
public class AutorityController
{

  

    @Autowired
    private  IAuthorityService  authorityService;//服务层
    
    /**
     * @Title showAuthorityManage
     * @Description 展示权限展示界面
     * @param request
     */
    @RequestMapping(value = "/showAuthorityManage")
    public String showAuthorityManage(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	authorityService.displayAuthorityManage(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return "authority_manage/authority_manage";
    }
    
    /**
     * @Title showCreateAuthority
     * @Description 展示权限创建界面
     * @param request
     */
    @RequestMapping(value = "/showCreateAuthority")
    public String showCreateAuthority(HttpServletRequest request, Model model)
    {    String pageNum=request.getParameter("pageNum");
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        model.addAttribute("pageNum", pageNum);
        return "authority_manage/create_authority";
    }
  
    /**
     * @Title showCreateAuthority
     * @Description 创建权限
     * @param request
     */
	@RequestMapping(value = "/CreateAuthority")
    @ResponseBody
    public Map<String, Object> CreateAuthority(HttpServletRequest request)
    {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=authorityService.executeCreateAuthority(request);
    		
		} catch (Exception e)
        {
            e.printStackTrace();
        }   
       
        return returnMap;
    }
	
	/**
     * @Title showEditAuthority
     * @Description 展示编辑页面
     * @param request
     */
    @RequestMapping(value = "/showEditAuthority")
    public String showEditAuthority(HttpServletRequest request, Model model)
    {    
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	//userManageService.displayEditUser(request, model);
        	authorityService.displayEditAuthority(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "authority_manage/edit_authority";
    }
    
    /**
     * @Title editAuthority
     * @Description 编辑权限
     * @param request
     */
    @RequestMapping(value = "/editAuthority")
    @ResponseBody
    public Map<String, Object> editAuthority(HttpServletRequest request)
        
    {   
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=authorityService.executeEditAuthority(request);
		} catch (Exception e)
        {
            e.printStackTrace();
           
        }
        return returnMap;
    }
    
    /**
     * @Title removeAuthority
     * @Description 删除权限
     * @param request
     */
    @RequestMapping(value = "/removeAuthority")
    @ResponseBody
    public Map<String, Object> removeAuthority(HttpServletRequest request)
    {   
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = authorityService.executeRemoveAuthority(request);
		} catch (Exception e){
            e.printStackTrace();
            
        }
        return returnMap;
    }
    
    /**
     * @Title queryAuthorityByCondition
     * @Description 根据条件查询展示
     * @param request
     */
    @RequestMapping(value = "/queryAuthorityByCondition")
    @ResponseBody
    public Map<String, Object> queryAuthorityByCondition(HttpServletRequest request)
    {   
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try { 
    		returnMap = authorityService.displayQueryAuthorityByCondition(request);

		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
   
}
