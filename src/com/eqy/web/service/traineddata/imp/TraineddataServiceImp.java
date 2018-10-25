package com.eqy.web.service.traineddata.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.web.pojo.CommonTemplateBean;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TNgcMail;
import com.eqy.web.pojo.TbCommonTask;
import com.eqy.web.pojo.TbTraineddata;
import com.eqy.web.service.mail.IMailService;
import com.eqy.web.service.traineddata.ITraineddataService;
import com.eqy.mail.MailTest;
import com.eqy.utils.DateTransfer;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.TNgcMailMapper;
import com.eqy.web.dao.TbTraineddataMapper;

@Service("traineddataService")
public class TraineddataServiceImp implements ITraineddataService {
    @Autowired
    TbTraineddataMapper TbTraineddataMapper;

	@Override
	public void displayMailManage(HttpServletRequest request, Model model) {
		 try
	        {

	            Map<String, Object> map = new HashMap<>();
	            Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
	            Integer size = DictionaryMethod.size;
	            Integer count = this.TbTraineddataMapper.countBean(map);
	            Pagenation page = new Pagenation(pageNum, size, count);
	            map.put("size", page.getSize());//每页行数
	            map.put("start", page.getStartRow());//重第一行开始,系统size为10
	            List<TbTraineddata> list = this.TbTraineddataMapper.selectListByPage(map);
	           // List<CommonTemplateBean> list1=commonTemplateBeanMapper.selectAll();
	            String result="";
	            for(TbTraineddata data:list){
	               result+=data.getTraineddata()+";";

	            }
	            if(result.endsWith(";")){
	            	result=result.substring(0, result.length()-1);
	            }
	            page.setList(list);
	            model.addAttribute("page", page);
	            model.addAttribute("result",result);
	           // model.addAttribute("list1", list1);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}

	
}
