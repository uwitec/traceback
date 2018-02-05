package com.tmsps.traceback.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringTools {

	public static void main(String[] args) {
//		String codes = "12,23,34";
//		System.err.println(splitStrToSqlIn(codes));
		
	}

	public static String splitStrToSqlIn(String codes) {
		// TODO 转换普通字符串为 sql in 字符数组
		String end = "''";
		if (ChkTools.isNull(codes)) {
			return end;
		}
		for (String c : codes.split(",")) {
			end += ",'" + c + "'";
		}
		return end.replace("'',", "");
	}

	public static List<String> splitStrToSqlInList(String codes) {
		// TODO 转换普通字符串为 sql in 字符数组
		List<String> list = new ArrayList<>();
		if (ChkTools.isNull(codes)) {
			return list;
		}
		for (String c : codes.split(",")) {
			list.add(c);
		}
		return list;
	}
	
	public static String distinctAuthCode(List<Map<String, Object>> codes) {
		Set<String> set = new HashSet<String>();
		for(Map<String,Object> map : codes){
			String[] auth_codes = map.get("auth_codes").toString().split(",");
			for(String str : auth_codes ){
				set.add(str);
			}
		}
		
		StringBuilder strBu = new StringBuilder();
		
		for(String s : set){
			strBu.append(",").append(s);
		}
		return strBu.toString();
	}

}
