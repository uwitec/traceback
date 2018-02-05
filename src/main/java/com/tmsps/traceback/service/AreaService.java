package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.util.tree.TreeTools;

@Service
public class AreaService extends BaseService {

	public List<Map<String, Object>> selectAreaRootList(int deep, int area_parent_id) {
		// TODO 查询根节点数据
		String sql = "select code,area_id,area_name,area_deep,area_parent_id,kid from t_fk_area t "
				+ "where t.status=0 and t.area_deep=? and t.area_parent_id=? order by t.code asc";
		List<Map<String, Object>> list = jt.queryForList(sql, new Object[] { deep, area_parent_id });
		return TreeTools.handleTree(list);
	}

	public List<Map<String, Object>> selectAreaList(int area_parent_id, int deep) {
		// TODO 查询某一级的地点
		String sql = "select t.area_id,t.area_name from t_fk_area t  "
				+ "where t.status=0 and t.area_parent_id=? and t.area_deep=? order by t.code asc";
		List<Map<String, Object>> list = jt.queryForList(sql, area_parent_id, deep);
		return list;
	}

}
