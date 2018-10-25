package com.eqy.web.controller.traineddata;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eqy.web.dao.TbTraineddataMapper;
import com.eqy.web.pojo.TNgcMail;
import com.eqy.web.pojo.TbTraineddata;
import com.eqy.web.service.mail.IMailService;
import com.eqy.web.service.mail.imp.MailServiceImp;
import com.eqy.constants.SystemConstants;
import com.eqy.mail.*;
import com.eqy.utils.FileUtil;
import com.eqy.web.service.traineddata.*;

@Controller
@RequestMapping(value = "/TraineddataManage")
public class TraineddataController
{
	@Value( "${Tessdata_PATH}" )
	private String Tessdata_PATH;
	
    @Autowired
     ITraineddataService  ITraineddataService ;//ssw add 服务层
    
    @Autowired
    TbTraineddataMapper TbTraineddataMapper;
    

    
    /**
     * @Title showmailManage
     * @Description 展示邮件服务器信息界面
     * @param request
     */
    @RequestMapping(value = "/showDataManage")
    public String showDataManage(HttpServletRequest request, Model model)
    {    
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	ITraineddataService.displayMailManage(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "traineddata_manage/traineddata";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody String multipleTaskOperation(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException  {
		String result = SystemConstants.WEB_SUCCESS;//0
		String file_path =Tessdata_PATH;
		if(files.length != 0){
		
			for(final MultipartFile f: files) {
				if (!f.isEmpty()) {		
					try {
						TbTraineddata t=new TbTraineddata();
						t.setTraineddata(f.getOriginalFilename());
						FileUtil.uploadFile(f.getBytes(), file_path, f.getOriginalFilename());//tiff上传至服务器
						TbTraineddataMapper.insertSelective(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//创建新线程,负责识别任务的执行
					
				} else {
					result = SystemConstants.WEB_FAILURE;
				}
			}
		}
		return result;
	}
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
   	public @ResponseBody String newTraineddate(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException  {
   		String result = SystemConstants.WEB_SUCCESS;//0
   		String file_path = Tessdata_PATH;
   		if(files.length != 0){
   		
   			for(final MultipartFile f: files) {
   				if (!f.isEmpty()) {
   					try {
   						//TbTraineddata t=new TbTraineddata();
   						//t.setTraineddata(f.getOriginalFilename());
   						FileUtil.uploadFile(f.getBytes(), file_path, f.getOriginalFilename());//tiff上传至服务器
   						//TbTraineddataMapper.insertSelective(t);
   					} catch (Exception e) {
   						e.printStackTrace();
   					}
   					//创建新线程,负责识别任务的执行
   					
   				} else {
   					result = SystemConstants.WEB_FAILURE;
   				}
   			}
   		}
   		return result;
   	}
    
}
