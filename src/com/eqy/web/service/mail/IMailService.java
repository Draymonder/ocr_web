package com.eqy.web.service.mail;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.eqy.web.pojo.TNgcMail;


public interface IMailService {
   
    int insert(TNgcMail record);

    int insertSelective(TNgcMail record);

    
    /**
     * @Title: selectOneByOne
     * @Description: 
     * @param @param 
     * @param @return 找出系统邮件服务器mail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    TNgcMail selectOneByOne();
    
    /**
     * @Title: update
     * @Description: 
     * @param @param 
     * @param @return updatemail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    void update(TNgcMail mail);
    
    void sendemail(String msg, String account);
    
    
    /**
     * @Title: displayMailManage
     * @Description:展示邮件服务器页面
     * @param @param 
     * @param @return updatemail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    void displayMailManage(HttpServletRequest request, Model model);
    
    /**
     * @Title:executeEditMail
     * @Description:修改邮件服务器配置页面
     * @param @param 
     * @param @return updatemail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    Map<String,Object> executeEditMail(HttpServletRequest request);
    
    /**
     * @Title:MailTest
     * @Description:邮箱验证码验证
     * @throws
     */
    Map<String,Object> executeMailTest(HttpServletRequest request,HttpSession session);
}
