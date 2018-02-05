package com.tmsps.traceback.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TimeTools {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String time1 = "08:12";
		String time2 = "07:56";
		String time3 = "12:56";
		String time4 = "23:56";
		String time5 = "10:00";

		System.out.println(getInitialTime(time1));
		System.out.println(getInitialTime(time2));
		System.out.println(getInitialTime(time3));
		System.out.println(getInitialTime(time4));
		System.out.println(getInitialTime(time5));
		System.err.println(getNowInitialTime());
		System.err.println(getNewInitialTime());
	}

	/**
	 * 时间就近取整:<=30分向前取整,>30分向后取整 Author:zr
	 * 
	 * @param time
	 *            inTime 07:56
	 * @return outTime 08:00
	 */

	public static List<String> getNowInitialTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return getInitialTime(sdf.format(new Date(System.currentTimeMillis())));
	}

	public static List<String> getNewInitialTime() {
		return getInitialTime("10:00");
	}

	private static List<String> getInitialTime(String time) {
		List<String> list = new ArrayList<>();
		int hour = 0;// 小时
		int minutes = 0;// 分钟
		StringTokenizer st = new StringTokenizer(time, ":");
		List<String> inTime = new ArrayList<String>();
		while (st.hasMoreElements()) {
			inTime.add(st.nextToken());
		}
		hour = Integer.parseInt(inTime.get(0).toString());
		minutes = Integer.parseInt(inTime.get(1).toString());
		if (minutes > 30) {
			hour = (hour + 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		try {
			while (true) {
				hour++;
				if (hour >= 24) {
					break;
				}
				if (hour >= 6 && hour <= 10) {
					continue;
				}
				list.add(sdf.format(sdf.parse(hour + ":00")));
				list.add(sdf.format(sdf.parse(hour + ":30")));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

}
