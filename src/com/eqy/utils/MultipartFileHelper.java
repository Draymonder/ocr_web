package com.eqy.utils;

import com.eqy.tools.SpringUtil;
import com.eqy.web.service.task.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengkang
 * 2018/7/31 下午4:27
 */
public class MultipartFileHelper implements Runnable {
    private static Logger log = LoggerFactory.getLogger(MultipartFileHelper.class);

    private MultipartFile[] files;
    private String file_path;

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        MultipartFileHelper.log = log;
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

    private String account;
    public MultipartFileHelper(){};
    public MultipartFileHelper(MultipartFile[] files,
                        String file_path,String account){
        this.files = files;
        this.account = account;
        this.file_path = file_path;
    }
    @Override
    public void run() {
        final ITaskService iTaskService = (ITaskService) SpringUtil.getBean("taskService");
        final int task_id = iTaskService.InsertTask(account);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for( MultipartFile file : files){
            if(!file.isEmpty()){
                final String path = file_path + file.getOriginalFilename();
                try {
                    FileUtil.uploadFile(file.getBytes(), file_path, file.getOriginalFilename());//tiff上传至服务器
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                iTaskService.taskManage(path,account,task_id);
                            } catch(IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
        //该线程可再用来监听所有任务到结束
        pool.shutdown();
        while(!pool.isTerminated()){

        }
        log.error("完成！");
        iTaskService.UpdateTaskStatus(task_id);//插入任务
    }
}
