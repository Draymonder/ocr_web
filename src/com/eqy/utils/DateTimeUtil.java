package com.eqy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 特殊时间格式自主添加
 * <p>
 * Title: Framework
 * </p>
 * <p>
 * Description: Framework
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: xwtech.com
 * </p>
 * 
 * @author
 * @version 1.0 日期公用类
 */
public class DateTimeUtil
{

	// 格式：2007年06月07日 12时12分12秒234毫秒
	private final static String[] FORMAT_CHINA =
	{ "年", "月", "日", "时", "分", "秒", "毫秒" };

	// 格式：2007-06-07 12:12:12 234
	private final static String[] FORMAT_NORMAL =
	{ "-", "-", "", ":", ":", "", "" };

	// 格式：2007/06/07 12:12:12 234
	private final static String[] FORMAT_DATATIME =
	{ "/", "/", "", ":", ":", "", "" };

	// 格式：中文星期
	private final static String[] FORMAT_WEEK =
	{ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 获取今日年份
	 * 
	 * @return yyyy
	 */
	public static String getTodayYear()
	{

		return DateFormatUtils.format(new Date(), "yyyy");
	}

	/**
	 * 获取今日月份
	 * 
	 * @return MM
	 */
	public static String getTodayMonth()
	{

		return DateFormatUtils.format(new Date(), "MM");
	}

	/**
	 * 获取今日日期
	 * 
	 * @return dd
	 */
	public static String getTodayDay()
	{

		return DateFormatUtils.format(new Date(), "dd");
	}

	/**
	 * 获取短日月
	 * 
	 * @return MMdd
	 */
	public static String getTodayChar4()
	{

		return DateFormatUtils.format(new Date(), "MMdd");
	}

	/**
	 * 把日期转换成6字符的格式类型字符串，如200801
	 * 
	 * @param date
	 *            日期
	 * @return String
	 */
	public static String getChar8(Date date)
	{

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	/**
	 * 把日期转换成6字符的格式类型字符串，如2008-01-11
	 * 
	 * @param date
	 *            日期
	 * @return String
	 */
	public static String getChar(Date date)
	{

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	/**
	 * 返回年月
	 * 
	 * @return yyyyMM
	 */
	public static String getTodayChar6()
	{

		return DateFormatUtils.format(new Date(), "yyyyMM");
	}

	/**
	 * 返回年月日
	 * 
	 * @return yyyyMMdd
	 */
	public static String getTodayChar8()
	{

		return DateFormatUtils.format(new Date(), "yyyyMMdd");
	}

	/**
	 * 返回 年月日小时分
	 * 
	 * @return yyyyMMddHHmm
	 */
	public static String getTodayChar12()
	{

		return DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
	}

	/**
	 * 返回 年月日小时分秒
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public static String getTodayChar14()
	{

		return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 返回 年月日小时分秒 毫秒
	 * 
	 * @return yyyyMMddHHmmssS
	 */
	public static String getTodayChar17()
	{

		String dateString = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssS");
		int length = dateString.length();
		if (length < 17)
		{
			String endStr = dateString.substring(14, length);
			int len = endStr.length();
			for (int i = 0; i < 3 - len; i++)
			{
				endStr = "0" + endStr;
			}
			dateString = dateString.substring(0, 14) + endStr;
		}
		return dateString;
	}

	/**
	 * 获取当前日期(返回月日，中间用.隔开)
	 * 
	 * @return 日期(格式：MM.dd)
	 */
	public static String getNowDateDay()
	{

		Calendar cal = Calendar.getInstance();
		int monthTemp = cal.get(Calendar.MONTH) + 1;
		String monthInfo = String.valueOf(monthTemp);
		if (monthTemp < 10)
		{
			monthInfo = "0" + monthInfo;
		}
		int dayTemp = cal.get(Calendar.DATE);
		String dayTempInfo = String.valueOf(dayTemp);
		if (dayTemp < 10)
		{
			dayTempInfo = "0" + dayTempInfo;
		}
		return monthInfo + "." + dayTempInfo;
	}

	/**
	 * 返回中文日期格式 支持4、6、8、12、14、17位转换
	 * 
	 * @param charDateTime
	 *            长整型 CHAR
	 * @return 2007年12月12日 12时12分12秒 234毫秒
	 */
	public static String getFormatChina(String charDateTime)
	{

		return getFormatDateTime(charDateTime, "FORMAT_CHINA");
	}

	/**
	 * 返回标准日期格式 支持4、6、8、12、14、17位转换
	 * 
	 * @param charDateTime
	 *            长整型 CHAR
	 * @return 2007-12-12 12:12:12 234
	 */
	public static String getFormatNormal(String charDateTime)
	{

		return getFormatDateTime(charDateTime, "FORMAT_NORMAL");
	}
	/**
	 * 
	* @Title: getFormatNormalShort
	* @Description: 长整型时间 转化为年月日
	* @param @param charDateTime
	* @param @return    设定文件 如：20170321083134
	* @return String    返回类型 如：2017-03-21
	* @throws
	 */
	public static String getFormatNormalShort(String charDateTime)
	{

		charDateTime = charDateTime.substring(0, 4) + "-" + charDateTime.substring(4, 6) + "-" + charDateTime.substring(6, 8);
	     return charDateTime;
	}
	/**
	 * 
	 * @Title: getFormatNormalShort1
	 * @Description: 长整型时间 转化为年月日
	 * @param @param charDateTime
	 * @param @return    设定文件 如：20170321083134
	 * @return String    返回类型 如：2017.03.21
	 * @throws
	 */
	public static String getFormatNormalShort1(String charDateTime)
	{
		
		charDateTime = charDateTime.substring(0, 4) + "." + charDateTime.substring(4, 6) + "." + charDateTime.substring(6, 8);
		return charDateTime;
	}
	/**
	 * 返回标准日期格式 支持4、6、8、12、14、17位转换
	 * 
	 * @param charDateTime
	 *            长整型 CHAR
	 * @return 2007/12/12 12:12:12 234
	 */
	public static String getFormatDateTime(String charDateTime)
	{

		return getFormatDateTime(charDateTime, "FORMAT_DATATIME");
	}

	/**
	 * 获取当前月天数
	 * 
	 * @return
	 */
	public static int getCurrentMonthDays()
	{

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		return a.get(Calendar.DATE);
	}

	/**
	 * 把日期格式转换为长整型格式
	 * 
	 * @param inputDateTime
	 * @return
	 */
	public static String getDateTimeToChar(String inputDateTime)
	{

		String strResult = "";
		if (null == inputDateTime)
		{
			return strResult = "";
		}
		if (("".equals(inputDateTime.trim())))
		{
			return strResult = "";
		}
		// 替换
		strResult = inputDateTime.replaceAll("年", "");
		strResult = strResult.replaceAll("月", "");
		strResult = strResult.replaceAll("日", "");
		strResult = strResult.replaceAll("时", "");
		strResult = strResult.replaceAll("分", "");
		strResult = strResult.replaceAll("秒", "");
		strResult = strResult.replaceAll("毫", "");
		strResult = strResult.replaceAll(" ", "");
		strResult = strResult.replaceAll("-", "");
		strResult = strResult.replaceAll("/", "");
		strResult = strResult.replaceAll(":", "");
		return strResult;
	}

	/**
	 * 对日期进行转换
	 * 
	 * @param charDateTime
	 * @param formatType
	 * @return
	 */
	public static String getFormatDateTime(String charDateTime, String formatType)
	{

		String strResult = "";
		if (null == charDateTime)
		{
			return strResult = "";
		}
		if (("".equals(charDateTime.trim())))
		{
			return strResult = "";
		}
		String[] FORMAT_CHAR = null;
		if (formatType.equals("FORMAT_CHINA"))
		{
			FORMAT_CHAR = FORMAT_CHINA;
		}
		else if (formatType.equals("FORMAT_NORMAL"))
		{
			FORMAT_CHAR = FORMAT_NORMAL;
		}
		else if (formatType.equals("FORMAT_DATATIME"))
		{
			FORMAT_CHAR = FORMAT_DATATIME;
		}
		else
		{
			return strResult = charDateTime;
		}
		// 去掉空格
		charDateTime = charDateTime.trim();
		if (charDateTime.length() == 4)
		{
			// MMdd 转换 MM月dd日
			strResult = charDateTime.substring(0, 2) + FORMAT_CHAR[1] + charDateTime.substring(2, 4) + FORMAT_CHAR[2];
		}
		else if (charDateTime.length() == 6)
		{
			// yyyyMM 转换 yyyy年MM月
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1];
		}
		else if (charDateTime.length() == 8)
		{
			// yyyyMMdd
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8) + FORMAT_CHAR[2];
		}
		else if (charDateTime.length() == 12)
		{
			// yyyyMMddHHmm
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4];
		}
		else if (charDateTime.length() == 14)
		{
			// yyyyMMddHHmmss
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4] + charDateTime.substring(12, 14) + FORMAT_CHAR[5];
		}
		else if (charDateTime.length() == 17)
		{
			// yyyyMMddHHmmssS
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4] + charDateTime.substring(12, 14) + FORMAT_CHAR[5] + " "
					+ charDateTime.substring(14, 17) + FORMAT_CHAR[6];
		}
		else
		{
			strResult = charDateTime;
		}
		return strResult;
	}

	/**
	 * 将指定Date类型参数转换为指定的Oracle日期时间格式字符串
	 * 
	 * @param inputDateTime
	 *            传入Date类型参数
	 * @return String
	 */
	public static String getOracleDate(Date inputDateTime) throws NullPointerException
	{

		if (null == inputDateTime)
		{
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(inputDateTime);
	}

	/**
	 * 比对两个时间间隔
	 * 
	 * @param startDateTime
	 *            开始时间
	 * @param endDateTime
	 *            结束时间
	 * @param distanceType
	 *            计算间隔类型 天d 小时h 分钟m 秒s
	 * @return
	 */
	public static String getDistanceDT(String startDateTime, String endDateTime, String distanceType)
	{

		String strResult = "";
		long lngDistancVal = 0;
		try
		{
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startDate = tempDateFormat.parse(startDateTime);
			Date endDate = tempDateFormat.parse(endDateTime);
			if (distanceType.equals("d"))
			{
				lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
			}
			else if (distanceType.equals("h"))
			{
				lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);
			}
			else if (distanceType.equals("m"))
			{
				lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60);
			}
			else if (distanceType.equals("s"))
			{
				lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000);
			}
			strResult = String.valueOf(lngDistancVal);
		}
		catch (Exception e)
		{
			strResult = "0";
		}
		return strResult;
	}

	public static long currentTimeMillis(String strDate)
	{

		long timeMillis = 0;
		if (strDate == null || strDate.trim().length() != 10)
		{
			return timeMillis;
		}
		String year = strDate.trim().substring(0, 4);
		String month = strDate.trim().substring(5, 7);
		String day = strDate.trim().substring(8, 10);
		timeMillis = Long.parseLong(year + month + day + "000000");
		return timeMillis;
	}

	public static long currentDateMillis(String strDate)
	{

		long timeMillis = 0;
		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat_3);
			String tem = format1.format(format2.parse(strDate));
			timeMillis = Long.parseLong(tem);
		}
		catch (ParseException e)
		{
		}
		return timeMillis;
	}

	public static long currentWeekMillis(int year, int month)
	{

		try
		{
			Calendar c = Calendar.getInstance();
			c.set(year, month - 1, 1);
			int tem = c.get(Calendar.DAY_OF_WEEK);
			return currentDateMillis(year + "-" + month + "-" + (10 - tem));
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public static long currentLastDayOfMonthMillis(int year, int month)
	{

		try
		{
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			int tem = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			return currentDateMillis(year + "-" + month + "-" + tem);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public static String getHour(String date)
	{

		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat_4);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
			return format1.format(format2.parse(date));
		}
		catch (ParseException e)
		{
			return "";
		}
	}

	public static String getDay(String date)
	{

		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat_5);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
			return format1.format(format2.parse(date));
		}
		catch (ParseException e)
		{
			return "";
		}
	}

	public static String formatDateToOther(String date)
	{

		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat_3);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
			return format1.format(format2.parse(date));
		}
		catch (ParseException e)
		{
			return "";
		}
	}

	public static String formatDateTo8(String date)
	{

		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat8);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat_3);
			return format1.format(format2.parse(date));
		}
		catch (ParseException e)
		{
			return "";
		}
	}

	public static String formatDateTo14(String date)
	{

		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat_3);
			return format1.format(format2.parse(date));
		}
		catch (ParseException e)
		{
			return "";
		}
	}

	private static final String dateFormat = "yyyyMMddHHmmss";

	private static final String dateFormat8 = "yyyyMMdd";

	private static final String dateFormat_2 = "yyyy-MM-dd HH:mm:ss";

	private static final String dateFormat_3 = "yyyy-MM-dd";

	private static final String dateFormat_4 = "HH:mm:ss";

	private static final String dateFormat_5 = "dd/MM";

	/**
	 * 输入yyyy-MM-dd HH:mm:ss格式日期 输出yyyyMMddHHmmss格式日期
	 */
	public static String formatStrToOtherStr(String time)
	{

		String reTime = "";
		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat_2);
			reTime = format1.format(format2.parse(time));
		}
		catch (ParseException e)
		{
		}
		return reTime;
	}

	public static String formatDateStrToOtherStr(String time)
	{

		String reTime = "";
		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat_2);
			SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
			reTime = format1.format(format2.parse(time));
		}
		catch (ParseException e)
		{
		}
		return reTime;
	}

	public static long formatDate(String strDate)
	{

		long reDate = 0;
		try
		{
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
			Date date = format1.parse(strDate);
			String tem = format1.format(date);
			reDate = Long.parseLong(tem);
		}
		catch (ParseException e)
		{
		}
		return reDate;
	}

	/**
	 * 日期差计算 例如在某个日期增加几天后的日期 返回几天后日期
	 * 
	 * @param startDate
	 * @param addDate
	 * @return
	 */
	public static String getIncreaseDT(String startDate, int addDate)
	{

		String strResult = "0000-00-00";
		try
		{
			// 把字符串型日期转换为日期
			Calendar localDate = new GregorianCalendar();
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date tempDate = tempDateFormat.parse(startDate);
			localDate.setTime(tempDate);
			localDate.add(Calendar.DATE, addDate);
			String curyear = "" + localDate.get(Calendar.YEAR);
			int intmonth = localDate.get(Calendar.MONTH) + 1;
			String curmonth = "" + intmonth;
			String curday = "" + localDate.get(Calendar.DAY_OF_MONTH);
			if (curmonth.length() == 1)
			{
				curmonth = "0" + curmonth;
			}
			if (curday.length() == 1)
			{
				curday = "0" + curday;
			}
			strResult = curyear + "" + curmonth + "" + curday;
		}
		catch (Exception e)
		{
		}
		return strResult;
	}
    
	/**
	 * 日期差计算 例如在某个日期增加几天后的日期 返回几天后日期
	 * 
	 * @param startDate
	 * @param addDate
	 * @return
	 */
	public static String getDateAdd(String startDate, int addDate)
	{

		String strResult = "0000-00-00";
		try
		{
			// 把字符串型日期转换为日期
			Calendar localDate = new GregorianCalendar();
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date tempDate = tempDateFormat.parse(startDate);
			localDate.setTime(tempDate);
			localDate.add(Calendar.DATE, addDate);
			String curyear = "" + localDate.get(Calendar.YEAR);
			int intmonth = localDate.get(Calendar.MONTH) + 1;
			String curmonth = "" + intmonth;
			String curday = "" + localDate.get(Calendar.DAY_OF_MONTH);
			if (curmonth.length() == 1)
			{
				curmonth = "0" + curmonth;
			}
			if (curday.length() == 1)
			{
				curday = "0" + curday;
			}
			strResult = curyear + "-" + curmonth ;
		}
		catch (Exception e)
		{
		}
		return strResult;
	}
	/**
	 * 获取本周的开始日期 （星期天的日期）20070201
	 * 
	 * @return
	 */
	public static String getWeekStartDate()
	{

		String strResult = "19000101";
		try
		{
			Calendar calendar = Calendar.getInstance();
			int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
			intWeekNum = intWeekNum - 1;
			strResult = getIncreaseDT(getTodayChar8(), -intWeekNum);
		}
		catch (Exception ex)
		{
			strResult = "19000101";
		}
		return strResult;
	}

	/**
	 * 获取今天星期几 中文
	 * 
	 * @return
	 */
	public static String getWeekChina()
	{

		String strResult = " ";
		try
		{
			Calendar calendar = Calendar.getInstance();
			int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
			intWeekNum = intWeekNum - 1;
			strResult = FORMAT_WEEK[intWeekNum];
		}
		catch (Exception ex)
		{
			strResult = " ";
		}
		return strResult;
	}

	public static String getBeforeMonth(int beforeMonth)
	{

		java.text.Format formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date todayDate = new java.util.Date();
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(todayDate);
		Cal.add(java.util.Calendar.MONTH, beforeMonth);
		return formatter.format(Cal.getTime());
	}

	/*
	 * 获取当天的上一个月
	 */
	public static String getBeforeMonth()
	{

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyyMM").format(cal.getTime());
	}

	/**
	 * 判断终止日期是否大于开始日期
	 * 
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
	public static boolean getDistanceDate(String startDateTime, String endDateTime)
	{

		boolean blnResult = false;
		long lngDistancVal = 0;
		try
		{
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startDate = tempDateFormat.parse(startDateTime);
			Date endDate = tempDateFormat.parse(endDateTime);
			lngDistancVal = endDate.getTime() - startDate.getTime();
			blnResult = lngDistancVal > 0 ? true : false;
		}
		catch (Exception e)
		{
			blnResult = false;
		}
		return blnResult;
	}

	/**
	 * 判断传入日期是否在给定的日期之后
	 * 
	 * @param calendar
	 *            当前时间
	 * @param day
	 *            指定时间
	 * @return 在指定日期之后返回true，否则返回false
	 */
	public static boolean isAfterDayOfMonth(Calendar calendar, int day)
	{

		boolean bRet = false;
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		if (today > day)
		{
			bRet = true;
		}
		return bRet;
	}

	/**
	 * 判断开始日期与终止日期是否相差几个月
	 * 
	 * @param startDateTime
	 * @param endDateTime
	 * @param num
	 *            相差几个月
	 * @return
	 */
	public static boolean getDisMonth(String startDateTime, String endDateTime, int num)
	{

		boolean blnResult = false;
		try
		{
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startDate = tempDateFormat.parse(startDateTime);
			Date endDate = tempDateFormat.parse(endDateTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, num);
			blnResult = cal.getTime().after(endDate);
		}
		catch (Exception e)
		{
			blnResult = false;
		}
		return blnResult;
	}

	/**
	 * 获取两个时间的相隔毫秒数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTwoTimeDTMill(String startTime, String endTime)
	{

		long lngDistancVal = 0;
		try
		{
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startDate = tempDateFormat.parse(startTime.substring(0, 14));
			Date endDate = tempDateFormat.parse(endTime.substring(0, 14));
			lngDistancVal = endDate.getTime() - startDate.getTime();
			lngDistancVal = lngDistancVal + (Integer.parseInt(endTime.substring(14, 17)) - Integer.parseInt(startTime.substring(14, 17)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return String.valueOf(lngDistancVal);
	}

	/**
	 * 获取当前和前几个月
	 * 
	 * @param formatType
	 *            日期格式化格式
	 * @param month
	 *            月数
	 * @return
	 */
	public static String[] getMothBefore(String formatType, int month)
	{

		String dateStr[] = new String[2];
		// 使用默认时区和语言环境获得一个日历。
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		// 通过格式化输出日期
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(formatType);
		dateStr[0] = format.format(Calendar.getInstance().getTime());
		dateStr[1] = format.format(cal.getTime());
		return dateStr;
	}

	/**
	 * 获取现在和之前五个月的数据
	 */
	public static String[] getNowAndBeforeFiveMonthDate(String dataFormat)
	{

		String dateStr[] = new String[2];
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
		cal.add(Calendar.MONTH, -5);// 取当前日期的前一天.
		// 通过格式化输出日期
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dataFormat);
		dateStr[0] = format.format(Calendar.getInstance().getTime());
		dateStr[1] = format.format(cal.getTime());
		return dateStr;
	}

	/**
	 * 获取当前时间若干天后的时间
	 * 
	 * @param days
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getNextTime(int days)
	{

		Calendar theCa = Calendar.getInstance();
		theCa.setTime(new Date());
		theCa.add(theCa.DATE, days);
		return DateFormatUtils.format(theCa.getTime(), "yyyyMMddHHmmss");
	}

	/**
	 * 
	 * @Title: getNextTimeForMin
	 * @Description: 获取多少分钟后的时间
	 * @author 戴光浩
	 * @param mins
	 * @return
	 * @throws
	 */
	@SuppressWarnings("static-access")
	public static String getNextTimeByMin(int mins)
	{

		Calendar theCa = Calendar.getInstance();
		theCa.setTime(new Date());
		theCa.add(theCa.MINUTE, mins);
		return DateFormatUtils.format(theCa.getTime(), "yyyyMMddHHmmss");
	}

	/**
	 * 判断是否大于当前时间
	 */
	public static boolean isAfterNowTime(String starOrEndTime)
	{

		boolean result = false;
		if ("".equals(starOrEndTime))
		{
			return result;
		}
		String currentDate = DateTimeUtil.getTodayChar14();
		if (Long.parseLong(starOrEndTime) < Long.parseLong(currentDate))
		{
			result = true;
		}
		return result;
	}

	/**
	 * 查询前6个月份信息
	 * 
	 * @param context
	 * @return
	 */
	public static String[] getQueryMonth()
	{

		Calendar cal = Calendar.getInstance();
		String[] query_ym = new String[6];
		for (int i = 5; i >= 0; i--)
		{
			int month = cal.get(Calendar.MONTH) + 1;
			String monthStr = String.valueOf(month);
			if (month < 10)
			{
				monthStr = "0" + monthStr;
			}
			query_ym[i] = String.valueOf(cal.get(Calendar.YEAR)) + monthStr;
			cal.add(Calendar.MONTH, -1);
		}
		return query_ym;
	}

	/**
	 * 获取某月的最后一天 201204-->20120430
	 * 
	 * @param month
	 *            某月
	 * @return String
	 * @author caoyan
	 */
	public static String getLastDayOfMonth(String month)
	{

		String strResult = "";
		try
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMM");
			Date tempDate;
			tempDate = tempDateFormat.parse(month);
			cal.setTime(tempDate);
			int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			strResult = month + day;
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return strResult;
	}

	public static String getSystemTime()
	{

		Calendar theCa = Calendar.getInstance();
		theCa.setTime(new Date());
		return DateFormatUtils.format(theCa.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYearMonth(Calendar cal, int addMonth)
	{

		cal.add(Calendar.MONTH, addMonth);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return (year + (month < 10 ? "0" : "") + month);
	}

	public static String[] getLast6Months()
	{

		String[] months = new String[5];
		Calendar cal = Calendar.getInstance();
		months[0] = getYearMonth(cal, 0);
		months[1] = getYearMonth(cal, -1);
		months[2] = getYearMonth(cal, -1);
		months[3] = getYearMonth(cal, -1);
		months[4] = getYearMonth(cal, -1);
		return months;
	}

	/*
	 * 设置当日有效
	 */
	public static long getCurrentDayExpireTime()
	{

		Calendar calendar = Calendar.getInstance();
		String date = DateTimeUtil.getTodayChar8();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6, 8)) + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		Calendar ccalendar = Calendar.getInstance();// 当前时间
		ccalendar.setTime(new Date());
		return calendar.getTimeInMillis() - ccalendar.getTimeInMillis();
	}

	/*
	 * 设置当月有效
	 */
	public static long getCurrentMonthExpireTime()
	{

		Calendar calendar = Calendar.getInstance();
		String date = DateTimeUtil.getTodayChar8();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Calendar ccalendar = Calendar.getInstance();// 当前时间
		ccalendar.setTime(new Date());
		return calendar.getTimeInMillis() - ccalendar.getTimeInMillis();
	}
	/**
     * @Title: formatDateToOracle
     * @Description: 格式化时间入库
     * @param date
     * @param type
     * @return
     */
    public static String formatDateToOracle(String date, String type)
    {
        if ("begin".equals(type))
        {
            String[] dates = date.split("-");
            date = dates[0] + dates[1] + dates[2] + "000000";
        }
        if ("end".equals(type))
        {
            String[] dates = date.split("-");
            date = dates[0] + dates[1] + dates[2] + "235959";
        }
        return date;
    }
    
    /**
     * @Title: formatDateToPage
     * @Description: 格式化时间显示:yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateToTable(String date)
    {
        if (!"".equals(date) && date != null && date.length() >= 14)
        {
            return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":"
                    + date.substring(10, 12) + ":" + date.substring(12, 14);
        }
        else
        {
            return date;
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		System.out.println(DateTimeUtil.getSystemTime());
		String[] array = getQueryMonth();
		// String str = "";
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i] + "\n");
		}
		System.out.println(str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			System.out.println(sdf.parse("2013-03-08 04:43:23"));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
