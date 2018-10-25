package com.eqy.web.dao;

import com.eqy.web.pojo.TbCommonDataMark;
import java.util.List;
import java.util.Map;

public interface TbCommonDataMarkMapper {

	int deleteByPrimaryKey(Integer id);

	int insertSelective(TbCommonDataMark record);

	TbCommonDataMark selectByPrimaryKey(Integer id);
	 
	int updateByPrimaryKeySelective(TbCommonDataMark record);
	    
	String  findDescription(Map<String, String> map);

	String  findflag(Map<String, String> map);


	int countByDataId(int id);//是否被提示规则改

	int countDataMark(int id);//是否被规则修改

	void deleteDataMark(int id);//删除所有相关数据id的规则描述
}