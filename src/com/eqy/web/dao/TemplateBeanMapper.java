package com.eqy.web.dao;

import com.eqy.web.pojo.TemplateBean;

import java.util.List;
import java.util.Map;



public interface TemplateBeanMapper {

    /**
     * @Title updateCoordiate
     * @Description 更新坐标
     * @param templateMap--fieldName tempalteName pageNo coordinateValue
     */
    void updateCoordinate(Map<String,Object> templateMap);

    /**
     * @Title insertTemplate
     * @Description 插入新的一行
     * @param templateMap--templateName
     */
    void insertByTemplateNameANDPageNo(Map<String,Object> templateMap);

    /**
     * @Title 根据模板名和页码查询全部坐标信息
     * @param templateMap
     * @return Bean对象
     */
    TemplateBean findByTemplateNameANDPageNo(Map<String,Object> templateMap);

    /**
     * @Title queryCoordinate
     * @param templateMap
     * @return 子图坐标
     */
    String queryCoordinate(Map<String,Object> templateMap);

    /**
     * @Title findAllfieldName
     * @return 所有待更新字段名
     */
    List<String> findAllFieldName();

    /**
     * @Title findAllTemplateName
     * @Description 查询所有模板名
     * @return 所有模板名称
     */
    List<String> findAllTemplateName();

    /**
     * @Title UpdateTemplateName
     * @Description 自定义模板名
     * @param
     * @param
     * @return
     */
    int updateTemplateName(Map<String,Object> map);

    /**
     * @Title findByTemplateName
     * @Description 根据模板名和指定页码模糊查询
     * @param map
     * @return
     */
    List<TemplateBean> findBasicTemplateInfoByConditionPage(Map<String, Object> map);

    /**
     * @Title findBasicTemplateInfo
     * @Description 根据指定页码查询TemplateBean
     * @return  List<TemplateBean>
     */
    List<TemplateBean> findBasicTemplateInfoByPage(Map<String, Object> map);

    /**
     * @Description 根据模板名和指定页码查询TemplateBean数量
     * @param map
     * @return
     */
    int countByTemplateNameANDPageNo(Map<String, Object> map);

    /**
     * @Description 查询TemplateBean数量(模板名不重复)
     * @return
     */
    int countDistinctTemplateBean();

    /**
     * @Description 查询符合条件的TemplateBean数量(模板名不重复)
     * @return
     */
    int countDistinctTemplateBeanByCondition(Map<String, Object> map);
    
    /**
     * @Description 根据模板名称查询到模板数据   add by ssw
     * @return
     */
    Map<String,Object>  findCoordinate(Map<String, Object> map);

    String findCoordinateByOne(Map<String, Object> map);
    
    
    int  countBeanByname(String templatename);
    
    void removeTemplate(String templatename);

}