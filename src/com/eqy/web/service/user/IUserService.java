package com.eqy.web.service.user;

/**
 * package com.eqy.web.service.user;
 */
import java.util.List;
import com.eqy.web.pojo.PermissionBean;
import com.eqy.web.pojo.UserBean;


public interface IUserService
{
	 	
	   
	    UserBean selectUserByAccountNum(String fAccountNumber);

		List<PermissionBean> selectPermListByAccount(String fAccountNumber);
		
		String selectFactoryName(String fAccountNumber);//登录显示个人信息
		
		int selectAutority(String fAccountNumber);//查询信息权限
}
