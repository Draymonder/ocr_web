package com.eqy.web.service.usermanage.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.utils.DateTimeUtil;
import com.eqy.utils.DictionaryMethod;
import com.eqy.utils.MD5;
import com.eqy.web.dao.RolePermissionBeanMapper;
import com.eqy.web.dao.TNgcFactoryMapper;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.web.dao.UserPermissionBeanMapper;
import com.eqy.web.dao.UserRoleBeanMapper;
import com.eqy.web.dao.TNgcRoleMapper;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcFactory;
import com.eqy.web.pojo.UserBean;
import com.eqy.web.pojo.UserPermissionBean;
import com.eqy.web.pojo.UserRoleBean;
import com.eqy.web.pojo.TNgcRole;
import com.eqy.web.service.usermanage.IUserManageService;

@Service("userListService")
public class UserManageServiceImpl implements IUserManageService
{
	
    @Autowired
    UserBeanMapper userBeanMapper;
    @Autowired
    UserRoleBeanMapper userRoleBeanMapper;
    @Autowired
    RolePermissionBeanMapper rolePermissionBeanMapper;
    @Autowired
    UserPermissionBeanMapper userPermissionBeanMapper;
  
    @Autowired
    TNgcRoleMapper TNgcRoleMapper;
    @Autowired
    TNgcFactoryMapper TNgcFactoryMapper;
  
    @Override
	public void userManage(HttpServletRequest request, Model model) {
    	try
        {
            
            Map<String, Object> map = new HashMap<String, Object>();
            Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
            Integer size = DictionaryMethod.size;
            Integer count = this.userBeanMapper.countUserBean();
            Pagenation page = new Pagenation(pageNum, size, count);
            map.put("size", page.getSize());//每页行数
            Integer start=(pageNum-1)*size;
            map.put("start", start);//重第一行开始,系统size为10
            List<UserBean> list = this.userBeanMapper.selectUserListByPage(map);
            for (UserBean userBean : list)
            {   /*
            	if(!"0".equals(userBean.getfUserLevel())){
            		ChannelBean channelBean = this.channelBeanMapper.selectChannelByChannelId(userBean.gettChannelId());
                	if(channelBean != null){
            		userBean.setChannelName(channelBean.gettChannelName());
                	}else {
                		userBean.setChannelName("--");
                	}
            		
            	} */ 
            	//System.out.println("角色角色:"+userBean.getfUserLevel());
            	if(userBean.getfUserLevel().equals("")){//删除角色后可能为空。加一个判断
            		userBean.setRoleName("");
            	}else{
            	userBean.setRoleName(TNgcRoleMapper.selectNamebyID(Integer.parseInt(userBean.getfUserLevel())));
            	}
                userBean.setfState(DictionaryMethod.useStatusMap.get(userBean.getfState()));
            }
            page.setList(list);
            model.addAttribute("page", page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
	}
    
    @Override
	public void userDetail(HttpServletRequest request, Model model) {
    	String fAccountNumber = request.getParameter("fAccountNumber");
    	String pageNum = request.getParameter("pageNum");
    	
        UserBean userBean = null;
        try
        {
        	fAccountNumber = new String(fAccountNumber.getBytes("iso8859-1"), "UTF-8");
            userBean = this.userBeanMapper.selectUserByAccountNum(fAccountNumber);
            String xx="",yy="";
            if(!userBean.getfUserLevel().equals("")){
             xx=TNgcRoleMapper.selectNamebyID(Integer.parseInt(userBean.getfUserLevel()));
            }
            if(!userBean.getFactory().equals("")){
             yy=TNgcFactoryMapper.selectByPrimaryKey(Integer.parseInt(userBean.getFactory())).getName();
            }
            userBean.setRoleName(xx);
            userBean.setFactoryname(yy);
            userBean.setfState(DictionaryMethod.useStatusMap.get(userBean.getfState()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            model.addAttribute("userBean", userBean);
            model.addAttribute("pageNum", pageNum);
        }
		
	}
    
    @Override
	public void displayCreateUser(HttpServletRequest request, Model model) {
    	String pageNum = request.getParameter("pageNum");
    	//Map<String, String> roleMap1 = new HashMap<String, String>();
    	List<TNgcRole> roleMap = null;
    	List<TNgcFactory> factoryMap = null;
        //List<ChannelBean> channelList = null;
        try
        {
            //channelList = channelBeanMapper.selectChannelList();
            //roleMap = DictionaryMethod.roleMap; //原先的的role是静态加载(自己定义的数据)，不是实时获取数据库的
            roleMap=(List<TNgcRole>) TNgcRoleMapper.selectAll();
            factoryMap=(List<TNgcFactory>) TNgcFactoryMapper.selectAll();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
           // model.addAttribute("channelList", channelList);
            model.addAttribute("roleMap", roleMap);
            model.addAttribute("factoryMap", factoryMap);
            model.addAttribute("pageNum", pageNum);
        }
		
	}
    
    @Override
	public Map<String,Object> executeCreateUser(HttpServletRequest request,HttpSession session) {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        String msg = "";// 错误信息
        String resultCode = "1";
        UserBean loginUser = (UserBean) request.getSession().getAttribute("user");
        String userCityName = request.getParameter("userCityName");
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userNumber = request.getParameter("userNumber");
        String userLevel = request.getParameter("userLevel");
        String userState = request.getParameter("userState");
        String tChannelId = request.getParameter("tChannelId");
        String username = request.getParameter("username");
        String factory = request.getParameter("factory");
        //String mail = request.getParameter("mail");
        String department = request.getParameter("department");
       //此处验证邮箱验证码是否正确
       // String code = request.getParameter("code");//邮箱验证码
       // String code1= (String) session.getAttribute(mail);//session存存的验证码
       // session.setAttribute(mail,"");//取到session中的code值后置为空，同是设置填写的验证码不能为空
        
       // if(code.equalsIgnoreCase(code1)){
        UserRoleBean userRoleBean = new UserRoleBean();
        userRoleBean.setfAccountNumber(userAccount);
        userRoleBean.setfRoleCode(userLevel);
        userRoleBean.setfCreateTime(DateTimeUtil.getTodayChar17());
        UserBean userBean = new UserBean();
        try
        {   
        	//校验用户名的唯一性
            UserBean userBean2 = this.userBeanMapper.selectUserByAccountNum(userAccount);
            //校验手机号的唯一性
            UserBean userBean3 = this.userBeanMapper.selectUserByUserNum(userNumber);
            
            if (userBean2 == null && userBean3 == null)
            {
            	//创建用户
                userBean.setfCity(userCityName);
                userBean.setfAccountNumber(userAccount);
                userBean.setfAccountPassword(MD5.Md5(userPassword));
                userBean.setfUserNumber(userNumber);
                userBean.setfUserLevel(userLevel);
                userBean.setfState(userState);
                userBean.setfCityCode("0");
               // userBean.setMail(mail);//add ssw
                userBean.setFactory(factory);//add ssw
                userBean.setUsername(username);//add ssw 
                userBean.setDepartment(department);//add ssw 
              //  userBean.setfFatherNumber(loginUser.getfAccountNumber());
                if(!"0".equals(userLevel)){//系统管理员没有渠道属性,渠道ID为空
                	userBean.settChannelId(tChannelId);
                }
                userBean.setfCreateTime(DateTimeUtil.getTodayChar17());
                this.userBeanMapper.insertUser(userBean);
                //int d=0;
                //创建该用户的角色T_CCS_USER_ROLE不存在
               // int d = this.userRoleBeanMapper.insert(userRoleBean);
                //if(!(d>0)){
                //	msg = "创建用户角色失败！";
              // }
              //创建该用户的权限
              //  UserPermissionBean userPermissionBean = null;
               // List<UserPermissionBean> userPermissionBeanList = new ArrayList<UserPermissionBean>();
                //List<String> permissions = this.rolePermissionBeanMapper.selectPermissionsByfRoleCode(userLevel);
                	//for (String permission : permissions) {
                	//	userPermissionBean = new UserPermissionBean();
                	//	userPermissionBean.setfAccountNumber(userAccount);
                	//	userPermissionBean.setfPermissionCode(permission);
                	//	userPermissionBean.setfCreateTime(DateTimeUtil.getTodayChar17());
                	//	userPermissionBean.setfState("0");	
                	//	userPermissionBeanList.add(userPermissionBean);
    				//}
                	//d = this.userPermissionBeanMapper.insertUserPermissionList(userPermissionBeanList);
                	//if(d!=0){
                   // 	msg = "创建用户初始化权限失败！";
                   // }
                
                resultCode = "0";
            }
            else if(userBean2 != null)
            {
                msg = "该用户已经存在！";
            }else if(userBean3 != null)
            {
            	msg = "该联系电话已经存在！";
            }else{
            	
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            msg = "创建用户异常";
        }
        finally
        {   
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
//        }else{
//        	
//        	resultCode = "1";
//        	msg = "验证码错误";
//        	returnMap.put("resultCode", resultCode);
//            returnMap.put("msg", msg);
//        	
//        }
        return returnMap;
		
	}
    
    @Override
	public void displayEditUser(HttpServletRequest request, Model model) {
    	String fAccountNumber = request.getParameter("fAccountNumber");
    	String pageNum = request.getParameter("pageNum");
        UserBean userBean = null;
//        Map<String, String> roleMap = new HashMap<String, String>();
//        List<ChannelBean> channelList = null;
        List<TNgcRole> roleMap1 = null;
    	List<TNgcFactory> factoryMap1 = null;
        try
        {
        	fAccountNumber = new String(fAccountNumber.getBytes("iso8859-1"), "UTF-8");
            userBean = this.userBeanMapper.selectUserByAccountNum(fAccountNumber);
            //channelList = channelBeanMapper.selectChannelList();
            //roleMap = DictionaryMethod.roleMap;
            //工厂和 角色需要转换
            String xx="",yy="";
            if(!userBean.getfUserLevel().equals("")){
             xx=TNgcRoleMapper.selectNamebyID(Integer.parseInt(userBean.getfUserLevel()));
            }
            if(!userBean.getFactory().equals("")){
             yy=TNgcFactoryMapper.selectByPrimaryKey(Integer.parseInt(userBean.getFactory())).getName();
            }
            userBean.setRoleName(xx);
            userBean.setFactoryname(yy);
            userBean.setfState(DictionaryMethod.useStatusMap.get(userBean.getfState()));
            roleMap1=(List<TNgcRole>) TNgcRoleMapper.selectAll();
            factoryMap1=(List<TNgcFactory>) TNgcFactoryMapper.selectAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       // model.addAttribute("channelList", channelList);
       // model.addAttribute("roleMap", roleMap);
        model.addAttribute("userBean", userBean);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("roleMap", roleMap1);
        model.addAttribute("factoryMap", factoryMap1);
		
	}
    
    @Override
	public Map<String, Object> executeEditUser(HttpServletRequest request,HttpSession session) {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        String msg = "";// 错误信息
        String resultCode = "1";
        UserBean loginUser = (UserBean) request.getSession().getAttribute("user");
//        String userCityName = request.getParameter("userCityName");
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userNumber = request.getParameter("userNumber");
        String userLevel = request.getParameter("rolename");
        String userState = request.getParameter("userState");
        String username = request.getParameter("username");
        String factory = request.getParameter("factory");
        String mail = request.getParameter("mail");
        String department = request.getParameter("department");
        String code=request.getParameter("code");

//        String userLevel = request.getParameter("userLevel");
//        String userCityCode = request.getParameter("citycode");
        UserBean userBean = new UserBean();
        try
        {
        	UserBean userBean2 = this.userBeanMapper.selectUserByUserNum(userNumber);
        	//校验手机号的唯一性或者不改手机号
        	if(userBean2 == null || userAccount.equals(userBean2.getfAccountNumber())){
//        		userBean.setfCity(userCityName);
                userBean.setfAccountNumber(userAccount);
                if(userPassword != null && !"".equals(userPassword)){
                	userBean.setfAccountPassword(MD5.Md5(userPassword));
                }
                userBean.setfUserNumber(userNumber);
//                if (!userLevel.equals(""))
//                {
//                    userBean.setfUserLevel(userLevel);
//                }
                if (!userState.equals(""))
                {
                    userBean.setfState(userState);
                }
                if (!userLevel.equals(""))
                {
                    userBean.setfUserLevel(userLevel);
                }
                if (!username.equals(""))
                {
                    userBean.setUsername(username);
                }
                if (!mail.equals(""))
                {
                    userBean.setMail(mail);
                }
                if (!factory.equals(""))
                {
                    userBean.setFactory(factory);
                }
                if (!department.equals(""))
                {
                    userBean.setDepartment(department);
                }
//                
                userBean.setfFatherNumber(loginUser.getfAccountNumber());
                String code1="";
                if(!code.equals("")){//code不为空默认为重新更改验证邮箱
                 code1= (String) session.getAttribute(mail);//session存存的验证码
                 session.setAttribute(mail,"");//取到session中
                }
                if(code.equals("")||code.equals(code1)){
                this.userBeanMapper.updateUser(userBean);
                resultCode = "0";	
                }else{
                	msg="验证码错误";
                	resultCode = "1";	
                }
               
                //用户的渠道变了则他创建(如果有权限的话)的合作券与活动券的渠道也会改变:
                //①改他创建合作券的父券的渠道id;②由于活动券没有渠道id或名称字段,其渠道就是他创建人的渠道,所以这里不用改
               // int d = this.parentCardBeanMapper.updateParentCardByCreatePeople(map);
               
        	}else{
        		msg = "该联系电话已经存在！";
        	}
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            msg = "更新用户信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		
    	
    	return returnMap;
	}
    
    @Override
	public Map<String, Object> executeRemoveUser(HttpServletRequest request) {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        String msg = "";// 错误信息
        String resultCode = "1";
        String fAccountNumber = request.getParameter("userAccount");
        try
        {
            this.userBeanMapper.deleteUserByAccountNum(fAccountNumber);
           // int d = this.userRoleBeanMapper.deleteByPrimaryKey(fAccountNumber);
           // if(!(d>0)){
            //	msg = "注销用户角色异常！";
           // }
            //d = this.userPermissionBeanMapper.deleteByfAccountNumber(fAccountNumber);
           // if(!(d>0)){
           // 	msg = "注销用户权限异常！";
            //}
            resultCode = "0";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            msg = "注销用户异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		return returnMap;
	}
    
    @Override
	public Map<String, Object> executeSelectUserByCondition(
			HttpServletRequest request) {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "";// 错误信息
        String resultCode = "1";
        String condition = request.getParameter("condition");
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        String fUserNumber = "";
        String fAccountNumber = "";
        List<UserBean> list = null;
        try
        {
            if (condition != null && !condition.equals("请输入用户名或手机号"))
            {
                if (condition.matches("^1[3|4|5|7|8][0-9]{9}$") || (condition.matches("^[0-9]{1,11}$")))
                {
                    fUserNumber = condition;
                }
                else
                {
                    fAccountNumber = condition;
                }
                map.put("fAccountNumber", fAccountNumber);
                map.put("fUserNumber", fUserNumber);
                Integer count = this.userBeanMapper.countUserBeanByCondition(map);
                System.out.println("page"+pageNum);
                System.out.println("fAccountNumber"+fAccountNumber);
                System.out.println("fUserNumber"+fUserNumber);
                System.out.println("222数量："+this.userBeanMapper.countUserBeanByCondition(map));
                
                
                Integer size = DictionaryMethod.size;
                Pagenation page = new Pagenation(pageNum, size, count);
                Integer start=(pageNum-1)*size;
                map.put("start", start);
                map.put("size", page.getSize());
                list = this.userBeanMapper.selectUserByConditionPage(map);
                for(UserBean userBean : list){
                	if(userBean != null){
                		
                		if("0".equals(userBean.getfUserLevel())){
                        	userBean.setChannelName("");//系统管理员没有渠道
                    		
                    	}
                		
                    	userBean.setRoleName(DictionaryMethod.roleMap.get(userBean.getfUserLevel()));
                    	userBean.setfState(DictionaryMethod.useStatusMap.get(userBean.getfState()));
                    }
                	
                }
                page.setList(list);
                returnMap.put("page", page);
                resultCode = "0";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            msg = "按条件查询用户异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		return returnMap;
	}
    
    @Override
	public void displayUserPermission(HttpServletRequest request, Model model) {
    	String fAccountNumber = request.getParameter("fAccountNumber");
    	String pageNum = request.getParameter("pageNum");
        List<UserPermissionBean> userPermissionList = null;
        try
        {
        	fAccountNumber = new String(fAccountNumber.getBytes("iso8859-1"), "UTF-8");
            userPermissionList = userPermissionBeanMapper.selectUserPermissionByAccountNum(fAccountNumber);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            model.addAttribute("fAccountNumber", fAccountNumber);
            model.addAttribute("list", userPermissionList);
            model.addAttribute("pageNum", pageNum);
        }
		
	}
    
    @Override
	public Map<String, Object> executeRedefineUserPermission(
			HttpServletRequest request) {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
        String msg = "";// 错误信息
        String resultCode = "1";
        String fAccountNumber = request.getParameter("fAccountNumber");
        String idstr = request.getParameter("idstr");
        String fUpdateTime = DateTimeUtil.getTodayChar17();
        String[] fPermissionCodeArray = idstr.split(",");
        Map<String, Object> removeMap = new HashMap<String, Object>();
        Map<String, Object> permissionMap = new HashMap<String, Object>();
        try
        {
            removeMap.put("fAccountNumber", fAccountNumber);
            removeMap.put("fUpdateTime", fUpdateTime);
            this.userPermissionBeanMapper.removeUserPermissionList(fAccountNumber);
            permissionMap.put("fAccountNumber", fAccountNumber);
            permissionMap.put("fPermissionCodeArray", fPermissionCodeArray);
            this.userPermissionBeanMapper.updateUserPermissionList(permissionMap);
            resultCode = "0";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            msg = "更改用户权限异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
		return returnMap;
	}

	@Override
	public String  UpdateRole(int roleid) {
		String msg="";
		List<UserBean> userbean=this.userBeanMapper.selectAccountByLevel(roleid);
		for(UserBean user:userbean){
			msg+=user.getfAccountNumber()+",";
			
		}
		if(msg.endsWith(",")){
			
			msg=msg.substring(0, msg.length()-1)+"账号的角色已被删除";
		}
		this.userBeanMapper.UpdateRoleByLevel(roleid);
		
		return msg;
	}
}
