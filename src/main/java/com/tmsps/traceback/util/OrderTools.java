package com.tmsps.traceback.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderTools {

	// 返回类似201603181601064663899883390订单号
	public static String getOrderNO() {
		return getOrderNO("yyMMddHHmmssSSS", 10);
	}

	public synchronized static String getOrderNO(String datePartten, int randomNumeric) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sd = new SimpleDateFormat(datePartten);
		return sd.format(new Date()) + RandomStringUtils.randomNumeric(randomNumeric);
	}

	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			s.add(getOrderNO());
		}
		System.err.println(s.size());
	}

	// 格式化两位小数点
	public static BigDecimal cutDecimal(BigDecimal total) {
		DecimalFormat df = new DecimalFormat("#.00");
		return ChkTools.getBigDecimal(df.format(total));
	}

	public static String getPayIntToString(BigDecimal total) {
		return total.multiply(ChkTools.getBigDecimal("100")).intValue() + "";
	}
}
