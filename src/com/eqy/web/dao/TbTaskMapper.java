package com.eqy.web.dao;

import java.util.List;
import java.util.Map;

import com.eqy.web.pojo.TbTask;


public interface TbTaskMapper {
   

    int countTaskBean(Map<String, Object> map);
    
    List<TbTask> selectTaskListByPage(Map<String, Object> map);
    
    void InsertTask(TbTask task);
    
    void UpdateTaskStatusAndTime(Map<String, Object> map);
    
    int selectMaxID();

}