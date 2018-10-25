package com.eqy.web.service.template;

import com.eqy.web.pojo.TemplateBean;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/5/30 上午8:55
 */

public interface TemplateService {


	void templateManage(HttpServletRequest request, Model model);
	Map<String, String> FindAllFieldName();
	void InsertTemplate(HttpServletRequest request,HttpSession session);
	void UpdateTemplate(HttpServletRequest request);
	List<String> GetAllTemplateName();
	int UpdateTemplateName(Map<String,Object> map);
	Map<String,Object> FindCoordinate(String templateName, Integer pageNo);
	Map<String, Object> executeSelectTemplateByCondition(HttpServletRequest request);
	void TemplateAddManage(HttpServletRequest request,HttpSession session);
	/**
	 * @Description 根据模板名称查询到模板数据   add by ssw
	 * @return
	 */
	// Map<String,Object>   findBasicTemplateInfo(String demo);
	void displayEditTemplate(HttpServletRequest request, Model model);
	Map<String,Object>   executeEditTemplate(HttpServletRequest request, Model model);
	    
	Map<String,Object>   removeTemplate(HttpServletRequest request);
	    
	//模板14个标志识别是否重复
	Map<String, Object> getTemplateNameANDResult(String path);
	    
	//模板14个标志识别是否重复后，提交生产新数据
	Map<String,Object> templateRecognitionMake(HttpServletRequest request);
	    
	//新模板，查询新模板的模板名称不能重复
	Map<String,Object>  templateCountBean(HttpServletRequest request);

	String getCoordinate(String templateName, String fieldName);


}
