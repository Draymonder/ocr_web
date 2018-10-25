package com.eqy.web.service.indexPage.iml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcAuthority;
import com.eqy.web.pojo.TNgcMenu;
import com.eqy.web.pojo.TNgcRole;
import com.eqy.web.service.indexPage.IndexPageService;
import com.eqy.web.service.role.IRoleService;
import com.eqy.web.service.usermanage.impl.UserManageServiceImpl;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.TNgcAuthorityMapper;
import com.eqy.web.dao.TNgcMenuMapper;
import com.eqy.web.dao.TNgcRoleMapper;
import com.eqy.web.dao.TemplateSearchMapper;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.utils.Halfyear;
@Service(" IndexPageService")
public class IndexPageServiceImp implements IndexPageService {
    @Autowired
    TemplateSearchMapper templateSearchMapper;
    
  
	@Override
	public Map<String, Object> displayIndexPageManage(HttpServletRequest request,HttpSession session) {
		String f_account_number=(String)request.getSession().getAttribute("User");//当前人账户
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String msg="";//错误信息;
        String resultCode="1";//执行结果
        String sj="";
        String monthMap=Halfyear.getHalfyear();
        String MonthDate="";//近6个月数据
        String xx[]=monthMap.split(",");
        Map<String, String> map=new HashMap<String, String>();
		try{
			sj+=templateSearchMapper.countMyAll(f_account_number)+","; //累计识别
			sj+=templateSearchMapper.countfinishAll(f_account_number)+",";//错误纠正（即是否异常为是的情况）
			sj+=templateSearchMapper.countwaitAll(f_account_number);//待识别
			for(String month:xx){
				map.put("f_account_number",f_account_number);
				map.put("month",month);
				MonthDate+=templateSearchMapper.countMonth(map)+",";
			}
			if(MonthDate.endsWith(",")){
				MonthDate=MonthDate.substring(0, MonthDate.length()-1);
			}

			 resultCode="0";
		} catch (Exception e) {
			e.printStackTrace();
			msg="报表查询有错误！！！";
		} finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
            returnMap.put("sj", sj);
            returnMap.put("MonthDate", MonthDate);
            returnMap.put("monthMap", monthMap);
        }
		return returnMap;
	}
	

	
}
