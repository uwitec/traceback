package com.tmsps.traceback.util.ip;

import javax.servlet.http.HttpServletRequest;

import com.tmsps.traceback.web.WebTools;


public class IPTools {

	public static String getIP() {
		HttpServletRequest req = WebTools.getRequest();
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}

		return ip;
	}
}
