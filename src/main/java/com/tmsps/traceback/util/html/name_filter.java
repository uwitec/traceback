package com.tmsps.traceback.util.html;

import com.tmsps.traceback.util.ChkTools;


public class name_filter {

	public static String filter(String inputString) {
		if(ChkTools.isNotNull(inputString)){
			boolean is_html = HtmlTools.is_html(inputString);
			if(is_html){
				inputString = inputString.replaceAll("<", "＜");//半角换全角
				inputString = inputString.replaceAll(">", "＞");
				inputString = inputString.replaceAll("\"", "＂");
				inputString = inputString.replaceAll("\'", "＇");
			}
		}
		return inputString;
	}
	
}