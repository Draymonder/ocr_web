package com.eqy.web.service.mail.imp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eqy.web.dao.UserBeanMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.web.pojo.TNgcMail;
import com.eqy.web.service.mail.IMailService;
import com.eqy.mail.MailTest;
import com.eqy.web.dao.TNgcMailMapper;

@Service("mailService")
public class MailServiceImp implements IMailService {
    @Autowired
    TNgcMailMapper TNgcMailMapper;

    @Autowired
    UserBeanMapper userBeanMapper;

	@Override
	public int insert(TNgcMail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TNgcMail record) {
		
		return 0;
	}

	

	
	

	@Override
	public TNgcMail selectOneByOne() {
		TNgcMail mail=TNgcMailMapper.selectOneByOne();
		return mail;
	}

	@Override
	public void update(TNgcMail mail) {
		
		TNgcMailMapper.update(mail);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendemail(String msg, String account) {
		TNgcMail TNgcMail=TNgcMailMapper.selectOneByOne();
    	String myemailaccount=TNgcMail.getMyemailaccount();//服务器账号
    	String myemailpassword=TNgcMail.getMyemailpassword();//服务器密码
    	String myemailsmtphost=TNgcMail.getMyemailsmtphost();//smtp地址
    	String smtpport=TNgcMail.getSmtpport();//端口号
    
          try {
       	   //发送邮件
              String mail= userBeanMapper.selectUserByAccountNum(account).getMail();
              MailTest.sendEmail(myemailaccount, myemailpassword,myemailsmtphost, smtpport,mail, msg);
       		
       		
   		   } catch (Exception e)
           {
               e.printStackTrace();
               
           }
           
		
	}

	@Override
	public void displayMailManage(HttpServletRequest request, Model model) {
		TNgcMail  mail=null; 
        try {
             mail =  TNgcMailMapper.selectOneByOne();
             model.addAttribute("mail", mail);			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public Map<String,Object> executeEditMail(HttpServletRequest request) {
		String msg = "";// 错误信息 
        String resultCode = "";// 执行结果  
        String mailaccount = request.getParameter("mailaccount");//邮箱服务器账号 
        String mailpassword = request.getParameter("mailpassword");//邮箱服务器密码
        String mailsmtphost = request.getParameter("mailsmtphost");//smtp地址 
        String mailsmtpport =  request.getParameter("mailsmtpport");//端口号
 
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		if(!mailaccount.equals("")){
    			TNgcMail mail =new TNgcMail();
    			mail.setMyemailaccount(mailaccount);
    			mail.setMyemailpassword(mailpassword);
    			mail.setMyemailsmtphost(mailsmtphost);
    			mail.setSmtpport(mailsmtpport);
    			resultCode = "0";
    		    this.TNgcMailMapper.update(mail);
       		 
    		} else{
    			msg = "更新服务器邮箱信息异常11";
    			resultCode = "1";
    		}
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "更新服务器邮箱信息异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        } 
        return returnMap;
	}

	@Override
	public Map<String, Object> executeMailTest(HttpServletRequest request,HttpSession session) {
		    String resultCode="1";//0是正确，1是错误
	        String msg="";
	    	String  receivemail=request.getParameter("mail");//页面需要验证的邮箱地址,即接收邮箱
	    	TNgcMail TNgcMail=TNgcMailMapper.selectOneByOne();
	    	String myemailaccount=TNgcMail.getMyemailaccount();//服务器账号
	    	String myemailpassword=TNgcMail.getMyemailpassword();//服务器密码
	    	String myemailsmtphost=TNgcMail.getMyemailsmtphost();//smtp地址
	    	String smtpport=TNgcMail.getSmtpport();//端口号
	    	
	    	 MailTest test=new MailTest();
	         int a=(int)((Math.random()*9+1)*100000);//随机6位数验证码
	         String aa=a+"";
	         Map<String, Object> returnMap = new HashMap<String, Object>();
	       try {
	    	   //发送邮件
	    		test.Test(myemailaccount, myemailpassword,myemailsmtphost, smtpport, receivemail, aa);
	    		resultCode="0";
			} catch (Exception e)
	        {
	            e.printStackTrace();
	            resultCode="1";
	            msg = "邮箱验证发送验证码异常,请检查邮箱服务器配置";
	        }
	        finally
	        {   returnMap.put("resultCode", resultCode);
	            returnMap.put("msg", msg);
	            session.setAttribute(receivemail,aa);
	        }
	        return returnMap;
	}


}
