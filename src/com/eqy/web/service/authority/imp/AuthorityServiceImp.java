package com.eqy.web.service.authority.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcAuthority;
import com.eqy.web.service.authority.IAuthorityService;
import com.eqy.utils.DateTransfer;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.TNgcAuthorityMapper;
import com.eqy.web.dao.TNgcMenuMapper;
@Service("authorityService")
public class AuthorityServiceImp implements IAuthorityService {
     @Autowired
     TNgcAuthorityMapper TNgcAuthorityMapper;
     @Autowired  
     TNgcMenuMapper TNgcMenuMapper;

	@Override
	public void displayAuthorityManage(HttpServletRequest request, Model model) {
		 Map<String, Object> map = new HashMap<String, Object>();
         Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
         Integer size = DictionaryMethod.size;
         //Integer count = this.userBeanMapper.countUserBean();
         Integer count =TNgcAuthorityMapper.countAuthorityBean();
         
         Pagenation page = new Pagenation(pageNum, size, count);
         Integer start=(pageNum-1)*size;
         map.put("start", start);
         map.put("size", page.getSize());
         System.out.println("数量:"+count);
         
         List<TNgcAuthority> list = TNgcAuthorityMapper.selectAuthorityListByPage(map);
         System.out.println("数量list:"+list.size());
       
         page.setList(list);
         model.addAttribute("page", page);
		
	}

	@Override
	public  Map<String,Object> executeCreateAuthority(HttpServletRequest request) {
		String msg="";//错误信息;
        String resultCode="";//执行结果
        String name=request.getParameter("authName");//权限名称
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		TNgcAuthority auth=new TNgcAuthority();
    		//returnMap = userManageService.executeCreateUser(request);
    		if(!name.equals("")){
    		Map<String, Object> a= new HashMap<String, Object>();
			a.put("name",name);
			int sl=TNgcAuthorityMapper.selectCountByID(a);
			if(sl>=1){
				msg="权限名称已存在！";
				resultCode = "1";
			}
			
			if(msg.equals("")){//没有冲突的情况 插入
				auth.setName(name);
				auth.setPermission(DateTransfer.DateChange(new Date()));
    			this.TNgcAuthorityMapper.insert(auth);
    			resultCode = "0";
    		 }
    		}else{
    			msg = "插入角色信息异常1";
    		}
    		
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "插入角色信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		return returnMap;
	}

	@Override
	public void displayEditAuthority(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
        String pageNum=request.getParameter("pageNum");
        List<TNgcAuthority>  auth=null;
        try {
        	auth=TNgcAuthorityMapper.selectAuthorityByID(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}finally
        {
            model.addAttribute("Authlist", auth);
            model.addAttribute("pageNum", pageNum);
        }
		
	}

	@Override
	public Map<String, Object> executeEditAuthority(HttpServletRequest request) {
		String msg = "";// 错误信息 
        String resultCode = "";// 执行结果
    	String id=request.getParameter("authId");//权限表id
        String name=request.getParameter("authName");//权限表name
        List<TNgcAuthority> auth =null;
        System.out.println("id"+id);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		if(!id.equals("")){
    			auth=TNgcAuthorityMapper.selectAuthorityByID(Integer.parseInt(id));
               if(auth.size()==1){
            	   
                 for(TNgcAuthority Authority:auth ){
    		      if(!name.equals(Authority.getName())){ //权限名称已修改，查重
    			   Map<String, Object> a= new HashMap<String, Object>();
    			   a.put("name",name);
    			   int sl=TNgcAuthorityMapper.selectCountByID(a);
    			    if(sl>=1){
    				msg="权限名称已存在！";
    				resultCode = "1";
    			   }
    		      }
                 }
               }
               
               if(msg.equals("")){//没有冲突的情况 更新
            	TNgcAuthority Authority=new TNgcAuthority();
            	Authority.setId(Integer.parseInt(id));
            	Authority.setName(name);
       			this.TNgcAuthorityMapper.updateAuthorityByID(Authority);
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
	public Map<String, Object> executeRemoveAuthority(HttpServletRequest request) {
		String authID=request.getParameter("authID");//权限ID
        System.out.println("authID:"+authID);
        String msg = "";// 错误信息 
        String resultCode = "";// 执行结果
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		if(authID.equals("")){
    			msg = "角色删除异常1";
    			resultCode = "1";
    		}else{
    			//此处删除权限表的数据，和映射表TNgcmenu的数据
    			this.TNgcMenuMapper.deleteAllbyAuthorityId(Integer.parseInt(authID));
    			this.TNgcAuthorityMapper.deleteByPrimaryKey(Integer.parseInt(authID));
    			resultCode = "0";
    		}
		} catch (Exception e){
            e.printStackTrace();
            msg = "删除权限信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        return returnMap;
	}

	@Override
	public Map<String, Object> displayQueryAuthorityByCondition(HttpServletRequest request) {
		String msg = "";// 错误信息
        String resultCode = "1";
        String condition = request.getParameter("condition");//此处为权限名称
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        System.out.println("condition"+condition);
        String authName="";
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<TNgcAuthority> list1=null;
    	try { 
    		//returnMap = userManageService.executeSelectUserByCondition(request);
            if (!condition.equals(""))
            {    authName=condition;
                map.put("name", authName);
                Integer count = this.TNgcAuthorityMapper.selectCountByName(map);
                //System.out.println("page"+pageNum);
                Integer size = DictionaryMethod.size;
                Pagenation page = new Pagenation(pageNum, size, count);
                Integer start=(pageNum-1)*size;
                map.put("start", start);
                map.put("size", page.getSize());
                list1 = this.TNgcAuthorityMapper.selectAuthorityByConditionPage(map);
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
