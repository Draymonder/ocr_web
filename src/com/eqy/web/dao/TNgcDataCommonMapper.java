package com.eqy.web.dao;


import com.eqy.web.pojo.TNgcDataCommon;
import java.util.List;
import java.util.Map;



public interface TNgcDataCommonMapper {
	
	TNgcDataCommon selectByPrimaryKey(Integer id);
 
    void updateTemplateName(Map<String, Object> map);//修改模板名称时，把数据表模板名称更新
    
    int countBean(Map<String, Object> map);//  有/无条件查询数据数量
    
    List<TNgcDataCommon> selectTaskListByPage(Map<String, Object> map);//有/无条件分页展示

    void updateData(Map<String, Object> map);
    
    String selectColumnByA(Map<String, String> map);
    
    void updateDataByID(Map<String, Object> map);
    void updateflag1(Map<String, Object> map);
    void updateflag2(Map<String, Object> map);
    void insertBasicInfo(Map<String, Object> map);

    int selectIDbyFileNumber(String fileNumber);

    int countFile(String fileNumber);

    void updateCreateTime(Map<String, Object> map);

    
    

	
}