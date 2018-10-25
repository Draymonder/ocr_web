package com.eqy.web.service.rule.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eqy.tools.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.CommonTemplateBeanMapper;
import com.eqy.web.dao.TCommonRuleMapperMapper;
import com.eqy.web.dao.TRuleMapperMapper;
import com.eqy.web.dao.TbCommonMapMapper;
import com.eqy.web.dao.TbCommonRuleMapper;
import com.eqy.web.dao.TbCommonMapMapper;
import com.eqy.web.dao.TbCommonRuleMapper;
import com.eqy.web.dao.TemplateBeanMapper;
import com.eqy.web.pojo.CommonTemplateBean;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TCommonRuleMapper;
import com.eqy.web.pojo.TRuleMapper;
import com.eqy.web.pojo.TbCommonRule;
import com.eqy.web.pojo.TbRule;
import com.eqy.web.pojo.TemplateBean;
import com.eqy.web.pojo.UserBean;
import com.eqy.web.service.rule.ICommonRuleService;
import com.eqy.web.service.rule.IRuleService;
@Service("CommonRuleService")
public class CommonRuleServiceImp implements ICommonRuleService{
    @Autowired
     TbCommonRuleMapper tbCommonRuleMapper;
    @Autowired
     CommonTemplateBeanMapper  commonTemplate;
    @Autowired
    TemplateBeanMapper TemplateBeanMapper;
    @Autowired
    TCommonRuleMapperMapper TRuleMapperMapper;
    @Autowired
    protected TbCommonMapMapper TbCommonMapMapper;
	@Override
	public void showRuleListManage(HttpServletRequest request, Model model) {
		 Map<String, Object> map = new HashMap<String, Object>();
         Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
         Integer size = DictionaryMethod.size;
         Integer count =tbCommonRuleMapper.countBeanByLike(map);
         Pagenation page = new Pagenation(pageNum, size, count);
         map.put("start", page.getStartRow());
         map.put("size", page.getSize());
         try{
         List<TbCommonRule> list =tbCommonRuleMapper.selectRuleListByPageLike(map);
         List<CommonTemplateBean> list1=commonTemplate.selectAll();
         System.out.println("数量list:"+list.size());
         for(TbCommonRule rule:list){
        	 System.out.println("模板ID:"+rule.getTemplateId());
        	 if(rule.getDate()!=null){
        		 rule.setSimpleDate(TemplateBean.DateChange(rule.getDate()));
        	 }
        	 if(rule.getTemplateId()!=null){
        		 rule.setTemplateName(commonTemplate.selectByKey(rule.getTemplateId()));
        	 }
         }
         page.setList(list);
         model.addAttribute("page", page);
         model.addAttribute("list1", list1);
         } catch (Exception e)
         {
             e.printStackTrace();
         }
         
	}

	@Override
	public Map<String, Object> queryRuleListByCondition(HttpServletRequest request, Model model) {
		 String msg = "";// 错误信息
	     String resultCode = "1";
		 Map<String, Object> map = new HashMap<String, Object>();
         Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
         Integer size = DictionaryMethod.size;
         map.put("begindate", request.getParameter("begindate"));
         map.put("enddate", request.getParameter("enddate"));
         map.put("ruleName", request.getParameter("condition"));
         Integer count =tbCommonRuleMapper.countBeanByLike(map);
         Pagenation page = new Pagenation(pageNum, size, count);
         map.put("start", page.getStartRow());
         map.put("size", page.getSize());
         List<TbCommonRule> list=null;
         Map<String, Object> returnMap = new HashMap<String, Object>();
         try{
         list=tbCommonRuleMapper.selectRuleListByPageLike(map);
         System.out.println("数量list:"+list.size());
         for(TbCommonRule rule:list){
        	 if(rule.getDate()!=null){
        		 rule.setSimpleDate(TemplateBean.DateChange(rule.getDate()));
        	 }
        	 if(rule.getTemplateId()!=null){
        		 rule.setTemplateName(commonTemplate.selectByKey(rule.getTemplateId()));
        	 }
        	 
         }
           page.setList(list);
           returnMap.put("page", page);
           resultCode = "0";
         } catch (Exception e)
         {
             e.printStackTrace();
             msg="按条件查询有误";
         }finally{
        	 returnMap.put("resultCode", resultCode);
             returnMap.put("msg", msg); 
         }
         return returnMap;
	}

	@Override
	public Map<String, Object> executeRemoveRule(HttpServletRequest request, Model model) {
		 String msg = "";// 错误信息
	     String resultCode = "1";
	     String ruleID=request.getParameter("ruleID");
	     Map<String, Object> returnMap = new HashMap<String, Object>();
	     try{
	    	 if(!ruleID.equals("")&&ruleID!=null){
	    		 tbCommonRuleMapper.deleteByPrimaryKey(Integer.parseInt(ruleID));
	    		
	    		 TRuleMapperMapper.deleteAllByRuleId(Integer.parseInt(ruleID));//删除原有映射
	    		 resultCode="0";
	    	 }
	    	 
	     }catch (Exception e){
	    	 e.printStackTrace();
             msg="注销有误";
	     }finally{
        	 returnMap.put("resultCode", resultCode);
             returnMap.put("msg", msg); 
         }
		return returnMap;
	}

	@Override
	public Map<String, Object> ExecuteUpdateRuleStatus(HttpServletRequest request, Model model) {
		 String msg = "";// 错误信息
	     String resultCode = "1";
	     String newstatus=request.getParameter("status");//规则状态
	     String id=request.getParameter("id");//id
	    // String pageNum=request.getParameter("pageNum");//页数
	     int status=0;//变量
         TbCommonRule rule=new TbCommonRule();
	     Map<String, Object> returnMap = new HashMap<String, Object>();
	     try{
	    	    if(!id.equals("")&&id!=null){
	    		   if(newstatus.equals("0")){
	    			   status=1;
	    			
	    		   }else if(newstatus.equals("1")){
	    			   status=0;
	    		   }
	    		   if(status==0||status==1){
	    			   rule.setStatus(status);
	    			   rule.setId(Integer.parseInt(id));
	    		   tbCommonRuleMapper.updateByPrimaryKeySelective(rule); 
	    		   resultCode="0";
	    		   }else{
	    			 msg="启用或停用规则状态有误"; 
	    		   }
	    		 
	    	 }else{
	    		 msg="启用或停用规则ID有误";
	    	 }
	    	 
	     }catch (Exception e){
	    	 e.printStackTrace();
             msg="启用或停用规则有误";
	     }finally{
       	     returnMap.put("resultCode", resultCode);
             returnMap.put("msg", msg); 
        }
		return returnMap;
	}

	@Override
	public void displayCreateRule(HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String templateId = request.getParameter("templateId");
       
        Map<String, String> map=new TreeMap<String, String>();//a1字段名等字段的合集 
        try
        {
            map= MapUtil.sortMapByKey(TbCommonMapMapper.findAll(Integer.parseInt(templateId)));//按key值排序     a1,xxx;
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
           // model.addAttribute("channelList", channelList);
           // model.addAttribute("roleMap", roleMap);
        	model.addAttribute("template_id", templateId);
            model.addAttribute("colums", map);
            model.addAttribute("pageNum", pageNum);
        }
	}

	@Override
	public Map<String, Object> executeCreateRule(HttpServletRequest request, HttpSession session) {
		 String msg = "";// 错误信息
	     String resultCode = "1";
	     Map<String, Object> map = new HashMap<String, Object>();
	     Map<String, Object> returnMap = new HashMap<String, Object>();
	     String list=request.getParameter("list");
	     String[] Arry=list.split(",");
	     try{  
	    	   TCommonRuleMapper trm=new TCommonRuleMapper();
	    	   map.put("ruleName",request.getParameter("ruleName"));
	    	   map.put("ruleNumber",request.getParameter("ruleNumber"));
	    	   Integer number=tbCommonRuleMapper.countBeanByLikeOR(map);//判断规则名称和规则编号是否重复
	    	   if(number>=1){//重复
	    		   msg="规则名称或规则编号已重复！！请重新修改";
                }else{//插入数据
                	TbCommonRule t_rule=new TbCommonRule();
                	t_rule.setRuleName(request.getParameter("ruleName"));
                	t_rule.setRuleNumber(request.getParameter("ruleNumber"));
                	t_rule.setCovertype(request.getParameter("coverType"));
                	t_rule.setFlagA(request.getParameter("flagA"));
                	t_rule.setFlagB(request.getParameter("flagB"));
                	t_rule.setDate(new Date());
                	t_rule.setStatus(0);//默认新建即启用
                	t_rule.setTemplateId(Integer.parseInt(request.getParameter("Template_Id")));//默认新建即启用
//                	System.out.println("测试："+session.getAttribute("user"));
//                	String[]   names = session.getValueNames(); 
//                	for   (int   i   =   0;   i   <   names.length;   i++)   { 
//                	    System.out.println("测试"+i+"："+names[i] + ","   +   session.getValue(names[i]));
//                	}
                	UserBean user=(UserBean) session.getAttribute("user");
                	t_rule.setAccounctNumber(user.getfAccountNumber());
                	tbCommonRuleMapper.insert(t_rule);
                	//插入成功后，那数据插入到映射表里。
                	int id=t_rule.getId(); //新插入的id
                	for(String str:Arry){
                   ///xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx待修改xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                	    if(!str.equals("")){
                	    	trm.setRuleId(id); 
                	    	trm.setAcolumn(str);
                	    	TRuleMapperMapper.insert(trm);//那数据插入到t_rule_mapper映射表中
                	    }
                	
                	}
                	resultCode = "0";
                }
	    	 
	     }catch (Exception e)
	     {
	            e.printStackTrace();
	            msg="新建规则有误！！！";
	     }finally
	        {
	           
	    	 returnMap.put("msg",msg);
	    	 returnMap.put("resultCode", resultCode);
	        }
	     
		return returnMap;
	}

	@Override
	public void displayEditRule(HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String id=request.getParameter("id");
		String templateId = request.getParameter("templateId");
        //List<String> list1=null;//a1等字段的合集
        List<String> list2=null;//a1等字段被选中的合集
        TbCommonRule rule=new TbCommonRule();
        Map<String, String> map=new TreeMap<String, String>();//a1字段名等字段的合集 
        try
        {
            map = MapUtil.sortMapByKey(TbCommonMapMapper.findAll(Integer.parseInt(templateId)));//按key值排序     a1,xxx;
        	//list1=TemplateBeanMapper.findAllFieldName();//字段信息
        	rule=tbCommonRuleMapper.selectByPrimaryKey(Integer.parseInt(id));//规则表信息
        	list2=TRuleMapperMapper.selectByRuleID(Integer.parseInt(id));//被选中字段信息
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            model.addAttribute("map", map);
            model.addAttribute("list2", list2);
            model.addAttribute("rule", rule);
            model.addAttribute("pageNum", pageNum);
        }
	}

	@Override
	public Map<String, Object> executeEditRule(HttpServletRequest request,HttpSession session) {
		 String msg = "";// 错误信息
	     String resultCode = "1";
	     Map<String, Object> map = new HashMap<String, Object>();
	     Map<String, Object> returnMap = new HashMap<String, Object>();
	     String list=request.getParameter("list");
	     String[] Arry=list.split(",");
	     int id=Integer.parseInt(request.getParameter("id"));
	     boolean flag=true;
	     try{  
	    	 TCommonRuleMapper trm=new TCommonRuleMapper();
	    	   
	    	   TbCommonRule rule=tbCommonRuleMapper.selectByPrimaryKey(id);
	    	   if(rule.getRuleName().equals(request.getParameter("ruleName"))||rule.getRuleNumber().equals(request.getParameter("ruleNumber"))){
	    		  //规则编号和规则名没改
	    		   flag=false;
	    	   }
	    	   
	    	   map.put("ruleName",request.getParameter("ruleName"));
	    	   map.put("ruleNumber",request.getParameter("ruleNumber"));
	    	   Integer number=tbCommonRuleMapper.countBeanByLikeOR(map);//判断规则名称和规则编号是否重复
	    	   if(number>=1&&flag){//重复
	    		   msg="规则名称或规则编号已重复！！请重新修改";
                }else{//插入数据
                	TbCommonRule t_rule=new TbCommonRule();
                	t_rule.setId(id);
                	t_rule.setRuleName(request.getParameter("ruleName"));
                	t_rule.setRuleNumber(request.getParameter("ruleNumber"));
                	t_rule.setCovertype(request.getParameter("coverType"));
                	t_rule.setFlagA(request.getParameter("flagA"));
                	t_rule.setFlagB(request.getParameter("flagB"));
                	t_rule.setDate(new Date());
                	//t_rule.setStatus(0);//编辑不改变状态
                	UserBean user=(UserBean) session.getAttribute("user");
                	t_rule.setAccounctNumber(user.getfAccountNumber());
                	tbCommonRuleMapper.updateByPrimaryKeySelective(t_rule);
                	TRuleMapperMapper.deleteAllByRuleId(id);//删除原有映射
                	//插入成功后，那数据插入到映射表里。
                	for(String str:Arry){//
                	    if(!str.equals("")){
                	    	trm.setRuleId(id); 
                	    	trm.setAcolumn(str);
                	    	TRuleMapperMapper.insert(trm);//那数据插入到t_rule_mapper映射表中
                	    }
                	
                	}
                	resultCode = "0";
                }
	    	 
	     }catch (Exception e)
	     {
	            e.printStackTrace();
	            msg="修改规则有误！！！";
	     }finally
	        {
	           
	    	 returnMap.put("msg",msg);
	    	 returnMap.put("resultCode", resultCode);
	        }
	     
		return returnMap;
	}

}
