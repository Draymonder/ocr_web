package com.eqy.web.service.template.impl;

import com.eqy.tools.MapUtil;
import com.eqy.utils.DateTransfer;
import com.eqy.utils.TemplateRecognitionUtil;
import com.eqy.web.dao.*;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TemplateBean;
import com.eqy.web.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chengkang
 * 2018/5/30 上午8:56
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    protected TemplateBeanMapper templateBeanMapper;

    @Autowired
    protected FactoryBeanMapper factoryBeanMapper;

    @Autowired
    protected UserBeanMapper userBeanMapper;
    
    @Autowired
    protected TemplateRecognitionMapper templateRecognitionMapper;
    
    @Autowired
    protected TemplateSearchMapper templateSearchMapper;

    @Autowired
    protected TbMapMapper tbMapMapper;


    /**
     *  查询模板部分信息
     * @param request
     * @param model
     */
    @Override
    public void templateManage(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<>();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer size =10;
        Integer count = templateBeanMapper.countDistinctTemplateBean();
        Pagenation page = new Pagenation(pageNum, size, count);
        //Pagenation分页已经改为mysql
        map.put( "start", page.getStartRow());
        map.put( "size", size);
        List<TemplateBean> list = this.templateBeanMapper.findBasicTemplateInfoByPage(map);
        for (TemplateBean t: list){
            if(t != null){
                t.setFactoryName(factoryBeanMapper.findFactoryNameByFactoryNo(userBeanMapper.findFactoryNoByAccount(t.getAccount_number())));
                t.setSimpledate(TemplateBean.DateChange(t.getDate()));
                
            }
        }
        page.setList(list);
        model.addAttribute("page", page);
    }

  
	/**
     *  处理按条件查询模板
     * @param request
     * @return 回调resultMap给ajax
     */
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
                Integer count = templateBeanMapper.countDistinctTemplateBeanByCondition(map);
                Pagenation page = new Pagenation(pageNum, size, count);
                map.put("start", page.getStartRow());
                map.put("size", size);
                List<TemplateBean> list = this.templateBeanMapper.findBasicTemplateInfoByConditionPage(map);
                for (TemplateBean t: list){
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

    /**
     * 查询所有坐标类字段名
     * @return
     */
    @Override
    public Map<String, String> FindAllFieldName() {
        return tbMapMapper.findAll();
    }

    /**
     * 插入模板坐标
     * @Title InsertTemplate
     * @param request
     */
    @Override
    public void InsertTemplate(HttpServletRequest request,HttpSession session) {

        Map<String, Object> Tmap = new HashMap<>();
        //坐标拼接成字符串
        String coordinate = request.getParameter("x1")+","+request.getParameter("y1")+","+request.getParameter("x2")+","+request.getParameter("y2");
        try {
            String templateName = new String(request.getParameter("templateName").getBytes("ISO-8859-1"),"UTF-8");
            Tmap.put("fieldName", request.getParameter("fieldName"));
            Tmap.put("coordinate", coordinate);
            Tmap.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            Tmap.put("templateName", templateName);
            Tmap.put("remark", request.getParameter("remark").getBytes("ISO-8859-1"));
            String account_number=(String) session.getAttribute("User");
            Tmap.put("account_number", account_number);
            Tmap.put("date", DateTransfer.DateChange(new Date()));
            Tmap.put("fileName",request.getParameter("fileName"));
            this.templateBeanMapper.insertByTemplateNameANDPageNo(Tmap);
            //同时插入14个结果集
            Map<String, Object> inputMap = new HashMap<>();
            inputMap.put("templateName", templateName);
            String[] Array = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8").split(",");
            for(int i = 1; i < Array.length-1; i++){
                inputMap.put("r"+i, Array[i]);
            }
            templateRecognitionMapper.insertTemplate(inputMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新模板坐标
     * @Title UpdateTemplate
     * @param request
     */
    @Override
    public void UpdateTemplate(HttpServletRequest request) {

        Map<String, Object> Cmap = new HashMap<>();
        //坐标拼接成字符串
        String coordinate = request.getParameter("x1")+","+request.getParameter("y1")+","+request.getParameter("x2")+","+request.getParameter("y2");
        //将坐标以及数据库索引信息存到HashMap
        try
        {
        Cmap.put("fieldName", request.getParameter("fieldName"));
        Cmap.put("coordinate", coordinate);
        Cmap.put("templateName", request.getParameter("templateName").getBytes("ISO-8859-1"));
        Cmap.put("pageNo", Integer.valueOf(request.getParameter( "pageNo")));
        Cmap.put("remark", request.getParameter("remark").getBytes("ISO-8859-1"));
        this.templateBeanMapper.updateCoordinate(Cmap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *  处理坐标存储，选择Update 或 Insert
     * @param request
     */
    @Override
    public void TemplateAddManage(HttpServletRequest request,HttpSession session) {
        Map<String, Object> requestMap = new HashMap<>();
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));

        requestMap.put("pageNo", pageNo);
        try {
            requestMap.put("templateName", request.getParameter("templateName").getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int count = templateBeanMapper.countByTemplateNameANDPageNo(requestMap);
        if(count != 0)  UpdateTemplate(request);
        else InsertTemplate(request,session);
    }

    /**
     *  查询所有模板名
     * @return
     */
    @Override
    public List<String> GetAllTemplateName() {
        return this.templateBeanMapper.findAllTemplateName();
    }

    /**
     *  更新模板名
     * @param
     * @return
     */
    @Override
    public int UpdateTemplateName(Map<String,Object> map) {
        return this.templateBeanMapper.updateTemplateName(map);
    }

    /**
     *  获取数据库指定模板和页码坐标信息集合
     * @param
     * @return TemplateBean对象
     */
    @Override
    public  Map<String,Object> FindCoordinate(String templateName, Integer pageNo) {
        Map<String, Object> Cmap = new HashMap<>();
        Cmap.put("templateName", templateName);
        Cmap.put("pageNo",  pageNo);
        return this.templateBeanMapper.findCoordinate(Cmap);
    }

	@Override
	public void displayEditTemplate(HttpServletRequest request,Model model) {
		
		String pageNum=request.getParameter("pageNum");
        TemplateBean template=new TemplateBean();
        Map<String, Object> Cmap = new HashMap<>();
        try {
            Cmap.put("templateName", request.getParameter("templatename").getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cmap.put("pageNo", 1);//默认pageNo为1
		try{
			template=templateBeanMapper.findByTemplateNameANDPageNo(Cmap);
			template.setSimpledate(TemplateBean.DateChange(template.getDate()));
		} catch (Exception e)
        {
            e.printStackTrace();
        }
		
		model.addAttribute("TemplateBean", template);
		model.addAttribute("pageNum", pageNum);
		
	}

	@Override
	public Map<String, Object> executeEditTemplate(HttpServletRequest request, Model model) {
	    String msg="" ;
	    String resultCode="1";
	    DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    Map<String,Object> map=new HashMap<>();
	    Map<String,Object> returnMap=new HashMap<>();
	    try{
	        String templatename = new String(request.getParameter("templatename").getBytes("ISO-8859-1"),"UTF-8");
	        String oldtemplatename = new String(request.getParameter("oldtemplatename").getBytes("ISO-8859-1"),"UTF-8");
		int sl=templateBeanMapper.countBeanByname(templatename);
		if(sl>0&&!templatename.equals(oldtemplatename)){//模板名称重复
			msg="模板名称已存在！";
		}else{
            map.put("templateNameAfter",request.getParameter("templatename"));
            map.put("date",simpleDateFormat.format(new Date()));
            map.put("templateNameBefore",request.getParameter("oldtemplatename"));
            map.put("remark",request.getParameter("remark"));
            
			templateBeanMapper.updateTemplateName(map);
			templateSearchMapper.updateTemplateName(map);
			templateRecognitionMapper.updateRecognitionTemplateName(map);

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
 
	//删除模板信息
	@Override
	public Map<String, Object> removeTemplate(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<>();
        String msg = "";// 错误信息
        String resultCode = "1";
        //String pageNum=request.getParameter("pageNum");
        String templateName = request.getParameter("templatename");
        try{
        	this.templateBeanMapper.removeTemplate(templateName);
        	this.templateRecognitionMapper.removeTemplate(templateName);
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

    /**
     *
     * @param request
     * @return
     */
	@Override
	public  Map<String,Object> templateRecognitionMake(HttpServletRequest request) {
        String msg="";
        String resultCode = "1";
        String templateName = request.getParameter("templateName");
        Map<String, Object> returnMap = new HashMap<>();
        if(templateRecognitionMapper.CountBean(templateName) != 0) {
            resultCode = "0";//已有该模板14个识别坐标（重置模板）
        }
        else{
            if(templateBeanMapper.countBeanByname(templateName) == 0){//模板表没有制作痕迹,不允许提交。
                msg = "请先制作模板!";
            }
            else{
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("templateName", templateName);//模板名称
                String[] Array = request.getParameter("result").split(",");
                if(Array.length == 16) {
                    for(int i = 1; i < Array.length-1; i++){
                        resultMap.put("r"+i, Array[i]);
                    }
                    templateRecognitionMapper.insertTemplate(resultMap);
                    resultCode = "0";
                }
                else msg = "模板识别坐标结果异常!";
            }

        }
        returnMap.put("msg", msg);
        returnMap.put("resultCode", resultCode);
        return returnMap;
    }

    /**
     *  查询所属模板名称、14个坐标结果
     * @param path jpg路径
     * @return
     */
	@Override
	public Map<String, Object> getTemplateNameANDResult(String path) {
        String templateName="";
        StringBuilder str = new StringBuilder("begin,");
        Map<String, Object> ResultMap = TemplateRecognitionUtil.getTemplateResultMap(path);
        Map<String, Object> Map = new HashMap<>();
        for(int i = 1; i <= ResultMap.size(); i++){
            str.append(ResultMap.get("r"+i));
            str.append(",");
        }
        str.append("end");

        //查询所有模板r1-->r14的值，return List<Map>
        List<Map<String, Object>> list = this.templateRecognitionMapper.findMapList();
        for (Map<String, Object> m : list) {
            if(MapUtil.compareMap(ResultMap, m)) templateName = m.get("templateName").toString();
        }
        Map.put("result", str.toString());
        Map.put("templateName", templateName);
        return Map;
	}

	@Override
	public Map<String, Object> templateCountBean(HttpServletRequest request) {
		 String templateName=request.getParameter("templateName");
		 String msg="";
		 String resultCode="1";
		 Map<String,Object> returnMap=new HashMap<>();
		 try{
			 int sl=templateBeanMapper.countBeanByname(templateName);
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

    @Override
    public String getCoordinate(String templateName, String fieldName) {
	    int fieldNum = Integer.parseInt(fieldName.substring(1));
	    int pageNo = fieldNum <= 185 ? 1 : 2;
	    Map<String, Object> map = new HashMap<>();
	    map.put("templateName",templateName);
	    map.put("fieldName",fieldName);
	    map.put("pageNo",pageNo);
        return templateBeanMapper.findCoordinateByOne(map);
    }
}
