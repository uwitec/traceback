package com.tmsps.traceback.util.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.traceback.util.ChkTools;

public class AliTreeTools {

	public static void main(String[] args) {

	}

	// 预处理树节点
	public static List<Map<String, Object>> handleTree(List<Map<String, Object>> menuList) {
		for (Map<String, Object> map : menuList) {
			map.remove("kid");
			map.remove("created");
			map.remove("status");
			map.remove("mid");
			map.put("action_type", "link");
			String url = (String) map.get("url");
			if(ChkTools.isNull(url)){
				url = "http://www.baidu.com";
			}
			url = url.replace("/wx/", "/ali/");
			map.put("action_param", url);
		}

		return menuList;
	}

	@SuppressWarnings("unchecked")
	public static String turnListToTree(List<Map<String, Object>> menuList) {
		// TODO 转换List为树形结构
		menuList = handleTree(menuList);

		List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> node1 : menuList) {
			String node1_code = (String) node1.get("code");
			String node1_parent_code = node1_code.substring(0, node1_code.length() - 3);

			boolean mark = false;
			for (Map<String, Object> node2 : menuList) {
				String node2_code = (String) node2.get("code");

				if (node1_parent_code != null && node1_parent_code.equals(node2_code)) {
					mark = true;
					if (node2.get("sub_button") == null) {
						node2.put("sub_button", new ArrayList<Map<String, Object>>());
					}
					((List<Map<String, Object>>) node2.get("sub_button")).add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}

		JSONObject menu = new JSONObject();
		menu.put("button", nodeList);

		return menu.toJSONString();
	}

}
