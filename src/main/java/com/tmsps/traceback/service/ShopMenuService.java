package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_auth;
import com.tmsps.traceback.model.t_shop_menu_auth;
import com.tmsps.traceback.util.StringTools;

@Service
public class ShopMenuService extends BaseService {

	// 查询所有菜单模版列表
	public List<Map<String, Object>> selectMenuList(JSONObject srh, Map<String, String> sort_param, Page page) {
		String sql = "select t.* from t_shop_menu t where t.status=0 and (t.name like ?) ";

		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		List<Map<String, Object>> list = bs.findList(sql, param, sort_param, page);
		return list;
	}
	
	// 查询模版下的菜单
	public List<Map<String, Object>> selectMenuAuthShopList(String menu_id) {
		String sql = "select t.* from t_shop_menu_auth t where t.status=0 and t.menu_id = '&?' order by t.code asc";
		sql = sql.replace("&?", menu_id);
		List<Map<String, Object>> list = jt.queryForList(sql);

		return list;
	}

	// 获取所有的菜单
	public List<Map<String, Object>> selectAllMenuList(String menu_id) {
		String sql = "select t.code,t.icon,t.name,t.kid,t.url from t_shop_menu_auth t where t.status=0 and t.menu_id='&?' order by t.code asc ";
		sql = sql.replace("&?", menu_id);
		List<Map<String, Object>> list = jt.queryForList(sql);

		return MenuService.turnToHierarchicalMenu(list);
	}

	// 按照权限获取菜单
	public List<Map<String, Object>> selectAllMenuList(String menu_id ,String role_codes) {
		String sql = "select t.code,t.icon,t.name,t.kid,t.url from t_shop_menu_auth t where t.status=0 and t.menu_id='&?' and t.code in (@?@) order by t.code asc ";
		sql = sql.replace("&?", menu_id);
		sql = sql.replace("@?@", StringTools.splitStrToSqlIn(role_codes));
		List<Map<String, Object>> list = jt.queryForList(sql);

		return MenuService.turnToHierarchicalMenu(list);
	}
	
	// 查询菜单的行 Row 
	public List<Map<String, Object>> selectMenuRowList(JSONObject srh, Map<String, String> sort_param, Page page,String menu_auth_id) {
		String sql = "select t.* from t_shop_menu_row t where t.status=0 and (t.menu_auth_id = ?) ";

		NeParamList param = NeParamList.makeParams();
		param.add(menu_auth_id);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_param, page);
		return list;
	}
	
	// 查询菜单的行 Row 
	public List<Map<String, Object>> selectMenuRowList(String menu_auth_id) {
		String sql = "select t.name text,t.field dataIndex,t.flex from t_shop_menu_row t where t.status=0 and (t.state = '显示') and (t.menu_auth_id = ?) order by t.code asc";
		List<Map<String, Object>> list = bs.findList(sql, new String[] { menu_auth_id });
		return list;
	}
	
	/* ------------------------ start ------------------------------
	 * TODO   检验 code 字段是否存在    chaixin 2017年11月6日11:20:55
	 */
	public t_shop_menu_auth findAuthByCode(String code,String menu_id) {
		// TODO 根据code查询是否存在
		String sql = "select * from t_shop_menu_auth t where t.status=0 and t.code=? and t.menu_id = ?";
		t_shop_menu_auth shop_menu_auth = (t_shop_menu_auth) bs.findObj(sql, new String[] { code,menu_id }, t_fk_auth.class);
		return shop_menu_auth;
	}

	public t_shop_menu_auth findAuthByCode(String kid, String code,String menu_id) {
		String sql = "select * from t_shop_menu_auth t where t.status=0 and t.code=? and t.kid!=?";
		t_shop_menu_auth shop_menu_auth = (t_shop_menu_auth) bs.findObj(sql, new String[] { code, kid ,menu_id }, t_shop_menu_auth.class);
		return shop_menu_auth;
	}
	// ------------------------- end ------------------------------
	
}
