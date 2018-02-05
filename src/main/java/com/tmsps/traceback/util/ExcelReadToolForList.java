package com.tmsps.traceback.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmsps.traceback.util.ChkTools;

public class ExcelReadToolForList {

	public static List<Map<String, Object>> turnToList(String[] content, Map<Integer, String> map, int len) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (content.length != len)
			throw new RuntimeException("导入与已知不符！");

		for (int i = 2; i < map.size(); i++) {
			String params = map.get(i);
			String[] pa = params.split("#");
			if (ChkTools.isNull(pa[0]))
				continue;
			Map<String, Object> con = new HashMap<String, Object>();
			for (int j = 0; j < len; j++) {
				if (j < pa.length) {
					con.put(content[j], pa[j]);
				} else {
					con.put(content[j], "");
				}
			}
			list.add(con);
		}
		return list;
	}

}
