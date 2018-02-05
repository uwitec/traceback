package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_setting;


@Service
public class SettingService extends BaseService{
	
	// 无参查询所有设置列表
	public List<Map<String, Object>> selectSettingListNoParam() {
		String sql = "select * from t_fk_setting t where t.status=0 ";
		
		List<Map<String, Object>> list = bs.findList(sql);
		return list;
	}
	
	// 查询所有设置列表
	public List<Map<String, Object>> selectSettingList(String query,String qtype,JSONObject srh, Map<String, String> sort_param, Page page) {
		String sql = "select * from t_fk_setting t where t.status=0 and t.field like ?";
		NeParamList param = NeParamList.makeParams();
		param.addLike(query);
		
		List<Map<String, Object>> list = bs.findList(sql,param,sort_param,page);
		return list;
	}

	// 通过设置名称查询设置项
	public t_fk_setting findSettingByField(String field) {
		String sql = " select * from t_fk_setting t where t.field=? and t.status=0 ";
		t_fk_setting setting = bs.findObj(sql, new Object[]{field}, t_fk_setting.class);
		return setting;
	}
	
	// 通过设置名称查询设置项
	public String getVal(String field) {
		String sql = " select * from t_fk_setting t where t.field=? and t.status=0 ";
		t_fk_setting setting = bs.findObj(sql, new Object[]{field}, t_fk_setting.class);
		if(setting==null){
			throw new RuntimeException("请设置@field参数！！！".replace("@field", field));
		}
		return setting.getVal();
	}
	
	// 通过kid查询设置项
	public t_fk_setting findSettingByKid(String kid){
		String sql = "select * from t_fk_setting t where t.kid=? and t.status=0 ";
		t_fk_setting setting = bs.findObj(sql,new Object[]{kid},t_fk_setting.class);
		return setting;
	}
	
}
