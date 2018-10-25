package com.eqy.web.dao;

import com.eqy.web.pojo.TbMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbMapMapper {


	Map<String,String> findAll();
	
	
  
}