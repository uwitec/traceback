package com.tmsps.traceback.util.ext;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.utils.ChkUtil;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.util.ChkTools;

public class ExtTools {

	// 设置查询参数
	public static void setSrhParams(ProjBaseAction baseAction, HttpServletRequest req) {
		Enumeration<String> params = req.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if (ChkTools.isNotNull(param) && param.startsWith("srh.")) {
				String key = param.substring(4);
				String val = req.getParameter(param);
				baseAction.srh.put(key, val);
			}

		}

		String sort_params_json = req.getParameter("sort");

		if (ChkTools.isNotNull(sort_params_json)) {
			JSONArray sorts = (JSONArray) JSONObject.parse(sort_params_json);
			for (Object sortParams : sorts) {
				JSONObject obj = (JSONObject) sortParams;
				baseAction.sort_params.put(obj.getString("property"), obj.getString("direction"));
			}
		}

	}

	// 设置排序
	public static void setSortParams(ProjBaseAction baseAction, HttpServletRequest req) {
		// sortname:name
		// sortorder:desc
		String sortname = req.getParameter("sortname");
		String sortorder = req.getParameter("sortorder");
		if (ChkUtil.isNull(sortorder)) {
			sortorder = "desc";
		}

		if (ChkTools.isNotNull(sortname)) {
			baseAction.sort_params.put(sortname, sortorder);
		}

	}
}
