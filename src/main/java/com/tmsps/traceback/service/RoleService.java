package com.tmsps.traceback.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.util.StringTools;

@Service
public class RoleService extends BaseService {

	public List<Map<String, Object>> selectRole(String srh_uname, Map<String, String> sort_params, Page page) {
		String sql = "select * from t_fk_role t  where t.status=0  order by t.name asc";

		NeParamList params = NeParamList.makeParams();
		params.addLike(srh_uname);

		List<Map<String, Object>> list = jt.queryForList(sql, params, sort_params, page);
		List<Map<String, Object>> roles = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {

			String codes = (String) map.get("codes");
			String value = StringTools.splitStrToSqlIn(codes);
			String sql1 = "select t.name auth_code from t_fk_auth t where t.status=0 and t.code in(" + value + ") ";

			List<Map<String, Object>> names = jt.queryForList(sql1);
			StringBuilder stb = new StringBuilder();
			for (Map<String, Object> name : names) {
				stb.append(",").append(name);
			}

			map.put("codes", stb.toString());
			roles.add(map);
		}
		return roles;
	}

	/**
	 * 2016.05.08
	 */
	public List<Map<String, Object>> selectRoleList(JSONObject srh) {
		String sql = "select t.* from t_fk_role t where t.status=0 and (t.name like ?) order by t.name asc ";

		return jt.queryForList(sql, "%" + srh.getString("name") + "%");
	}

	public List<Map<String, Object>> selectRoleList() {
		String sql = "select * from t_fk_role t  where t.status=0 order by t.name asc ";

		List<Map<String, Object>> list = jt.queryForList(sql);
		return list;
	}

	public Map<String, Object> selectRoleNameByIds(String role_ids) {
		String ids = StringTools.splitStrToSqlIn(role_ids);
		String sql = "select t.name name from t_fk_role t  where t.status=0 and t.kid in(" + ids + ") ";
		List<Map<String, Object>> names = jt.queryForList(sql);
		StringBuilder stb = new StringBuilder();
		for (Map<String, Object> name : names) {
			stb.append(name.get("name")).append(",");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		System.err.println("stb==" + stb.toString() + ",index=" + stb.lastIndexOf(","));
		map.put("name", stb.toString().substring(0, stb.lastIndexOf(",")));
		return map;
	}

	public List<Map<String, Object>> selectRoleCodesByRoleIds(String role_ids) {
		String ids = StringTools.splitStrToSqlIn(role_ids);
		String sql = "select codes from t_fk_role t  " + "where t.status=0 and t.kid in(" + ids + ") ";
		List<Map<String, Object>> list = jt.queryForList(sql);
		return list;
	}

	public static void main(String[] args) {
	}

}
