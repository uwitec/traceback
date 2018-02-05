package com.tmsps.traceback.util.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmsps.traceback.util.ChkTools;

public class CookieTools {

	/**
	 * 设置值
	 * 
	 * @param name
	 * @param obj
	 * @param resp
	 */
	public static void setAttribute(String name, String val,
			HttpServletResponse resp) {
		setAttribute(name, val, resp, false);
	}

	public static void setAttribute(String name, String val,
			HttpServletResponse resp, boolean isMax) {
		resp.setHeader("P3P", "CP=CAO PSA OUR");

		String valEncode = null;
		try {
			valEncode = URLEncoder.encode(val, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie(name, valEncode);
		// cookie.setDomain("kaihuangzhe.com");
		cookie.setPath("/");
		if (isMax) {
			cookie.setMaxAge(12 * 30 * 24 * 60 * 60);
		}

		resp.addCookie(cookie);
	}

	/**
	 * 根据 name 获取 对应的值
	 * 
	 * @param name
	 * @param req
	 * @return
	 */
	public static String getAttribute(String name, HttpServletRequest req) {
		String val = null;

		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (name.equals(c.getName())) {
					cookie = c;
					break;
				}
			}
		}

		if (cookie != null) {
			val = cookie.getValue();
		}
		if(ChkTools.isNotNull(val)){
			try {
				val = URLDecoder.decode(val, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return val;
	}

}
