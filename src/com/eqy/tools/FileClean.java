package com.eqy.tools;
import java.io.File;
public class FileClean {


	  /*************************删除文件夹delFolder / 删除文件夹中的所有文件delAllFile *start***********/
	
	    /**
	     * 删除文件夹
	     * @param folderPath 文件夹完整绝对路径 ,"Z:/xuyun/save"
	     */
	    public static void delFolder(String folderPath) {
	        try {
	            delAllFileA(folderPath); //删除完里面所有内容
	            String filePath = folderPath;
	            filePath = filePath.toString();
	            java.io.File myFilePath = new java.io.File(filePath);
	            myFilePath.delete(); //删除空文件夹
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	    }
	
	    /**
	     * 删除指定文件夹下所有文件(不包括子文件夹）
	     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save"
	     * 
	     */
	    public static boolean delAllFileA(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) {
	            return flag;    
	        }
	        if (!file.isDirectory()) {
	            return flag;
	        }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	            if (path.endsWith(File.separator)) {
	                temp = new File(path + tempList[i]);
	            } else {
	                temp = new File(path + File.separator + tempList[i]);
	            }
	            
	            if (temp.isFile()) {
	                temp.delete();
	            }
	            //if (temp.isDirectory()) {
	            //    delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	            //    delFolder(path + "/" + tempList[i]);//再删除空文件夹
	            //    flag = true;
	           //    }
	        }
	        return flag;
	    }
	    
	    /**
	     * 删除指定文件夹下子文件夹及文件
	     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save"
	     * 
	     */
	    public static boolean delAllFileB(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) {
	            return flag;    
	        }
	        if (!file.isDirectory()) {
	            return flag;
	        }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	            if (path.endsWith(File.separator)) {
	                temp = new File(path + tempList[i]);
	            } else {
	                temp = new File(path + File.separator + tempList[i]);
	            }
	            
	           // if (temp.isFile()) {
	           //     temp.delete();
	           // }
	            if (temp.isDirectory()) {
	                delAllFileA(path + "/" + tempList[i]);//先删除文件夹里面的文件
	                delFolder(path + "/" + tempList[i]);//再删除空文件夹
	                flag = true;
	               }
	        }
	        return flag;
	    }
	    
	    
	    public static void main(String[] args) {

            for(int i = 0; i <1000000000 ; i++) {
                if(i%13==0) System.out.println(1111.13032132f+i);
            }
		}
}

