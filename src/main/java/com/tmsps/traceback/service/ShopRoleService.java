package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.web.SessionTools;

@Service
public class ShopRoleService extends BaseService {

	// 无参查询所有设置列表
	public List<Map<String, Object>> selectRoleList(String shopId, JSONObject srh, Map<String, String> sort_param,
			Page page) {
		String sql = "select * from t_shop_role t where t.status=0 and (t.shop_id=?) and (t.name like ?)";
		NeParamList params = NeParamList.makeParams();
		params.add(shopId);
		params.addLike(srh.getString("name"));
		List<Map<String, Object>> list = bs.findList(sql, params, sort_param, page);
		return list;
	}

	public List<Map<String, Object>> selectRoleList() {
		String sql = "select * from t_shop_role t where t.status=0 and (t.shop_id=?) ";
		return jt.queryForList(sql, SessionTools.getCurrentShopId());
	}

}
