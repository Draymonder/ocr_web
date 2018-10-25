package com.eqy.web.dao;


import java.util.Map;


public interface TbCommonMapMapper {
	Map<String,String> findAll(int  commonTemplate_id);

	void insertMap(Map<String, Object> map);
	void updateMap(Map<String, Object> map);
	
    String  findNoteByOne(Map<String, Object> map);//查找注解
}