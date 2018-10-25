package com.eqy.web.dao;

import java.util.List;
import java.util.Map;

import com.eqy.web.pojo.TNgcData;


public interface TemplateSearchMapper {
    

    int deleteByPrimaryKey(Integer id);

    int insert(TNgcData record);

    int insertSelective(TNgcData record);

   
    TNgcData selectByPrimaryKey(Integer id);

    
    int updateByPrimaryKeySelective(TNgcData record);

    int updateByPrimaryKey(TNgcData record);
    
    void updateTemplateName(Map<String, Object> map);//修改模板名称时，把数据表模板名称更新
    
    int countBean(Map<String, Object> map);//  有/无条件查询数据数量
    
    List<TNgcData> selectTaskListByPage(Map<String, Object> map);//有/无条件分页展示

    void updateData(Map<String, Object> map);
    
    String selectColumnByA(Map<String, String> map);
    
    void updateDataByID(Map<String, Object> map);
    void updateflag1(Map<String, Object> map);
    void updateflag2(Map<String, Object> map);
    void insertBasicInfo(Map<String, Object> map);

    int selectIDbyFileNumber(String fileNumber);
    
    
    int countMyAll(String f_account_number);//首页 找登陆者识别文件总数量
    int countfinishAll(String f_account_number);//首页 找登陆者已完成数量
    int countwaitAll(String f_account_number);//首页 找登陆者待办数量

    int countFile(String fileNumber);

    void updateCreateTime(Map<String, Object> map);
                          
    int countMonth(Map<String, String> map);//查询登陆者莫个月的识别文件刷量


}