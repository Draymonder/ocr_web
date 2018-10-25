package com.eqy.web.controller;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.web.pojo.UserBean;
import com.eqy.web.service.user.IUserService;
import com.eqy.utils.MD5;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author:  戴光浩
 * @date: 2018-5-11 上午11:36:12
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController
{    @Autowired
	 IUserService userSercice;
    /**
     * 跳转登录页面
     * 
     * @return
     * @see
     */
    @RequestMapping(value = "")
    public String login()
    {
        return "login";
    }
    /**
     * 方法描述: 用户登录验证 业务逻辑: 采用shiro自定义ShiroDbRealm验证，已用户账号代替角色做用户权限验证
     * 
     * @param request
     * @return
     * @see
     */
    @RequestMapping(value = "/into")
    @ResponseBody
    public Map<String, Object> into(HttpServletRequest request,HttpSession session)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "登录失败!";// 错误信息
        String resultCode = "1";// 登录标识，默认失败
        try
        {   
            String userAccount = request.getParameter("userAccount");// 用户账号
            String userPassword = request.getParameter("userPassword");// 密码
            
            userPassword=MD5.Md5(userPassword);//md5明文加密成密文去登入
            
            // 用户登录验证
            UsernamePasswordToken token = new UsernamePasswordToken(userAccount, userPassword);
            token.setRememberMe(true);
            // 获取验证后对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
           
            // 登录验证判断
            if (subject.isAuthenticated())
            {
                // 登录成功后查询用户基本信息并放入缓存中
                UserBean user = new UserBean();
                // 转换用户角色
                user.setRoleName(DictionaryMethod.roleMap.get(user.getfUserLevel()));
                // 用户基本信息放入缓存
                user.setfAccountNumber(userAccount);
                user.setFactoryname(userSercice.selectFactoryName(userAccount));
                user.setUsername(userSercice.selectUserByAccountNum(userAccount).getUsername());
                request.getSession().setAttribute("user", user);
                int sl=userSercice.selectAutority(userAccount);//查询该账号是否有管理员查询权限 0是无,1是有
                request.getSession().setAttribute("SearchAuthority", sl);
                resultCode = "0";
                session.setAttribute("User", userAccount);
            }
            else
            {
                msg = "登录验证失败!";
            }
        }
        catch (IncorrectCredentialsException e)
        {
            msg = "登录密码错误!";
        }
        catch (UnknownAccountException e)
        {
            msg = "帐号不存在!";
        }
        catch (DisabledAccountException e)
        {
            msg = "帐号已被禁用!";
        }
        catch (Exception e)
        {
            msg = "登录异常!";
            e.printStackTrace();
        }
        finally
        {
            map.put("resultCode", resultCode);
            map.put("msg", msg);
        }
        return map;
    }
    /**
     * 方法描述: 用户登出操作 业务逻辑: 用户登出同时清空session，忘记用户登录信息
     * 
     * @param request
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/out")
    public String out(HttpServletRequest request)
    {
        // 获取缓存中信息
        Enumeration<String> em = request.getSession().getAttributeNames();
        // 清空缓存信息
        while (em.hasMoreElements())
        {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        // 清空用户缓存信息
        request.getSession().removeAttribute("user");
        SecurityUtils.getSubject().logout();// 用户登出
        return "redirect:/";
    }
    /**
     * 方法描述: 跳转至系统主页面 业务逻辑: 跳转至主页面同时查询卡券数量
     * 
     * @param type
     * @param request
     * @param model
     * @return
     * @see
     */
    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request, Model model)
    {
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        String type = request.getParameter("type");
        // 将返回标志为存入静态变量,页面没有传入就从静态池中取出
        if (null == type || "".equals(type))
        {
            type = DictionaryMethod.staticType;
        }
        else
        {
            DictionaryMethod.staticType = type;
        }
        model.addAttribute("type", type);
        return "main";
    }
}
