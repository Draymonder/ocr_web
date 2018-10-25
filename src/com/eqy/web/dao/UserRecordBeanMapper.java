package com.eqy.web.dao;

import java.util.List;
import java.util.Map;

import com.eqy.web.pojo.UserRecordBean;


public interface UserRecordBeanMapper {
	
	int deleteByPrimaryKey(String recordId);

    int insert(UserRecordBean record);

    int insertSelective(UserRecordBean record);

    List<UserRecordBean> selectByCondition(Map<String,String> map);

    int updateByPrimaryKeySelective(UserRecordBean record);

    int updateByPrimaryKey(UserRecordBean record);
}