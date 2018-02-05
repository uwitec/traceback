package com.tmsps.traceback.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalenderTools {
	/**
	 * 用于将给定的日期进行加1个月
	 * @param str 
	 * @return
	 */
	public static  String getNewDate(String str){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		Date date;
		String newDate=null;
		try {
			date = simpleDateFormat.parse(str);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.add(Calendar.MONTH,1);
			newDate = simpleDateFormat.format(calender.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	/**
	 * 用于将给定的日期进行加1年
	 * @param str 
	 * @return
	 */
	public static  String getNewYear(String str){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		Date date;
		String newDate=null;
		try {
			date = simpleDateFormat.parse(str);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.add(Calendar.YEAR,1);
			newDate = simpleDateFormat.format(calender.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	/**
	 * 比较日期   
	 * @param mDate
	 * @return 返回大于0的值表示当前日期比mDate大;等于0表示两个日期相等 出错返回null
	 */
	public static  Integer compareDate(String mDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(mDate);
			Calendar calender = Calendar.getInstance();
			Calendar calender1 = Calendar.getInstance();
			calender.setTime(d);
			calender1.setTime(new Date());
			return calender1.compareTo(calender);  //calender1>calender 返回大于0的值
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 字符串转 Timestamp对象
	 * 
	 * @param date
	 */
	public static Timestamp strToTimestamp(String date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date d = sdf.parse(date);
			return new Timestamp(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
