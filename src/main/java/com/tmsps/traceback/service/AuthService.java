package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_auth;
import com.tmsps.traceback.util.StringTools;

@Service
public class AuthService extends BaseService {

	public List<Map<String, Object>> selectAuthSysList() {
		// TODO 查询所有权限数据
		String sql = "select * from t_fk_auth t  where t.status=0 and t.system='cp系统' order by t.code asc";
		List<Map<String, Object>> list = jt.queryForList(sql);

		return list;
	}

	public List<Map<String, Object>> selectAuthShopList() {
		// TODO 查询所有权限数据
		String sql = "select * from t_fk_auth t  where t.status=0 and t.system='商户' order by t.code asc";
		List<Map<String, Object>> list = jt.queryForList(sql);

		return list;
	}

	public List<Map<String, Object>> selectSystemAuthList() {
		return selectSystemAuthList("cp系统");
	}

	public List<Map<String, Object>> selectSystemAuthList(String system) {
		// TODO 查询 系统的 所有菜单和操作项
		String sql = "select * from t_fk_auth t  where t.status=0 and t.system=? order by t.code asc";
		List<Map<String, Object>> list = jt.queryForList(sql, new String[] { system });
		return list;
	}

	public List<Map<String, Object>> selectMenuAuthList(String system) {
		// TODO 查询所有菜单
		String sql = "select * from t_fk_auth t  where t.status=0 and t.type='菜单' and t.system=? order by t.code asc";

		List<Map<String, Object>> list = jt.queryForList(sql, system);
		return list;
	}

	public List<Map<String, Object>> selectMenuAuthList() {
		// TODO 查询所有菜单
		String sql = "select * from t_fk_auth t  where t.status=0 and t.type='菜单' and t.system=? order by t.code asc";

		List<Map<String, Object>> list = jt.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> selectMenuAuthList(String system, String role_codes) {
		// TODO 查询 用户菜单
		String codes = StringTools.splitStrToSqlIn(role_codes);

		String sql = "select * from t_fk_auth t  where t.status=0 and t.system=? and t.code in (@codes) order by t.code asc";
		sql = sql.replace("@codes", codes);

		List<Map<String, Object>> list = jt.queryForList(sql, system);
		return list;
	}

	public t_fk_auth findAuthByCode(String code) {
		// TODO 根据code查询是否存在
		String sql = "select * from t_fk_auth t where t.status=0 and t.code=?";
		t_fk_auth auth = (t_fk_auth) bs.findObj(sql, new String[] { code }, t_fk_auth.class);
		return auth;
	}

	public t_fk_auth findAuthByCode(String kid, String code) {
		String sql = "select * from t_fk_auth t where t.status=0 and t.code=? and t.kid!=?";
		t_fk_auth auth = (t_fk_auth) bs.findObj(sql, new String[] { code, kid }, t_fk_auth.class);
		return auth;
	}

}
