package com.tmsps.traceback.web;

import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.model.t_shop_admin;

public class SessionTools {

	// 登陆 cp 管理员
	public static String LOGIN_USER = "LOGIN_USER";

	// 当前登陆店铺管理员
	public static String LOGIN_SHOP_ADMIN = "LOGIN_SHOP_ADMIN";
	public static String LOGIN_SHOP = "LOGIN_SHOP";

	// 登陆普通用户
	public static String LOGIN_MEMBER = "LOGIN_MEMBER";

	// 登陆后返回的 URL
	public static String LOGIN_TO_URL = "LOGIN_TO_URL";
	// 图片验证码
	public static String VARIETY_KID = "VARIETY_KID";
	// 短信验证码
	public static String SMS_CODE = "SMS_CODE";
	// 购物车中选中结算的项
	public static final String CART_JSON = "CART_JSON";
	// wx 在 session中订单
	public static final String WX_ORDER_INSN = "WX_ORDER_INSN";

	// wx当前支付的原因
	public static final String WX_PAY = "WX_PAY";// 1 购买 2 充值
	
	// wx 充值的信息
	public static final String WX_CHARGE = "WX_CHARGE";

	// ========= put get ====================
	public static void put(String key, Object val) {
		// TODO 设置session值
		WebTools.getSession().setAttribute(key, val);
	}

	public static Object get(String key) {
		// TODO 获取session值
		return WebTools.getSession().getAttribute(key);
	}

	// ========= 获取各session值方法 ====================
	public static String getVarietyKid() {
		// TODO 获取session值
		return WebTools.getSession().getAttribute(VARIETY_KID).toString();
	}

	public static t_fk_admin getCurrentLoginCpAdmin() {
		t_fk_admin user = (t_fk_admin) WebTools.getSession().getAttribute(LOGIN_USER);
		return user;
	}

	public static String getCurrentLoginCpAdminId() {
		t_fk_admin user = getCurrentLoginCpAdmin();
		return user != null ? user.getKid() : null;
	}

	public static t_shop_admin getCurrentShopAdmin() {
		t_shop_admin admin = (t_shop_admin) WebTools.getSession().getAttribute(LOGIN_SHOP_ADMIN);
		return admin;
	}

	public static t_shop getCurrentShop() {
		t_shop shop = (t_shop) WebTools.getSession().getAttribute(LOGIN_SHOP);
		return shop;
	}

	// 获取当前登陆店铺 id
	public static String getCurrentShopId() {
		t_shop shop = (t_shop) WebTools.getSession().getAttribute(LOGIN_SHOP);
		return shop != null ? shop.getKid() : null;
	}

	public static String getLoginToUrl() {
		String loginToUrl = (String) WebTools.getSession().getAttribute(LOGIN_TO_URL);
		return loginToUrl;
	}

}
