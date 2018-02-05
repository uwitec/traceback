package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.ne4spring.utils.MD5Util;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.util.SpringTools;
import com.tmsps.traceback.util.json.JsonTools;

@Service
public class AdminService extends BaseService {

	public t_fk_admin selectUserByEmailAndPwd(String uname, String pwd) {
		// 账号密码查询
		String sql = "select * from t_fk_admin t where t.uname=? and t.pwd=?  and t.status=0";
		t_fk_admin user = bs.findObj(sql, new Object[] { uname, MD5Util.MD5(pwd) }, t_fk_admin.class);
		return user;
	}

	// 查询管理员列表
	public List<Map<String, Object>> selectAdminList(JSONObject srh, Map<String, String> sort_param, Page page) {
		String sql = "select t.*,tr.name role_name from t_fk_admin t "
				+ " left join t_fk_role tr on t.role_id=tr.kid and tr.status=0 "
				+ " where t.status=0 and (t.uname like ?) and (t.name like ?) ";

		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("uname"));
		param.addLike(srh.getString("name"));
		List<Map<String, Object>> list = bs.findList(sql, param, sort_param, page);
		return list;
	}

	public t_fk_admin findUser(String uname) {
		String sql = "select * from t_fk_admin t where t.status=0 and t.uname=?";
		t_fk_admin admin = bs.findObj(sql,new String[]{uname},t_fk_admin.class);
		return admin;

	}

	public static void main(String[] args) {
		AdminService s = SpringTools.getBean(AdminService.class);
		System.err.println(JsonTools.toJson(s.findUser("admin")));
	}

}
