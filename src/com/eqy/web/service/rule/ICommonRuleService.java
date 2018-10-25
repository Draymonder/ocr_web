package com.eqy.web.service.rule;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.ui.Model;

public interface ICommonRuleService {
     void showRuleListManage(HttpServletRequest request,Model model);
     Map<String,Object> queryRuleListByCondition(HttpServletRequest request,Model model);
     Map<String,Object>  executeRemoveRule(HttpServletRequest request,Model model);
     Map<String,Object>  ExecuteUpdateRuleStatus(HttpServletRequest request,Model model);
     void   displayCreateRule(HttpServletRequest request,Model model);
     Map<String,Object>  executeCreateRule(HttpServletRequest request,HttpSession session);
     void   displayEditRule(HttpServletRequest request,Model model);
     Map<String,Object>  executeEditRule(HttpServletRequest request,HttpSession session);
     
     
}
