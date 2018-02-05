package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
/**
 * 审核状态
 * @author 申伟
 *
 */
@Service
public class ShopAuthService extends BaseService{
	public List<Map<String, Object>> selectShopAuthList() {
		// TODO 查询所有权限数据
		String sql = "select * from t_shop_auth t  where t.status=0 ";
		
		List<Map<String, Object>> list  = jt.queryForList(sql);
		
		return list;
	}
}
