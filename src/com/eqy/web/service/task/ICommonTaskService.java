package com.eqy.web.service.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.eqy.web.pojo.TbTask;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by chengkang
 * 2018/6/21 下午7:54
 */
public interface ICommonTaskService {
   void common_taskManage(String path, String account, int task_id, int templateID, String traineddata);
   
   void showTaskListManage(HttpServletRequest request, Model model);
   
   Map<String,Object> executeQueryTaskListByCondition(HttpServletRequest request);

   //int InsertTask();//定时新增任务
   
   int InsertTask(String account);//手工新增任务

   void UpdateTaskStatus(int task_id);
}
