package com.eqy.web.service.factory;

import com.eqy.web.pojo.TNgcFactory;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;


public interface IFactoryService {
     /**
      * @Title: displayRoleManage
      * @Description: 
      * @param @param id
      * @param @return 展示全部角色
      * @return List<UserBean> 返回类型
      * @throws
      */
     void  displayFactoryManage(HttpServletRequest request,Model model);
     
     /**
      * @Title: executeCreateFactory
      * @Description: 展示创建工厂页面
      * @param @param id
      * @param @return 
      * @return List<UserBean> 返回类型
      * @throws
      */
     Map<String,Object> executeCreateFactory(HttpServletRequest request);
     
     
     /**
      * @Title: executeEditFactory
      * @Description: 展示编辑工厂页面
      * @param @param id
      * @param @return 
      * @return List<UserBean> 返回类型
      * @throws
      */
     void displayEditFactory(HttpServletRequest request,Model model);
     
     /**
      * @Title: executeEditFactory
      * @Description: 编辑工厂页面
      * @param @param id
      * @param @return 
      * @return List<UserBean> 返回类型
      * @throws
      */
     Map<String,Object> executeEditFactory(HttpServletRequest request);
     
     /**
      * @Title: executeRemovefactory
      * @Description: 编辑工厂页面
      * @param @param id
      * @param @return 
      * @return List<UserBean> 返回类型
      * @throws
      */
     Map<String,Object> executeRemovefactory(HttpServletRequest request);
     
     /**
      * @Title: executeQueryfactoryByCondition
      * @Description: 按条件查询显示结果
      * @param @param id
      * @param @return 
      * @return List<UserBean> 返回类型
      * @throws
      */
     Map<String,Object> executeQueryfactoryByCondition(HttpServletRequest request);
     
}