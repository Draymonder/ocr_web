package com.eqy.web.dao;

import com.eqy.web.pojo.TRuleMapper;
import java.util.List;
import java.util.Map;


public interface TRuleMapperMapper {


    int insert(TRuleMapper record);
    List<String> selectByRuleID(int id);
    void deleteAllByRuleId(int ruleId);
    List<Map<String, Object>> selectByFieldname(String a);
    
  

   
}