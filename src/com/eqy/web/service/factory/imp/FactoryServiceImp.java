package com.eqy.web.service.factory.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcFactory;
import com.eqy.web.service.factory.IFactoryService;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.TNgcFactoryMapper;
@Service("factoryService")
public class FactoryServiceImp  implements IFactoryService {
    @Autowired
    TNgcFactoryMapper TNgcFactoryMapper;

	@Override
	public void displayFactoryManage(HttpServletRequest request, Model model) {
        try {
        	 Map<String, Object> map = new HashMap<String, Object>();
             Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
             Integer size = DictionaryMethod.size;
             Integer count =TNgcFactoryMapper.countFactoryBean();
             
             Pagenation page = new Pagenation(pageNum, size, count);
             Integer start=(pageNum-1)*size;
             map.put("start", start);
             map.put("size", page.getSize());
             //System.out.println("数量:"+count);
             List<TNgcFactory> list = TNgcFactoryMapper.selectFactoryListByPage(map);
             System.out.println("数量list:"+list.size());
             page.setList(list);
             model.addAttribute("page", page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> executeCreateFactory(HttpServletRequest request) {
		String msg="";//错误信息;
        String resultCode="";//执行结果
        String name=request.getParameter("name");//工厂名称
        String number=request.getParameter("number");//工厂编号
        String address=request.getParameter("address");//工厂地址
        //String id=request.getParameter("Name");//权限名称
        
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		TNgcFactory factory=new TNgcFactory();
    		if(!name.equals("")){
    		Map<String, Object> a= new HashMap<String, Object>();
			a.put("name",name);
			int sl=TNgcFactoryMapper.selectCountByID(a);
			if(sl>=1){
				msg="工厂名称已存在！";
				resultCode = "1";
			}
			Map<String, Object> b= new HashMap<String, Object>();
			b.put("number",number);
			int sl1=TNgcFactoryMapper.selectCountByID(b);
			if(sl1>=1){
				msg="工厂编号已存在！";
				resultCode = "1";
			}
			if(msg.equals("")){//没有冲突的情况 插入
				factory.setName(name);
				factory.setNumber(number);
				factory.setAddress(address);
    			this.TNgcFactoryMapper.insert(factory);
    			resultCode = "0";
    		 }
    		}else{
    			msg = "插入工厂信息异常1";
    		}
    		
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "插入工厂信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        return returnMap;
	}

	@Override
	public void displayEditFactory(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
        String pageNum=request.getParameter("pageNum");
        TNgcFactory  factory=null;
       try {
       	factory=TNgcFactoryMapper.selectByPrimaryKey(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}finally
       {
           model.addAttribute("FactoryBean", factory);
           model.addAttribute("pageNum", pageNum);
       }
  
	}

	@Override
	public Map<String,Object> executeEditFactory(HttpServletRequest request) {
		String msg = "";// 错误信息 
        String resultCode = "";// 执行结果
    	String id=request.getParameter("id");//权限表id
    	 String name=request.getParameter("name");//工厂名称
         String number=request.getParameter("number");//工厂编号
         String address=request.getParameter("address");//工厂地址
     
        TNgcFactory factory =null;
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		if(!id.equals("")){
    			factory=TNgcFactoryMapper.selectByPrimaryKey(Integer.parseInt(id));
               if(factory.getId()!=null){
    		      if(!name.equals(factory.getName())){ //工厂名称已修改，查重
    			   Map<String, Object> a= new HashMap<String, Object>();
    			   a.put("name",name);
    			   int sl=TNgcFactoryMapper.selectCountByID(a);
    			    if(sl>=1){
    				msg="工厂名称已存在！";
    				resultCode = "1";
    			   }
    			    
    		      }
    		      if(!number.equals(factory.getNumber())){ //工厂名称已修改，查重
       			   Map<String, Object> a= new HashMap<String, Object>();
       			   a.put("name",name);
       			   int sl=TNgcFactoryMapper.selectCountByID(a);
       			    if(sl>=1){
       				msg="工厂编号已存在！";
       				resultCode = "1";
       			   }
       		      } 
               }
               if(msg.equals("")){//没有冲突的情况 更新
            	TNgcFactory factory2=new TNgcFactory();
            	factory2.setId(Integer.parseInt(id));
            	factory2.setName(name);
            	factory2.setNumber(number);
            	factory2.setAddress(address);
       			this.TNgcFactoryMapper.updateByPrimaryKey(factory2);
       			resultCode = "0";
       		   }
       		 
    		} else{
    			msg = "更新权限信息异常11";
    			resultCode = "1";
    		}
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "更新权限信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        
        return returnMap;
		
	}

	@Override
	public Map<String, Object> executeRemovefactory(HttpServletRequest request) {
		String id=request.getParameter("id");//权限ID
        //System.out.println("id:"+id);
        String msg = "";// 错误信息 
        String resultCode = "";// 执行结果
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		if(id.equals("")){
    			msg = "角色删除异常1";
    			resultCode = "1";
    		}else{
    			//此处删除权限表的数据，和映射表TNgcmenu的数据
    			//此处少一个删除用户表中工厂的信息；
    			this.TNgcFactoryMapper.deleteByPrimaryKey(Integer.parseInt(id));
    			resultCode = "0";
    		}
		} catch (Exception e){
            e.printStackTrace();
            msg = "删除工厂信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        return returnMap;
	}

	@Override
	public Map<String, Object> executeQueryfactoryByCondition(HttpServletRequest request) {
		    String msg = "";// 错误信息
	        String resultCode = "1";
	        String condition = request.getParameter("condition");//此处为权限名称
	        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
	        //System.out.println("condition"+condition);
	        String name="",number="";
	    	Map<String, Object> returnMap = new HashMap<String, Object>();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	List<TNgcFactory> list1=null;
	    	try { 
	            if (!condition.equals(""))
	            {     if (condition.matches("^[0-9]{1,}$"))
	                   {
	           	        number = condition;
	                   }
	                   else
	                  {
	           	        name = condition;
	                   }
	                map.put("name", name);
	                map.put("number",  number);
	                Integer count = this.TNgcFactoryMapper.selectCountByIDLike(map);
	                System.out.println("page"+pageNum);
	                Integer size = DictionaryMethod.size;
	                Pagenation page = new Pagenation(pageNum, size, count);
	                Integer start=(pageNum-1)*size;
	                map.put("start", start);
	                map.put("size", page.getSize());
	                list1 = this.TNgcFactoryMapper.selectfactoryByConditionPage(map);
	               
	                page.setList(list1);
	                returnMap.put("page", page);
	                resultCode = "0";
	            }else{
	            	resultCode = "1";
	            	msg = "按条件查询角色异常1";
	            }
	    		
			} catch (Exception e)
	        {
	            e.printStackTrace();
	            msg = "按条件查询角色异常";
	        }
	        finally
	        {
	            returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	        }
	        return returnMap;
	}

}
