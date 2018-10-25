package com.eqy.web.dao;

import com.eqy.web.pojo.TCommonRuleMapper;
import com.eqy.web.pojo.TRuleMapper;
import com.eqy.web.pojo.TbCommonRule;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TCommonRuleMapperMapper {

	    int insert(TCommonRuleMapper TCommonRuleMapper);
	    List<String> selectByRuleID(int id);
	    void deleteAllByRuleId(int ruleId);
	    List<Map<String, Object>> selectByFieldNameANDTemplateID(Map<String, Object> map);

}