package com.eqy.web.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.eqy.web.pojo.CommonTemplateBean;

/**
 * Created by chengkang
 * 2018/7/19 下午5:16
 */
public interface CommonTemplateBeanMapper {
    /**
     * @Title findAllfieldName
     * @return 所有待更新字段名
     */
    List<String> findAllFieldName();
    
    int countDistinctTemplateBean(Map<String,Object> map);
    
    List<CommonTemplateBean>  findTemplateInfoByPage(Map<String,Object> map);
    
    CommonTemplateBean findByTemplateName(Map<String,Object> map);
    
    int countBeanByName(String templateName);
    
    void updateTemplateName(Map<String,Object> map);
    
    void  removeTemplate(String templateName);
    
    String selectByKey(int id);
    
    List<CommonTemplateBean> selectAll();

    String findCoordinateByOne(Map<String, Object> map);//查找四维坐标

    Map<String, Object> findAllCoordinate(String templateName);

    void insertBasicInfo(Map<String, Object> map);//插入基本信息

    void updateCommonCoordinate(Map<String, Object> map);

    int findIDByTemplateName(String s);//查ID
    
    int selectBYTemplateName(String TemplateName);//add by ssw 通过模板名称找到通用模板id

    String findTemplateNameByID(int id);
    
}
