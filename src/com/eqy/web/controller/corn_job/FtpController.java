package com.eqy.web.controller.corn_job;



import com.eqy.constants.SystemConstants;
import com.eqy.web.service.task.ITaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eqy.tools.FileTransfer;


@Component
public class FtpController {
	
	@Autowired
	ITaskService iTaskService;
	
	@Value("${remotePath}")
	public   String remotePath; //目的地文件
	@Value("${finishPath}")
	public   String  finishPath;  //finish文件
	@Value("${localPath}")
	public   String localPath;  //目的地文件转移到tomcat地址下/uploads/tiff下，存放在日期文件夹下

	@Scheduled(cron = "0 0/30 * * * ?")
	public void gotoTask(){

		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        //System.out.println("测试结果二");
        //System.out.println("url"+url);
       // int port=Integer.parseInt(port1);

        try{
			String result = FileTransfer.copyFiles(remotePath,finishPath,localPath);
        	if(result.indexOf(".tif")>=0||result.indexOf(".TIF")>=0){//没有更新的文件要识别
                //创建任务 把任务ID和文件路径集合发送到，然后调用识别接口
       			System.out.println("新建任务");
       			int task_id = iTaskService.InsertTask();
       			String[] Array = result.split(";");
       			for(String path : Array){
       				iTaskService.taskManage(path,SystemConstants.FTP_TASK_CREATOR,task_id);
       			}
                iTaskService.UpdateTaskStatus(task_id);//已完成
       		}else{
       			System.out.println("文件名集合"+result);


       		}
        }catch (Exception e) {
			e.printStackTrace();
		} 



	}
}
