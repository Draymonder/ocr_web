package com.eqy.utils;

import com.eqy.tools.SpringUtil;
import com.eqy.web.service.task.ICommonTaskService;
import com.eqy.web.service.task.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengkang
 * 2018/8/1 上午9:29
 */
public class MultipartFileHelper1 implements Runnable{
    private static Logger log = LoggerFactory.getLogger(MultipartFileHelper1.class);

    private MultipartFile[] files;
    private String file_path;
    private String account;
    private int templateID;
    private String traineddata;

    public MultipartFileHelper1(){}
    public MultipartFileHelper1(MultipartFile[] files, int templateID,String traineddata,
                               String file_path,String account){
        this.files = files;
        this.account = account;
        this.file_path = file_path;
        this.templateID = templateID;
        this.traineddata = traineddata;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        MultipartFileHelper1.log = log;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    public String getTraineddata() {
        return traineddata;
    }

    public void setTraineddata(String traineddata) {
        this.traineddata = traineddata;
    }

    @Override
    public void run() {
        final ICommonTaskService iCommonTaskService = (ICommonTaskService) SpringUtil.getBean("CommonTaskService");
        final int task_id = iCommonTaskService.InsertTask(account);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for( MultipartFile file : files){
            if(!file.isEmpty()){
                final String path = file_path + file.getOriginalFilename();//文件路径（全）
                try {
                    FileUtil.uploadFile(file.getBytes(), file_path, file.getOriginalFilename());//tiff上传至服务器
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            iCommonTaskService.common_taskManage(path, account, task_id, templateID, traineddata);

                        }
                    });
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        pool.shutdown();
        while(!pool.isTerminated()){

        }
        //log.error("完成！");
        iCommonTaskService.UpdateTaskStatus(task_id);
    }
}
