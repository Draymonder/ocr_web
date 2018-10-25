package com.eqy.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTransfer {


    public  static String copyFiles(String source,String source1, String destination) throws IOException {
        File srcDir = new File(source); //目标tif文件池
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        File Dir1 = new File(source1); //目标tif文件池
        File Dir2 = new File(destination); //目标tif文件池
        if (!Dir1.exists()) {
            Dir1.mkdir();
        }
        if (!Dir2.exists()) {
            Dir2.mkdir();
        }
        
        source1+="/"+df.format(new Date());
        destination+="/"+df.format(new Date());
        File finishDir = new File(source1); //完成tif文件池 文件夹
        File destinationDir = new File(destination); //目的地tif文件池（将目标文件转移到的地方）
        //System.out.println(source1);
        //System.out.println(destination);
		
        
        if (!srcDir.exists()) {
            srcDir.mkdir();
        }
        if (!finishDir.exists()) {
            finishDir.mkdir();
        }
        if (!destinationDir.exists()) {
            destinationDir.mkdir();
        }
		
        File[] files = srcDir.listFiles();
        FileChannel in = null;
        FileChannel out = null;
        String result="";   //记录到达目标文件夹要识别的文件
        String result1=""; //记录要删除的文件集合
        int i=0;
        for (File file : files) {
            try {
                //把目标文件转移到finish
                if(i<10){ //每次去抓取数量小于10个时，继续去抓取
                    if(file.getName().endsWith("tif")||file.getName().endsWith("TIF")){
                        in = new FileInputStream(file).getChannel();
                        File outFile = new File(source1, file.getName());
                        out = new FileOutputStream(outFile).getChannel();
                        in.transferTo(0, in.size(), out);
                        //把目标文件转移到目的地文件夹
                        File outFile1 = new File(destination, file.getName());
                        out = new FileOutputStream(outFile1).getChannel();
                        in.transferTo(0, in.size(), out);
		  
                        result+=destination+"/"+file.getName()+";";
                        i++;
                        result1+=source+"/"+file.getName()+";";
                        //删除方法不能在流没关闭前用，用了失效
                        //boolean f=new File(source+"/"+file.getName()).delete(); //删除这个已经去任务的文件

                    }
                }else{ //每次去抓取数量等于10个时，完成本次定时抓取任务去识别
                    break;
                }
            } finally {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            }
		  
        }
        //FileClean.delAllFileA(source);
        String[] Arry=result1.split(";");
        for(String str:Arry){ //流关闭后，依次删除已经被转移走的文件
            //System.out.println(str);
            new File(str).delete();
        }
        if(result.endsWith(";")){
            result=result.substring(0, result.length()-1);
        }
        System.out.println("完成任务"+result);
        return result;
    }
	
    public static void main(String[] args) throws IOException {
        FileTransfer.copyFiles("C:/2","C:/4","C:/3");
		
    }

}

