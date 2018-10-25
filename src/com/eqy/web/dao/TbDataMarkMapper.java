package com.eqy.web.dao;

import com.eqy.web.pojo.TbDataMark;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbDataMarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TbDataMark record);

    TbDataMark selectByPrimaryKey(Integer id);
 
    int updateByPrimaryKeySelective(TbDataMark record);
    
    String  findDescription(Map<String, String> map);

    String  findflag(Map<String, String> map);


    int countByDataId(int id);//是否被提示规则改

    int countDataMark(int id);//是否被规则修改

    void deleteDataMark(int id);//删除所有相关数据id的规则描述
}