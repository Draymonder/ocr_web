package com.eqy.web.dao;

import com.eqy.web.pojo.TbCommonTask;
import com.eqy.web.pojo.TbTraineddata;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbTraineddataMapper {



	int countBean(Map<String,Object> map);
	List<TbTraineddata> selectListByPage(Map<String,Object> map);
    int insertSelective(TbTraineddata record);

    List<TbTraineddata> selectAllBean();



   
}