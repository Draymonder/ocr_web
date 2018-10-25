package com.eqy.mail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {  
    public static void main(String[] args) {  
          Runnable runner1 = new Runner1(1);  
          Runnable runner2 = new Runner1(2); 
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
          System.out.println("时间:"+df.format(new Date()));
  //      Thread(Runnable target) 分配新的 Thread 对象。  
          Thread thread1 = new Thread(runner1);  
          Thread thread2 = new Thread(runner2);  
          //thread1.start();  
          //thread2.start();  
           thread1.run();  
           thread2.run();  
     }  
 }  
   
 class Runner1 implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  
     private int t =0;
     public Runner1(int t){
    	 this.t= t;
     }
	 public void run() {  
         for (int i = 0; i < 10000; i++) {  
            // System.out.println("进入Runner1运行状态——————————" + i);  
         }  
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         System.out.println("线程一:"+df.format(new Date()));
     }  
     
    
 }  
   
 class Runner2 implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  
    public void run() {  
         for (int i = 0; i <10000; i++) {  
            // System.out.println("进入Runner2运行状态==========" + i);  
         }  
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         System.out.println("线程er"+df.format(new Date()));
     }  
 }  