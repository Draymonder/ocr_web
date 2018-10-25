package com.eqy.web.service.role.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcAuthority;
import com.eqy.web.pojo.TNgcMenu;
import com.eqy.web.pojo.TNgcRole;
import com.eqy.web.service.role.IRoleService;
import com.eqy.web.service.usermanage.impl.UserManageServiceImpl;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.TNgcAuthorityMapper;
import com.eqy.web.dao.TNgcMenuMapper;
import com.eqy.web.dao.TNgcRoleMapper;
import com.eqy.web.dao.UserBeanMapper;
@Service("roleService")
public class RoleServiceImp implements IRoleService {
    @Autowired
    TNgcRoleMapper TNgcRoleMapper; 
    @Autowired
    UserBeanMapper UserBeanMapper;
    @Autowired
    TNgcAuthorityMapper TNgcAuthorityMapper; 
    @Autowired
    TNgcMenuMapper TNgcMenuMapper; 
    @Autowired
    private UserManageServiceImpl UserManageServiceImpl;

	@Override
	public void displayRoleManage(HttpServletRequest request, Model model) {
		 Map<String, Object> map = new HashMap<String, Object>();
         Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
         Integer size = DictionaryMethod.size;
         //Integer count = this.userBeanMapper.countUserBean();
         Integer count =TNgcRoleMapper.countRoleBean();
         
         Pagenation page = new Pagenation(pageNum, size, count);
         Integer start=(pageNum-1)*size;
         map.put("start", start);
         map.put("size", page.getSize());
         
         List<TNgcRole> list = TNgcRoleMapper.selectRoleListByPage(map);
         System.out.println("数量list:"+list.size());
        
         page.setList(list);
         model.addAttribute("page", page);
		
	}

	@Override
	public void displayRoleDetail(HttpServletRequest request, Model model) {
		 // 验证用户是否登录，未登录返回登录页面
    	String id = request.getParameter("id");
    	String pageNum = request.getParameter("pageNum");
    	TNgcRole  TNgcRole=null;
        try {
        	TNgcRole =TNgcRoleMapper.selectAllbyID(Integer.parseInt(id));
        	//System.out.println("名称："+TNgcRole.getRoleNumber());
        	TNgcRole.setRoleName(TNgcRole.getRoleName());
        	TNgcRole.setRoleNumber(TNgcRole.getRoleNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}finally
        {
            model.addAttribute("roleBean", TNgcRole);
            model.addAttribute("pageNum", pageNum);
        }
	}

	@Override
	public Map<String, Object> executeCreateRole(HttpServletRequest request) {
		String msg="";//错误信息;
        String resultCode="";//执行结果
        String roleName=request.getParameter("roleName");
        String roleNumber=request.getParameter("roleNumber");
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		TNgcRole role=new TNgcRole();
    		//returnMap = userManageService.executeCreateUser(request);
    		Map<String, Object> a= new HashMap<String, Object>();
			a.put("roleName",roleName);
			int sl=TNgcRoleMapper.selectCountByID(a);
			if(sl>=1){
				msg="角色名称已存在！";
				resultCode = "1";
			}
			Map<String, Object> b= new HashMap<String, Object>();
			b.put("roleNumber",roleNumber);
			int sl1=TNgcRoleMapper.selectCountByID(b);
			if(sl1>=1){
				msg+="角色编号已存在！";
				resultCode = "1";
			}
			if(msg.equals("")){//没有冲突的情况 插入
				role.setRoleName(roleName);
				role.setRoleNumber(roleNumber);
    			this.TNgcRoleMapper.insert(role);
    			resultCode = "0";
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
	public void displayEditRole(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
        String pageNum=request.getParameter("pageNum");
        TNgcRole  TNgcRole=null;   
       try {
       	  TNgcRole=TNgcRoleMapper.selectAllbyID(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}finally
        {
           model.addAttribute("roleBean", TNgcRole);
           model.addAttribute("pageNum", pageNum);
        }
		
	}

	@Override
	public Map<String,Object> executeEditRole(HttpServletRequest request) {
		String msg = "";// 错误信息 
        String resultCode = "";// 执行结果
    	String roleId=request.getParameter("roleId");//
        String roleName=request.getParameter("roleName");
        String roleNumber=request.getParameter("roleNumber");
        TNgcRole TNgcRole =null;
        //System.out.println("roleId"+roleId);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		//returnMap = userManageService.executeEditUser(request);
    		TNgcRole=TNgcRoleMapper.selectAllbyID(Integer.parseInt(roleId));
    		if(!roleName.equals(TNgcRole.getRoleName())){ //角色名称已修改，查重
    			Map<String, Object> a= new HashMap<String, Object>();
    			a.put("roleName",roleName);
    			int sl=TNgcRoleMapper.selectCountByID(a);
    			if(sl>=1){
    				msg="角色名称已存在！";
    				resultCode = "1";
    			}
    		}
    		if(!roleNumber.equals(TNgcRole.getRoleNumber())){//角色编号已修改，查重
    			Map<String, Object> b= new HashMap<String, Object>();
    			b.put("roleNumber",roleNumber);
    			int sl=TNgcRoleMapper.selectCountByID(b);
    			if(sl>=1){
    				msg+="角色编号已存在！";
    				resultCode = "1";
    			}
    		}
    		if(msg.equals("")){//没有冲突的情况 更新
    			TNgcRole.setId(Integer.parseInt(roleId));
    			TNgcRole.setRoleName(roleName);
    			TNgcRole.setRoleNumber(roleNumber);
    			this.TNgcRoleMapper.updateRoleByID(TNgcRole);
    			resultCode = "0";
    		}
    		 
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "更新角色信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        
        return returnMap;
	}

	@Override
	public Map<String, Object> executeRemoveRole(HttpServletRequest request) {
		    String roleID=request.getParameter("roleID");
	        System.out.println("roleID:"+roleID);
	        String msg = "";// 错误信息 
	        String resultCode = "";// 执行结果
	    	Map<String, Object> returnMap = new HashMap<String, Object>();
	    	try {
	    		//returnMap = userManageService.executeRemoveUser(request);
	    		if(roleID.equals("")){
	    			msg = "角色删除异常1";
	    			resultCode = "1";
	    		}else{
	    			this.TNgcRoleMapper.deleteByPrimaryKey(Integer.parseInt(roleID));
	    			resultCode = "0";
	    			//此处只是删除角色表的信息，没有删除用户表里角色的字段f_user_level
	    	        msg=this.UserManageServiceImpl.UpdateRole(Integer.parseInt(roleID));
	    	        if(!msg.equals("")){//删除的角色已经被用户用了，要提醒客户
	    	        	resultCode = "2";
	    	        }
	    		}
			} catch (Exception e){
	            e.printStackTrace();
	            msg = "删除角色信息异常";
	        }
	        finally
	        {
	            returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	        }
	        return returnMap;
	}

	@Override
	public Map<String, Object> displayQueryRoleByCondition(HttpServletRequest request) {
		String msg = "";// 错误信息
        String resultCode = "1";
        String condition = request.getParameter("condition");
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        String roleName="",roleNumber="";
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<TNgcRole> list1=null;
    	try { 
            if (!condition.equals(""))
            {
                if (condition.matches("^[0-9]{1,}$"))
                {
                	 roleNumber = condition;
                }
                else
                {
                	 roleName = condition;
                }
               
                map.put("roleName", roleName);
                map.put("roleNumber", roleNumber);
                Integer count = this.TNgcRoleMapper.selectCountByNameorNumber(map);
                System.out.println("page"+pageNum);
                
                Integer size = DictionaryMethod.size;
                Pagenation page = new Pagenation(pageNum, size, count);
                Integer start=(pageNum-1)*size;
                map.put("start", start);
                map.put("size", page.getSize());
                list1 = this.TNgcRoleMapper.selectRoleByConditionPage(map);
                
                page.setList(list1);
                returnMap.put("page", page);
                resultCode = "0";
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

	@Override
	public void displayPermission(HttpServletRequest request,Model model) {
		    String id = request.getParameter("id");//角色id
		    String pageNum = request.getParameter("pageNum");
		    List<TNgcAuthority> AuthList = null;
		    List<TNgcAuthority> AuthList1 = null;
		    String rolename="";
		    String bz="";//标志
	        try {
	        	//userManageService.displayUserPermission(request, model);
	        	AuthList= TNgcAuthorityMapper.selectAll();//展示所有权限
	        	id = new String(id.getBytes("iso8859-1"), "UTF-8");
	        	//此处展示角色权限，新建角色无权限，默认展示所有权限去勾选
	        	//MenuList = userPermissionBeanMapper.selectUserPermissionByAccountNum(fAccountNumber);
	        	if(!id.equals("")){
	        		rolename=TNgcRoleMapper.selectNamebyID(Integer.parseInt(id));
	        		AuthList1=TNgcAuthorityMapper.selectAllByID(Integer.parseInt(id));
	        	  if(AuthList1.size()>0){//该角色已有权限
	        		  bz="1";
	        		 
	        	  }else{//该角色还没有权限，展示所有权限
	        		AuthList1= null;
	        		bz="0";
	        	  }
	        	
	        	}
	        	//已有权限的角色，展示其固有权限
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally
	        {   model.addAttribute("bz",bz);
				model.addAttribute("roleID",id);
	            model.addAttribute("roleName", rolename);
	            model.addAttribute("list", AuthList);
	            model.addAttribute("list1", AuthList1);
	            model.addAttribute("pageNum", pageNum);
	        }
	       
	}

	@Override
	public  Map<String,Object> executePermission(HttpServletRequest request) {
		 String msg = "";// 错误信息
	       String resultCode = "1"; 
	       String roleID = request.getParameter("roleID");//角色id
	       String idstr = request.getParameter("idstr");
	       String[] Arry = idstr.split(",");
	       System.out.println("roleID:"+roleID+";idstr"+idstr);
	    	Map<String, Object> returnMap = new HashMap<String, Object>();
	    	try {
	    		if(!roleID.equals("")){
	    			TNgcMenuMapper.deleteAllbyroleID(Integer.parseInt(roleID));//删除该角色的权限
	    		  
	    		  for(String a:Arry){
	    			  TNgcMenu menu=new TNgcMenu();
	    			  menu.setRoleid(Integer.parseInt(roleID));
	    			  menu.setAuthorityid(Integer.parseInt(a));
	    			  TNgcMenuMapper.insert(menu);
	    			  resultCode = "0"; 
	    		  }
	    		}
	    		
			}catch (Exception e)
	        {
	            e.printStackTrace();
	            msg = "更改角色权限异常";
	        }
	        finally
	        {
	            returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	        }
	        
	        return returnMap;
		
	 }

}
