package com.tmsps.traceback.util.recommend;

import com.tmsps.ne4spring.utils.Base58;
import com.tmsps.traceback.util.ChkTools;

/**
 * 优惠码 加解密Tools
 * 
 * @author Administrator
 * 
 */
public class RecommendTools {

	/**
	 * 加密优惠码
	 * 
	 * @param rdCode
	 * @return
	 */
	public static String encode(String rdCode) {
		if (ChkTools.isNull(rdCode)) {
			throw new RuntimeException("优惠码不能为空");
		}
		String encode = Base58.encode(rdCode.getBytes());
		return encode;
	}

	/**
	 * 解密
	 * 
	 * @param rdCodeEncoden
	 * @return
	 */
	public static String decode(String rdCodeEncoden) {
		if (ChkTools.isNull(rdCodeEncoden)) {
			throw new RuntimeException("优惠码不能为空");
		}
		byte[] decode = Base58.decode(rdCodeEncoden);
		return new String(decode);
	}

}
