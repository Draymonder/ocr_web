package com.eqy.web.service.common_template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/7/11 下午3:30
 */
public interface ICommonTemplateService {
    void InsertTemplate(HttpServletRequest request, HttpSession session);
    void UpdateTemplate(HttpServletRequest request);
    void TemplateAddManage(HttpServletRequest request,HttpSession session);
    List<String> getFieldName();
    void showTemplateListManage(HttpServletRequest request,Model model);
    
    void displayEditTemplate(HttpServletRequest request,Model model);
    
    
    Map<String,Object> executeSelectTemplateByCondition(HttpServletRequest request);
    
    Map<String, Object> executeEditTemplate(HttpServletRequest request,Model model);
    
    Map<String, Object> removeTemplate(HttpServletRequest request);
  //新模板，查询新模板的模板名称不能重复
  	Map<String,Object>  templateCountBean(HttpServletRequest request);
  	
    String getCommonCoordinateAndnote(String templateName,String fieldName);
}
