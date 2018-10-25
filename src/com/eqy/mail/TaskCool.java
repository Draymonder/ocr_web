package com.eqy.mail;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class TaskCool {
//在spring——mvc 里面设置了扫描
    // 每五秒执行一次
    @Scheduled(cron = "0 0/2 * * * ?")
    public void TaskJob() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String dz="/"+df.format(new Date());
        System.out.println("测试结果一");
        FTPTest.FTPDownload("10.1.8.23", 2121, "Administrator", "223315ssw",dz,"E:\\FTPDownload");
        
       
       
    }
}