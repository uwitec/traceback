package com.tmsps.traceback.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

	// 增加对应天数
	public static Timestamp addDay(Timestamp end, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(end.getTime());

		cal.add(Calendar.DAY_OF_YEAR, day);
		return new Timestamp(cal.getTimeInMillis());

	}

	// 增加对应秒数
	public static Timestamp addSecond(Timestamp end, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(end.getTime());

		cal.add(Calendar.SECOND, sec);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static int countDays(Timestamp begin, Timestamp end) {
		long beginTime = begin.getTime();
		long endTime = end.getTime();
		int days = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
		return days;
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

	public static String addOneDay(String date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date d = sdf.parse(date);
			Timestamp time = new Timestamp(d.getTime());
			return DateTools.addDay(time, 1).toString();
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
	public static Timestamp strToDatestamp(String date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
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

	/**
	 * 字符串转 Timestamp对象
	 *
	 * @param date
	 */
	public static java.sql.Date strToDate(String date) {
		String pattern = "yyyy-MM-dd";
		return strToDate(date, pattern);
	}

	public static java.sql.Date strNumToDate(String date) {
		String pattern = "yyyyMMdd";
		return strToDate(date, pattern);
	}

	public static java.sql.Date strToDate(String date, String pattern) {
		if (ChkTools.isNull(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date d = sdf.parse(date);
			return new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 取得当前的年月
	public static String getYearMonth() {
		String pattern = "yyyy-MM";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(System.currentTimeMillis()));
	}
	
	// 获取上个月的年月
	public static String getLastYearMonth(){
		Calendar cal = Calendar.getInstance();
		//取得系统当前时间所在月第一天时间对象
		cal.set(Calendar.DAY_OF_MONTH, 1);
		//日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);
		java.util.Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(date);
	}

	public static java.sql.Date getYearMonth(String date) {
		String pattern = "yyyy-MM";
		return strToDate(date, pattern);
	}

	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month;
	}

	public static String getToday() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String getToday(String reg) {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat(reg);
		return df.format(date);
	}

	public static String format(java.sql.Date date) {
		if (ChkTools.isNull(date)) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String getTodayTime() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static int countDaysVSToday(java.sql.Date start) {
		Timestamp now = new Timestamp(System.currentTimeMillis());

		int days = (int) ((now.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
		return days;

	}

	public static void main(String[] args) {
		System.err.println(getDayOfMonth(2000,2));
		System.err.println(getDayOfMonth("2000-02"));
	}
	
	//获取每月最大天数
	//参数 yyyy-MM 格式
	public static int getDayOfMonth(String yearMonth) {
		java.sql.Date date = getYearMonth(yearMonth);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}
	//获取每月最大天数
	public static int getDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}

	public static String formatDateTime(Timestamp date) {
		if (ChkTools.isNull(date)) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String formatDate(Timestamp date) {
		return formatDate(date,"yyyy-MM-dd");
	}

	public static String formatDate(Timestamp date, String fmt) {
		if (ChkTools.isNull(date)) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		return df.format(date);
	}
	
	public static String formatDate(Date date) {
		if (ChkTools.isNull(date)) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	//获取某月的最后一天  month格式：yyyy-MM-dd  or yyyy-MM
	public static String getMonthFinalDay(String month) {
		if(month.length()<10){
			month = month+"-01";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateTools.strToDate(month));
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		//日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);
		java.util.Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static int getWeek(Timestamp date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}


}
