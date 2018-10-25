package com.eqy.web.controller.mail;
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
import com.eqy.web.pojo.TNgcMail;
import com.eqy.web.service.mail.IMailService;
import com.eqy.web.service.mail.imp.MailServiceImp;
import com.eqy.mail.*;

@Controller
@RequestMapping(value = "/mailManage")
public class MailController
{
   
    @Autowired
    private  MailServiceImp  MailServiceImp ;//ssw add 服务层
    
    @Autowired
    private  IMailService mailService ;//ssw add 服务层
    
    /**
     * @Title showmailManage
     * @Description 展示邮件服务器信息界面
     * @param request
     */
    @RequestMapping(value = "/showmailManage")
    public String showmailManage(HttpServletRequest request, Model model)
    {    
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	mailService.displayMailManage(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "mail_manage/edit_mail";
    }
   
    /**
     * @Title edit_mail
     * @Description 编辑邮件服务器设置页面
     * @param request
     */
    @RequestMapping(value = "/edit_mail")
    @ResponseBody
    public Map<String, Object> EditMail(HttpServletRequest request)   
    {   
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap=mailService.executeEditMail(request);		
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
   
    /**
     * @Title mail_yz
     * @Description 邮件服务器往目标邮箱发送验证信息
     * @param request
     */
    @RequestMapping(value = "/mail_yz") //邮箱验证
    @ResponseBody
    public Map<String, Object> MailTest(HttpServletRequest request,HttpSession session)
        
    {   
       Map<String, Object> returnMap = new HashMap<String, Object>();
       try {
    	   returnMap=mailService.executeMailTest(request, session);
		} catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMap;
    }
}
