package com.eqy.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTransfer {
	 /**
     * date转换成String
     * 
     * @param date
     * @return 2017-07-18 23:34:35
     */
	public static String DateChange(Date date) {
   	 DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
   	 return simpleDateFormat.format(date);
	}
	public static String DateChangeA(Date date) {
		DateFormat simpleDateFormat= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");   //创建一个格式化日期对象
		return simpleDateFormat.format(date);
	}
	
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
	public static String getWeekOfDate(Date dt) {
	        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dt);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
	        return weekDays[w];
	}
	public static void main(String[] args) {
		System.out.println(DateTransfer.DateChangeA(new  Date()));

	}
}
