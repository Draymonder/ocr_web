package com.eqy.web.controller.corn_job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eqy.tools.FileClean;

@Component
public class FileCleanController {
	
	@Value("${TempPath}") 
	public   String TempPath;   //每3月一删除的截取图片
	@Value("${CommonPath}") 
	public   String CommonPath;  //通用模板识别图片(原图）
	@Value("${TiFPath}") 
	public   String TiFPath;  //计量报告模板识别图片原图（手动上传和自动获取）
	
	 /**
     * 删除指定文件夹下所有文件(不包括子文件夹）
     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save"
     * 
     */
	@Scheduled(cron ="0 45 23 * * ?")  //每天删除temp上传任务的分页图片或者制作模板的分页图片
	public void CleanFilesA(){
	    System.out.println("准备中.....");
	    //System.out.println(TempPath);
		System.out.println("开始清理文件"+TempPath);
        try{
        	
        	boolean f=FileClean.delAllFileA(TempPath);
        	System.out.println(f);
        }catch (Exception e) {
			e.printStackTrace();
		} 


	}
	
	
	 /**
     * 删除指定文件夹下子文件夹及文件
     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save" 每年3,6,9,12月份28号23时删除
     * 
     */
	@Scheduled(cron ="0 0 23 28 3,6,9,12 ?")//每年3,6,9,12月份28号23时删除temp上传任务截取的一个个小图片
	public void CleanFilesB(){
	    System.out.println("月度清理文件夹准备中.....");
	    //System.out.println(TempPath);
		System.out.println("开始清理文件"+TempPath);
        try{
        	
        	boolean f=FileClean.delAllFileB(TempPath);
        	System.out.println(f);
        }catch (Exception e) {
			e.printStackTrace();
		} 


	}
	
	 /**
     * 删除指定文件夹下子文件夹及文件
     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save" 每年3,6,9,12月份28号23时删除
     * 
     */
	@Scheduled(cron ="0 0 23 28 3,6,9,12 ?")//每月最后一天才删除common和tiff上传的识别数据（包括tif自动抓取的图片）
	public void CleanFiles(){
	    System.out.println("识别图片文件夹准备中.....");
	    //System.out.println(TempPath);
		System.out.println("开始清理识别文件1:"+CommonPath);
		System.out.println("开始清理识别文件2:"+TiFPath);
		boolean f=false,f1=false,f2=false;
		
        try{
        	//暂时先不删除，确定后继续
        	 //f=FileClean.delAllFileA(CommonPath);
        	 //f1=FileClean.delAllFileA(TiFPath);
        	// f2=FileClean.delAllFileB(TiFPath);
        	System.out.println(f);
        	System.out.println(f1);
        	System.out.println(f2);
        }catch (Exception e) {
			e.printStackTrace();
		} 


	}
	
	
	
}
