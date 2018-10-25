package com.eqy.mail;



import  com.eqy.mail.FTPUtil;//自己加工测试，遍历所有文件
public class FTPTest {
	public static void main(String[] args) {
		
	}

	public static void FTPDownload(String url, int port, String username, String password,
        String remotePath,  String localPath)  {
		FTPUtil t = new FTPUtil();
		// 连接本地FTP服务器测试
		//boolean f = t.connect("10.1.8.23", 2121, "Administrator", "223315ssw");"E:\\FTPDownload"
		//System.out.println(f);
		//boolean f1 = t.downFileAll(url, port, username ,password, remotePath, localPath);
		//System.out.println(f1);

	}
}
