package com.eqy.web.service.indexPage;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;


public interface IndexPageService {

    /**
     * @Title: displayRoleManage
     * @Description: 
     * @param @param id
     * @param @return 展示全部角色
     * @return List<UserBean> 返回类型
     * @throws
     */
	Map<String, Object>  displayIndexPageManage(HttpServletRequest request,HttpSession session);
    

    
}
