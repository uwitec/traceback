package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.web.SessionTools;

@Service
public class ShopService extends BaseService {

	// 查询所有店铺列表
	public List<Map<String, Object>> selectShopList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t1.*,t2.name admin_name from t_shop t1 "
				+ " left outer join t_shop_admin t2 on t1.admin_id=t2.kid "
				+ " where t1.status=0 and (t1.name like ?) and (t2.link_man like ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.addLike(srh.getString("link_man"));

		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	// 监管人员查看所属
	public List<Map<String, Object>> selectTaskShopList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t.*,ts.code scode,ty.code ycode from t_shop t "
				+ " left outer join t_shop_shengcanlicense ts on t.shengcan_id=ts.kid "
				+ " left outer join t_shop_yingyelicense ty on t.yingye_id=ty.kid "
				+ "where t.status=0 and (t.name like ?) and (t.address = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.add(SessionTools.getCurrentShop().getName());
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}

	// 根据店铺名称查询
	public t_shop findShopByName(String shop_name) {
		String sql = "select * from t_shop t where t.status=0 and t.name=? ";
		t_shop shop = bs.findObj(sql, new String[] { shop_name }, t_shop.class);
		return shop;
	}

//	// 店铺注册
//	@Transactional
//	public boolean regShop(String mobile, String pwd, String m_name,String username, String province, String city, String addres) {
//		t_shop shop = new t_shop();
//		shop.setType("个人店铺");
//		shop.setName(m_name);
//		shop.setLink_man(username);
//		shop.setLink_mobile(mobile);
//		shop.setCountry("中国");
//		shop.setAddress(addres);
//		shop.setCity(city);
//		shop.setProvince(province);
//		bs.saveObj(shop);
//
//		t_shop_admin shopAdmin = new t_shop_admin();
//		shopAdmin.setName(username);
//		shopAdmin.setShop_id(shop.getKid());
//		shopAdmin.setMobile(mobile);
//		shopAdmin.setType("管理员");
//		shopAdmin.setPwd(MD5Util.MD5(pwd));
//		bs.saveObj(shopAdmin);
//		
//		// 设置超级管理员
//		shop.setAdmin_id(shopAdmin.getKid());
//		bs.updateObj(shop);
//		return true;
//	}

	// 无参查询所有店铺 设置 列表
	public List<Map<String, Object>> selectSettingList() {
		String sql = "select * from t_shop_setting t where t.status=0 and t.shop_id=? order by t.field asc ";
		List<Map<String, Object>> list = jt.queryForList(sql, SessionTools.getCurrentShopId());
		return list;
	}
}
