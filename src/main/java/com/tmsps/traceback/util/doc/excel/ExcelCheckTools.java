package com.tmsps.traceback.util.doc.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmsps.traceback.util.ChkTools;

public class ExcelCheckTools {

	/**
	 * 功能：验证导入的excel 字符串长度，和number类型数据
	 * 
	 * @param list
	 *            要验证的数据
	 * @param map
	 *            ：key 为需要验证的excel 列， 
	 *            val > 0 则验证字符串长度 ; val == 0,则验证是否为number 
	 * @return 符合ture 反之false
	 */
	public static boolean checkStringLenOrNum(List<Map<String, Object>> list,
			Map<String, Integer> ruleMap) {
		boolean flag = false;
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				if (ChkTools.isNotNull(ruleMap) && ruleMap.containsKey(key)) {
					String val = (String) map.get(key);
					int len = ruleMap.get(key);
					if (len == 0) {
						flag =  isNumber(val);
					} else {
						flag =  validateStrByLength(val, len);
					}
					if(flag == false)
						return flag;
				}else{
					return false;
				}
			}

		}
		return flag;
	}

	/**
	 * 功能：验证字符串长度是否符合要求，
	 * 
	 * @param strParameter
	 *            要验证的字符串
	 * @param limitLength
	 *            验证的长度
	 * @return 符合长度ture 超出范围false
	 */
	public static boolean validateStrByLength(String strParameter,
			int limitLength) {

//		int temp_int = 0;
//		byte[] b = strParameter.getBytes();
//
//		for (int i = 0; i < b.length; i++) {
//			if (b[i] >= 0) {
//				temp_int = temp_int + 1;
//			} else {
//				temp_int = temp_int + 1;
//				i++;
//			}
//		}
		if(ChkTools.isNull(strParameter)){
			strParameter = "";
		}

		if (strParameter.length() < limitLength) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：正则表达式数字验证
	 * 
	 * @param strParameter
	 *            要验证的字符串
	 * @return 符合长度ture 超出范围false
	 */
	public static boolean isNumber(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("[0-9,.]*");
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 5; i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", "威尼商人");
			m.put("sex", "");
			m.put("age", "");
			m.put("salary", "3000.50");
			list.add(m);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("name",5);
		map.put("sex", 2);
		map.put("age", 0);
		
		System.err.println(checkStringLenOrNum(list,map));
		
		System.out.println(isNumber(""));
	}
}
