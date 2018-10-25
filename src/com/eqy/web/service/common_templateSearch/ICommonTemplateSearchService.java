package com.eqy.web.service.common_templateSearch;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.eqy.web.pojo.TNgcData;


public interface ICommonTemplateSearchService {
    

    /**
     * @Description 根据模板名称测试tess4j数据并更新到数据库中   add by ssw
     * @return
     */
    void updateData(Map<String, Object> map);
    
    
    void showTemplateSearchManage(HttpServletRequest request,Model model,HttpSession  session);
    
    Map<String, Object> executeQueryByCondition(HttpServletRequest request);
    
    void   dispalyDetail(HttpServletRequest request, Model model);
    
    Map<String, Object> executeEditDetail(HttpServletRequest request);
    
    Map<String, Object> executeupdateStatus(HttpServletRequest request);
}
