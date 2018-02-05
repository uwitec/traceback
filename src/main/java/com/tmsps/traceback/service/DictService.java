package com.tmsps.traceback.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.ne4spring.orm.ClassUtil;
import com.tmsps.ne4spring.utils.ChkUtil;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.*;

@Service
public class DictService extends BaseService {

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

	public List<Map<String, Object>> selectTableList(String table) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return null;
		}
		if (!this.contain(table, "code")) {
			throw new RuntimeException(table + "表没有 code 字段.");
		}

		String sql = "select * from @table t where t.status=0 order by t.code asc";
		sql = sql.replace("@table", table);
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> selectTableByFieldList(String table, String field, String value) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return null;
		}
		if (!this.contain(table, "code")) {
			throw new RuntimeException(table + "表没有 code 字段.");
		}
		String sql = null;
		if (value.length() == 3) {

			sql = "select * from @table t where t.status=0 and (t.@field like ?) and length(t.code)=6 order by t.code asc";
			sql = sql.replace("@table", table);
			sql = sql.replace("@field", field);
		}
		if (value.length() == 6) {
			sql = "select * from @table t where t.status=0 and (t.@field like ?) and length(t.code)=9 order by t.code asc";
			sql = sql.replace("@table", table);
			sql = sql.replace("@field", field);
		}

		return jt.queryForList(sql, value + "%");
	}

	public boolean selectTableFindValue(String table, String field, String value) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return false;
		}
		if (!this.contain(table, field)) {
			throw new RuntimeException(table + "表没有 @code 字段.".replace("@code", field));
		}

		String sql = "select count(*) cnt from @table t where t.status!=-100 and t.@field=?";
		sql = sql.replace("@table", table);
		sql = sql.replace("@field", field);
		Number l = (Number) jt.queryForMap(sql, value).get("cnt");

		return l.intValue() > 0 ? true : false;
	}

	public boolean selectTableFindValue(String shop_id, String table, String field, String value) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return false;
		}
		if (!this.contain(table, field)) {
			throw new RuntimeException(table + "表没有 @code 字段.".replace("@code", field));
		}

		String sql = "select count(*) cnt from @table t where t.status!=-100 and t.@field=? and t.shop_id=? ";
		sql = sql.replace("@table", table);
		sql = sql.replace("@field", field);
		Number l = (Number) jt.queryForMap(sql, value, shop_id).get("cnt");

		return l.intValue() > 0 ? true : false;
	}

	public boolean selectTableFindValueNotme(String table, String field, String value, String kid) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return false;
		}
		if (!this.contain(table, field)) {
			throw new RuntimeException(table + "表没有 code 字段.");
		}

		String sql = "select count(*) cnt from @table t where t.status!=-100 and t.@field=? and t.kid!=?";
		sql = sql.replace("@table", table);
		sql = sql.replace("@field", field);
		Number l = (Number) jt.queryForMap(sql, value, kid).get("cnt");

		return l.intValue() > 0 ? true : false;
	}

	public boolean selectTableFindValueNotme(String shop_id, String table, String field, String value, String kid) throws Exception {
		if (ChkUtil.isNull(table) || table.contains(" ")) {
			return false;
		}
		if (!this.contain(table, field)) {
			throw new RuntimeException(table + "表没有 code 字段.");
		}

		String sql = "select count(*) cnt from @table t where t.status!=-100 and t.@field=? and t.kid!=? and t.shop_id=? ";
		sql = sql.replace("@table", table);
		sql = sql.replace("@field", field);
		Number l = (Number) jt.queryForMap(sql, value, kid, shop_id).get("cnt");

		return l.intValue() > 0 ? true : false;
	}

	// 获取 list 字段描述
	public static List<String> getFieldDesc(Class<?> clazz) {
		System.err.println("输出表单对应keys:\n");
		List<String> list = new ArrayList<String>();
		List<Field> fields = ClassUtil.getClassFields(clazz);
		for (Field field : fields) {
			list.add("'" + field.getName() + "'");
		}
		return list;
	}

	// 获取 list 字段描述
	public static List<String> getFieldDescMap(Map<?, ?> map) {
		System.err.println("输出表单对应keys:\n");
		List<String> list = new ArrayList<String>();
		for (Object field : map.keySet()) {
			list.add("'" + field.toString() + "'");
		}
		return list;
	}

	// update 工具类
	public static void getUpdateSetGet(Class<?> clazz, String modelName) {
		System.err.println("输出修改类时 对应的 get set 方法:\n");
		List<Field> fields = ClassUtil.getClassFields(clazz);
		for (Field field : fields) {
			String f = (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
			System.err.println("@modelDb.set@Field(@model.get@Field());".replace("@model", modelName).replace("@Field", f));

		}
	}

	/**
	 * 创建建表语句
	 * 
	 * @param clazz
	 */
	public static void getCreateTable(Class<?> clazz) {
		System.err.println("输出对应的 create table 方法:\n");
		List<Field> fields = ClassUtil.getClassFields(clazz);

		String sql = "CREATE TABLE " + clazz.getSimpleName() + " ( \n";
		for (Field field : fields) {
			sql += field.getName();
			if (field.getType() == int.class) {
				sql += " int(11) default NULL,";
			} else if (field.getType() == long.class) {
				sql += " bigint(20) default NULL,";
			} else if (field.getType() == Timestamp.class) {
				sql += " datetime default NULL,";
			} else if (field.getType() == Date.class || field.getType() == java.util.Date.class) {
				sql += " date default NULL,";
			} else if (field.getType() == BigDecimal.class) {
				sql += " decimal(20,2) default NULL,";
			} else {
				sql += " varchar(50) default NULL,";
			}
			sql += "\n";
		}

		sql += " PRIMARY KEY  (`kid`));";
		System.err.println(sql);
	}

}
