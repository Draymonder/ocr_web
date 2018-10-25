package com.eqy.web.dao;

import com.eqy.web.pojo.TbCommonTask;
import java.util.List;
import java.util.Map;


public interface TbCommonTaskMapper {
	     int countTaskBean(Map<String, Object> map);
	    
	    List<TbCommonTask> selectTaskListByPage(Map<String, Object> map);
	    
	    void InsertTask(TbCommonTask task);
	    
	    void UpdateTaskStatusAndTime(Map<String, Object> map);
	    
	    int selectMaxID();
}