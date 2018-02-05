package com.tmsps.traceback.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4Weixin.beans.QrCode;
import com.tmsps.ne4Weixin.config.WxConfig;
import com.tmsps.ne4Weixin.utils.HttpClient;
import com.tmsps.traceback.util.json.JsonTools;

public class WxCode {

	public static final String table = "table_";

	protected static final String BaseURL = "https://api.weixin.qq.com";
	private static final String QRCODEURL = BaseURL.concat("/cgi-bin/qrcode/create?access_token=%s");

	public static QrCode getQrCode(WxConfig config, String scene_str) {
		String result = HttpClient.postJson(String.format(QRCODEURL, config.getAccessToken()), getPermanentJson(scene_str));
		return JSONObject.parseObject(result, QrCode.class);
	}

	private static String getPermanentJson(String scene_str_value) {
		Map<String, Object> qMap = new HashMap<String, Object>();
		qMap.put("action_name", "QR_LIMIT_STR_SCENE");
		Map<String, Object> action_info = new HashMap<String, Object>();
		Map<String, String> scene_str = new HashMap<String, String>();
		scene_str.put("scene_str", scene_str_value);
		action_info.put("scene", scene_str);
		qMap.put("action_info", action_info);
		return JsonTools.toJson(qMap);
	}

}
