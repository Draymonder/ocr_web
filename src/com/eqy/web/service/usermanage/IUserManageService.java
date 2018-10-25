package com.eqy.web.service.usermanage;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface IUserManageService
{
    public void userManage(HttpServletRequest request, Model model);
    
    public void userDetail(HttpServletRequest request, Model model);
    
    public void displayCreateUser(HttpServletRequest request, Model model);
    
    public Map<String,Object> executeCreateUser(HttpServletRequest request, HttpSession session);
    
    public void displayEditUser(HttpServletRequest request, Model model);
    
    public Map<String,Object> executeEditUser(HttpServletRequest request, HttpSession session);
    
    public Map<String,Object> executeRemoveUser(HttpServletRequest request);
    
    public Map<String,Object> executeSelectUserByCondition(HttpServletRequest request);
    
    public void displayUserPermission(HttpServletRequest request, Model model);
    
    public Map<String,Object> executeRedefineUserPermission(HttpServletRequest request);
    //add by  ssw 
    public String  UpdateRole(int roleid);

}
