package com.tmsps.traceback.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tmsps.traceback.web.WebTools;



public class SpringTools {

	private static FileSystemXmlApplicationContext bf = null;

	/*
	 * 用于 Service Bean 获取. 在web里千万别调用.
	 */
	// @Deprecated
	public static <T extends Object> T getBean(Class<T> clazz) {
		String xml = ".\\target\\seal\\WEB-INF\\applicationContext.xml";
		xml = ".\\target\\classes\\applicationContext.xml";
		xml = ".\\src\\main\\webapp\\WEB-INF\\applicationContext.xml";
		xml = "classpath:applicationContext.xml";
		System.err.println("在web里千万别调用...SpringUtil.getBean....用 getWebBean !");
		if (bf == null) {
			bf = new FileSystemXmlApplicationContext(xml);
		}
		T bean = bf.getBean(clazz);
		// bf.close();//不能关闭
		return bean;
	}

	/**
	 * 在web中获取 bean
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends Object> T getWebBean(Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(WebTools.getSession()
						.getServletContext());

		return context.getBean(clazz);
	}

	public static void main(String[] args) {
//		UserService ds = SpringTools.getBean(UserService.class);
//		System.out.println(ds);
	}
}
