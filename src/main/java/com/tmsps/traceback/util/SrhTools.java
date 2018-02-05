package com.tmsps.traceback.util;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.tmsps.traceback.util.json.JsonTools;

public class SrhTools {

	/**
	 * Action 打包查询参数
	 * 
	 * @Description:
	 * @param modelMap
	 * @throws
	 */
	public static void packParams(Map<String, Object> paramsMap,
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		String srh_params = JsonTools.toJson(paramsMap);
		req.setAttribute("srh_params", srh_params);
	}

	/**
	 * 拆分查询参数
	 * 
	 * @Description:
	 * @param mv
	 * @param srh_params
	 * @throws
	 */
	public static void splitParams(HttpServletRequest req, String srh_params) {
		// TODO Auto-generated method stub
		Map<String, Object> modelMap = JsonTools.jsonStrToMap(srh_params);
		Set<String> keys = modelMap.keySet();
		for (String key : keys) {
			req.setAttribute(key, modelMap.get(key));
		}
	}

}
