package com.tmsps.traceback.util.sms;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.traceback.service.SmsService;
import com.tmsps.traceback.web.SessionTools;

public class resourceTools {
	
	public static String resource(SmsService smsSevice,String phoneNum) {
		String code = RandomStringUtils.randomNumeric(4);
		long thirty_time = System.currentTimeMillis()+1800*1000;
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("thirty_time", thirty_time);
		
		System.out.println("时间："+new Date(thirty_time)+",验证码"+code);
		
		SessionTools.put(SessionTools.SMS_CODE, json);
		boolean isSend = smsSevice.sendSms(phoneNum, "reg", code);
		return isSend ? "YES" : "NO";
	}
	
}
