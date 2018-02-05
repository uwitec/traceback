package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_shop_product;
import com.tmsps.traceback.model.t_shop_production;
import com.tmsps.traceback.web.SessionTools;

@Service
public class ShopProductionService extends BaseService {

	public List<Map<String, Object>> selectShopProductionList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t.* from t_shop_production t "
				+ "where t.status=0 and (t.name like ?) and (t.shop_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.add(SessionTools.getCurrentShopId());
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectSuperShopProduction(JSONObject srh, Map<String, String> sort_params, Page page,String shop_id) {
		String sql = "select t.* from t_shop_production t "
				+ "where t.status=0 and (t.shop_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(shop_id);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopTagList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_tag t where t.status=0 and (t.production_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopTouliaoList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_touliao t where t.status=0 and (t.parent_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopJianyanList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_jianyan t where t.status=0 and (t.parent_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}

	public List<Map<String, Object>> selectShopShengcangcList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_shengcangc t where t.status=0 and (t.parent_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}

	public List<Map<String, Object>> selectShopGuanjiankzdList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_guanjiankzd t where t.status=0 and (t.parent_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public t_shop_production find_trace_code(String trace_code) {
		String sql = "select * from t_shop_production t where t.status=0 and t.trace_code=?";
		t_shop_production shop_production = bs.findObj(sql, new Object[] { trace_code }, t_shop_production.class);
		return shop_production;

	}

	public List<Map<String, Object>> selectShopProductList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t.* from t_shop_product t where t.status=0 and (t.name like ?) and (t.shop_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.add(SessionTools.getCurrentShopId());
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopTouliaoTemplateList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_touliao_template t where t.status=0 and (t.product_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopGuanjiankzdTemplateList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_guanjiankzd_template t where t.status=0 and (t.product_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectShopJianyanTemplateList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_jianyan_template t where t.status=0 and (t.product_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}

	public List<Map<String, Object>> selectShopShengcangcTemplateList(JSONObject srh, Map<String, String> sort_params, Page page, String kid) {
		String sql = "select t.* from t_shop_shengcangc_template t where t.status=0 and (t.product_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.add(kid);
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public t_shop_product get_shop_product(String name) {
		String sql = "select * from t_shop_product t where t.status=0 and t.name = ? and t.shop_id= ? ";
		t_shop_product shop_product = bs.findObj(sql, new Object[] { name,SessionTools.getCurrentShopId() }, t_shop_product.class);
		return shop_product;
	}

}
