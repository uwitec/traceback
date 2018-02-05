package com.tmsps.traceback.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tmsps.ne4spring.utils.PKUtil;

public class WebTools {

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	public static HttpServletResponse getReponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}

	public static HttpSession getSession() {
		HttpSession sn = WebTools.getRequest().getSession();
		return sn;
	}

	public static String getIp() {
		HttpServletRequest request = getRequest();
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	// 存储在浏览器客户端的 cookie sn 码
	public static String getClientCookieSN() {
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if ("cookie_sn".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}

		String value = PKUtil.getPK();
		Cookie cookie = new Cookie("cookie_sn", value);
		cookie.setPath("/");
		cookie.setMaxAge(10 * 365 * 24 * 60 * 60);
		getReponse().addCookie(cookie);
		return value;
	}
}
