package com.tmsps.traceback.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.util.ChkTools;

@Service
public class MenuService extends BaseService {
	
	public List<Map<String, Object>> selectMenuAuthList(String system) {
		// TODO 查询所有菜单
		String sql = "select * from t_fk_auth t where t.status=0 and t.type='菜单' and t.system=? order by t.code asc";

		List<Map<String, Object>> list = jt.queryForList(sql, system);
		return list;
	}
	
	public List<Map<String, Object>> selectFirstLevelMenuList(String system) {
		// TODO 查找一级的菜单
		String sql = "select t.* from t_fk_auth t where t.status=0 and LENGTH(t.code)=3 and t.system=? order by t.code asc ";
		List<Map<String, Object>> list = jt.queryForList(sql, system);
		return list;
	}

	public List<Map<String, Object>> selectAllMenuList(String system) {
		// TODO 查找二级，三级的菜单
		String sql = "select t.code,t.url,t.icon,t.name from t_fk_auth t where t.status=0 and t.system=? order by t.code asc ";
		List<Map<String, Object>> list = jt.queryForList(sql,system);

		return turnToHierarchicalMenu(list);
	}

	public static List<Map<String, Object>> turnToHierarchicalMenu(List<Map<String, Object>> list) {
		List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> node1 : list) {
			String node1_code = (String) node1.get("code");
			String node1_parent_code = node1_code.substring(0, node1_code.length() - 3);

			boolean mark = false;
			for (Map<String, Object> node2 : list) {
				String node2_code = (String) node2.get("code");

				if (node1_parent_code != null && node1_parent_code.equals(node2_code)) {
					mark = true;
					if (node2.get("submenu") == null) {
						node2.put("submenu", new ArrayList<Map<String, Object>>());
					}
					((List<Map<String, Object>>) node2.get("submenu")).add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		return nodeList;
	}
	
	public Map<String, Object> selectRoleMenuList(String role_codes) {
		// TODO 通过role_id获取权限
//		t_fk_role role = bs.findById(role_id, t_fk_role.class);
//		String code = role.getCodes();
		if(ChkTools.isNull(role_codes)){
			return null;
		}
		String code = role_codes;
		String[] codes = code.split(",");
		String code_three = "";
		String code_six = "";
		for (String string : codes) {
			String sub = string.substring(0, 3);
			code_three = checkAndGetCode(code_three, sub);

			String sub_six = string.substring(0, 6);
			code_six = checkAndGetCode(code_six, sub_six);
		}
		code = code + code_six + code_three;
		Map<String, Object> map = new HashMap<>();
		map.put("first", selectMenuListByCodes(code_three));
		map.put("all", selectMenuListByCodes(code));
		return map;
	}

	private String checkAndGetCode(String codes, String sub) {
		// TODO 判断是否包含sub，无则添加
		if (codes.indexOf(sub) < 0) {
			codes += "," + sub;
		}
		return codes;
	}
	
	private List<Map<String, Object>> selectMenuListByCodes(String codes) {
		String code_msg = turnToSqlIn(codes);
		String sql = "select t.code,t.url,t.icon,t.name from t_fk_auth t where t.status=0 and t.system='cp系统' and t.code in (@codes) order by t.code asc";
		sql = sql.replace("@codes", code_msg);
		List<Map<String, Object>> list = jt.queryForList(sql);

		return turnToHierarchicalMenu(list);
	}
	
	private String turnToSqlIn(String codes) {
		// TODO 转换普通字符串为 sql in 字符数组
		String end = "''";
		if (ChkTools.isNull(codes)) {
			return end;
		}
		for (String c : codes.split(",")) {
			end += ",'" + c + "'";
		}
		return end;
	}

}
