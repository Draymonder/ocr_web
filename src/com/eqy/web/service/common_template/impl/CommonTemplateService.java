package com.eqy.web.service.common_template.impl;

import com.eqy.utils.DateTransfer;
import com.eqy.web.dao.CommonTemplateBeanMapper;
import com.eqy.web.dao.FactoryBeanMapper;
import com.eqy.web.dao.TbCommonMapMapper;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.web.pojo.CommonTemplateBean;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TemplateBean;
import com.eqy.web.service.common_template.ICommonTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/7/11 下午3:34
 */
@Service
public class CommonTemplateService implements ICommonTemplateService {

    @Autowired
    private CommonTemplateBeanMapper commonTemplateBeanMapper;
    @Autowired
    protected FactoryBeanMapper factoryBeanMapper;

    @Autowired
    protected UserBeanMapper userBeanMapper;

    @Autowired
    private TbCommonMapMapper tbCommonMapMapper;

    @Override
    public void InsertTemplate(HttpServletRequest request, HttpSession session) {
        Map<String, Object> Tmap = new HashMap<>();
        Map<String, Object> Smap = new HashMap<>();
        //坐标拼接成字符串
        String coordinate = request.getParameter("x1")+","+request.getParameter("y1")+","+request.getParameter("x2")+","+request.getParameter("y2");
        String fieldName = request.getParameter("fieldName");
        try
        {
            String templateName = new String(request.getParameter( "templateName").getBytes("ISO-8859-1"), "UTF-8");

            Tmap.put("fileName",request.getParameter("fileName"));//文件名
            Tmap.put("fieldName", fieldName);//字段名
            Tmap.put("coordinate", coordinate);//坐标
            Tmap.put("templateName", templateName);
            Tmap.put("remark", request.getParameter( "remark").getBytes("iso8859-1"));
            String account_number=(String) session.getAttribute("User");
            Tmap.put("account_number", account_number);
            Tmap.put("date", DateTransfer.DateChange(new Date()));
            commonTemplateBeanMapper.insertBasicInfo(Tmap);

            Smap.put("mapper", request.getParameter("mapper"));
            Smap.put("fieldName", fieldName);
            Smap.put("id", commonTemplateBeanMapper.findIDByTemplateName(templateName));
            tbCommonMapMapper.insertMap(Smap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateTemplate(HttpServletRequest request) {
        Map<String, Object> Cmap = new HashMap<>();
        Map<String, Object> Smap = new HashMap<>();
        //坐标拼接成字符串
        String coordinate = request.getParameter("x1")+","+request.getParameter("y1")+","+request.getParameter("x2")+","+request.getParameter("y2");
        //将坐标以及数据库索引信息存到HashMap
        try
        {
            String templateName = new String(request.getParameter("templateName").getBytes("ISO-8859-1"), "UTF-8");
            Cmap.put("fieldName", request.getParameter("fieldName"));
            Cmap.put("coordinate", coordinate);
            Cmap.put("templateName", templateName);
            Cmap.put("remark", request.getParameter( "remark").getBytes("iso8859-1"));
            commonTemplateBeanMapper.updateCommonCoordinate(Cmap);

            Smap.put("mapper", request.getParameter("mapper"));
            Smap.put("fieldName", request.getParameter("fieldName"));
            Smap.put("id", commonTemplateBeanMapper.findIDByTemplateName(templateName));
            tbCommonMapMapper.updateMap(Smap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void TemplateAddManage(HttpServletRequest request, HttpSession session) {
        String templateName = null;
        try {
            templateName = new String(request.getParameter("templateName").getBytes("ISO-8859-1"), "UTF-8");
            int count = commonTemplateBeanMapper.countBeanByName(templateName);
            if(count == 0) InsertTemplate(request, session);
            else UpdateTemplate(request);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getFieldName() {
        return commonTemplateBeanMapper.findAllFieldName();
    }

	@Override
	public void showTemplateListManage(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<>();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer size =10;
        Integer count = commonTemplateBeanMapper.countDistinctTemplateBean(map);
        Pagenation page = new Pagenation(pageNum, size, count);
        //Pagenation分页已经改为mysql
        map.put( "start", page.getStartRow());
        map.put( "size", size);
        List<CommonTemplateBean> list = this.commonTemplateBeanMapper.findTemplateInfoByPage(map);
        for (CommonTemplateBean t: list){
            if(t != null){
                t.setFactoryName(factoryBeanMapper.findFactoryNameByFactoryNo(userBeanMapper.findFactoryNoByAccount(t.getAccount_number())));
                t.setSimpledate(TemplateBean.DateChange(t.getDate()));
                
            }
        }
        page.setList(list);
        model.addAttribute("page", page);
		
	}

	@Override
	public void displayEditTemplate(HttpServletRequest request, Model model) {
		String pageNum=request.getParameter("pageNum");
        CommonTemplateBean commonTemplate=new CommonTemplateBean();
        Map<String, Object> Cmap = new HashMap<>();
        try {
            Cmap.put("templateName", request.getParameter("templatename").getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       
		try{
			commonTemplate=commonTemplateBeanMapper.findByTemplateName(Cmap);
			commonTemplate.setSimpledate(TemplateBean.DateChange(commonTemplate.getDate()));
		} catch (Exception e)
        {
            e.printStackTrace();
        }
		
		model.addAttribute("TemplateBean", commonTemplate);
		model.addAttribute("pageNum", pageNum);
		
	}

	@Override
	public Map<String, Object> executeSelectTemplateByCondition(HttpServletRequest request) {
		 Map<String, Object> returnMap = new HashMap<>();
	        Map<String, Object> map = new HashMap<>();
	        Integer size = 10;
	        String msg = "";// 错误信息
	        String resultCode = "1";
	        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
	        String condition = request.getParameter("condition");
	        try{
	            if(condition != null) {
	                map.put( "templateName", condition);
	                map.put("begindate", request.getParameter("begindate"));
	                map.put("enddate", request.getParameter("enddate"));
	                Integer count =commonTemplateBeanMapper.countDistinctTemplateBean(map);
	                Pagenation page = new Pagenation(pageNum, size, count);
	                map.put("start", page.getStartRow());
	                map.put("size", size);
	                List<CommonTemplateBean> list = this.commonTemplateBeanMapper.findTemplateInfoByPage(map);
	                for (CommonTemplateBean t: list){
	                    if(t != null){
	                        t.setFactoryName(factoryBeanMapper.findFactoryNameByFactoryNo(userBeanMapper.findFactoryNoByAccount(t.getAccount_number())));
	                        t.setSimpledate(TemplateBean.DateChange(t.getDate()));

	                    }
	                }
	                page.setList(list);
	                returnMap.put("page", page);
	                resultCode = "0";
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            msg = "按条件查询异常!";
	        }
	        finally {
	            returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	        }
	        return returnMap;
	}

	@Override
	public Map<String, Object> executeEditTemplate(HttpServletRequest request, Model model) {
		String msg="" ;
	    String resultCode="1";
	    DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    Map<String,Object> map=new HashMap<>();
	    Map<String,Object> returnMap=new HashMap<>();
	    String templatename="";
	    String oldtemplatename="";
	    
	    try{
	    	templatename=request.getParameter("templatename");
	    	oldtemplatename=request.getParameter("oldtemplatename");
		int sl=commonTemplateBeanMapper.countBeanByName(templatename);
		if(sl>0&&!templatename.equals(oldtemplatename)){//模板名称重复
			msg="模板名称已存在！";
		}else{
            map.put("templateNameAfter",templatename);
            map.put("date",simpleDateFormat.format(new Date()));
            map.put("templateNameBefore",oldtemplatename);
            map.put("remark",request.getParameter("remark"));
            
            commonTemplateBeanMapper.updateTemplateName(map);
			//templateSearchMapper.updateTemplateName(map);	
			resultCode="0";
		}
	    } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
    	return returnMap;
	}

	@Override
	public Map<String, Object> removeTemplate(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<>();
        String msg = "";// 错误信息
        String resultCode = "1";
        //String pageNum=request.getParameter("pageNum");
        String templateName = request.getParameter("templatename");
        try{
        	this.commonTemplateBeanMapper.removeTemplate(templateName);
        	//this.templateRecognitionMapper.removeTemplate(templateName);
        	resultCode = "0";
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "注销模板异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		return returnMap;
	}

    @Override
    public String getCommonCoordinateAndnote(String templateName, String fieldName) {
        Map<String, Object> findMap = new HashMap<>();
        findMap.put("templateName", templateName);
        findMap.put("fieldName", fieldName);
        String result = commonTemplateBeanMapper.findCoordinateByOne(findMap);//查找坐标
        if(result==null) result = "";
        String jg=tbCommonMapMapper.findNoteByOne(findMap);  //查找注解
        if(jg==null) jg = "";
        result=result+";"+jg;
        return result;
    }

	@Override
	public Map<String, Object> templateCountBean(HttpServletRequest request) {
		String templateName=request.getParameter("templateName");
		 String msg="";
		 String resultCode="1";
		 Map<String,Object> returnMap=new HashMap<>();
		 try{
			 int sl= commonTemplateBeanMapper.countBeanByName(templateName);
			 if(sl==0){ //重复
				 resultCode="0";
			 }else{
				msg="模板名称重复！！！！";
			 }

		 }catch (Exception e)
	        {
	            e.printStackTrace();
	            msg = "模板查询异常";
	        }
	        finally
	        {
	            returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	        }
			return returnMap;
	}


}
