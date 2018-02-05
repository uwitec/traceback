package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.ne4spring.utils.MD5Util;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_shop_admin;
import com.tmsps.traceback.web.SessionTools;

@Service
public class ShopAdminService extends BaseService {

	// 通过用户名 或 手机号登录SHOP端
	public t_shop_admin loginShopByUnameOrMobile(String mobile, String pwd) {
		String sql = "select t.* from t_shop_admin t where t.status=0 and (t.mobile=?) and (t.pwd=?) ";
		t_shop_admin staff = bs.findObj(sql, new Object[] { mobile, MD5Util.MD5(pwd) }, t_shop_admin.class);
		return staff;
	}
	
	public t_shop_admin loginShopByUnameOrAllPwd(String mobile) {
		String sql = "select t.* from t_shop_admin t where t.status=0 and (t.mobile=?)";
		t_shop_admin staff = bs.findObj(sql, new Object[] { mobile }, t_shop_admin.class);
		return staff;
	}
	
	// 查询所有店铺管理员列表
	public List<Map<String, Object>> selectShopAdminList(JSONObject srh, Map<String, String> sort_params,Page page) {
		String sql = "select t.*,tr.name role from t_shop_admin t "
				+ " left outer join t_shop_role tr on t.role_id=tr.kid "
				+ " where t.status=0 and (t.shop_id=?) and (t.mobile like ?) and (t.name like ?)";
		NeParamList param = NeParamList.makeParams();
		param.add(SessionTools.getCurrentShopId());
		param.addLike(srh.getString("mobile"));
		param.addLike(srh.getString("name"));
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}

	// 查询所有店铺管理员列表
	public List<Map<String, Object>> selectShopAdminList() {
		String sql = "select t.* from t_shop_admin t where t.status=0 and (t.shop_id=?)";
		List<Map<String, Object>> list = jt.queryForList(sql,SessionTools.getCurrentShopId());
		return list;
	}
	
	// 通过name查询店铺管理员
	public t_shop_admin findShopAdminByName(String name) {
		String sql = "select * from t_shop_admin t where t.status=0 and t.name=? ";
		t_shop_admin shop_admin = bs.findObj(sql, new Object[] { name }, t_shop_admin.class);

		return shop_admin;
	}

	// 查找注册邮箱是否存在
	public t_shop_admin findEmail(String email) {
		String sql = "select * from t_shop_admin t where t.status=0 and t.email=?";
		t_shop_admin admin = bs.findObj(sql, new Object[] { email }, t_shop_admin.class);
		return admin;

	}

	// 查找注册的手机号是否存在
	public t_shop_admin findMobile(String mobile) {
		String sql = "select * from t_shop_admin t where t.status=0 and t.mobile=?";
		t_shop_admin admin = bs.findObj(sql, new Object[] { mobile }, t_shop_admin.class);
		return admin;

	}


}
