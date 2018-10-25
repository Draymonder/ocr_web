package com.eqy.mail;

import java.io.File;
public class Testxianchneg {
 public static void main(String[] args) throws Exception {
  //递归显示C盘下所有文件夹及其中文件
  File root = new File("E:\\FTPTest");
  showAllFiles(root);
 }
 
 final static void showAllFiles(File dir) throws Exception{
  File[] fs = dir.listFiles();
  System.out.println("长度："+fs.length);
  for(int i=0; i<fs.length; i++){
   System.out.println("根路径"+fs[i].getAbsolutePath());
   if(fs[i].isDirectory()){
    try{
     showAllFiles(fs[i]);
    }catch(Exception e){}
   }
  }
 }
}


