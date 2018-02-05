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
public class ShopIngoodsService extends BaseService {

	public List<Map<String, Object>> selectShopIngoodsList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t.* from t_shop_ingoods t "
				+ "where t.status=0 and (t.name like ?) and (t.shop_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.add(SessionTools.getCurrentShopId());
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
}
