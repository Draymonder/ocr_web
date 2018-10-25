package com.eqy.web.controller.IndexPage;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eqy.web.service.indexPage.*;

@Controller
@RequestMapping(value = "/IndexPageManage")
public class IndexPageController
{
    @Autowired
    private IndexPageService IndexPageService;
    

    @RequestMapping(value = "/countBean")
    @ResponseBody
    public Map<String, Object> removeUser(HttpServletRequest request,HttpSession session)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = IndexPageService.displayIndexPageManage(request,session);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    
  
}
