package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
/**
 * 店铺等级 
 * @author 申伟
 *
 */

@Service
public class ShopLevelService extends BaseService {
	public List<Map<String, Object>> selectShopLevelList() {
		
		String sql = "select * from t_shop_level t  where t.status=0 ";
		List<Map<String, Object>> list = bs.findList(sql);

		return list;
	}
}
