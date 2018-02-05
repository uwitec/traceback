package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.ne4spring.orm.ClassUtil;
import com.tmsps.ne4spring.utils.ChkUtil;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.util.ChkTools;
@Service
public class MenuLevelService extends BaseService{
	
	// 判断表中是否有字段
	public boolean contain(String table, String field) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(t_fk_admin.class.getPackage().getName() + "." + table);
			List<String> list = ClassUtil.getPropertyName(clazz);
			return list.contains(field);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}


	public List<Map<String, Object>> selectTableByFieldList(String table, String field, String value) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return null;
		}
		if (!this.contain(table, "code")) {
			throw new RuntimeException(table + "表没有 code 字段.");
		}
		String sql = null;
		if (value.length() == 3 && ChkTools.isNotNull(value)) {

			sql = "select * from @table t where t.status=0 and (t.@field like ?) and length(t.code)=6 order by t.code asc";
			sql = sql.replace("@table", table);
			sql = sql.replace("@field", field);
		}
		if (value.length() == 6 && ChkTools.isNotNull(value)) {
			sql = "select * from @table t where t.status=0 and (t.@field like ?) and length(t.code)=9 order by t.code asc";
			sql = sql.replace("@table", table);
			sql = sql.replace("@field", field);
		}
		return jt.queryForList(sql, value + "%");
	}

}
