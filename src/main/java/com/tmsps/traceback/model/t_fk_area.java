package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 地区表
 */

@Table
public class t_fk_area extends DataModel {

	private String code;// 编号: 每3位代表一级
	private int area_id;

	private String area_name;
	private String area_name_all;// 全名称
	private String area_parent_id;// 上级
	private int area_sort;// 排序
	private int area_deep = 1;// 所属层级 1...
	private String area_region;// 所属大区 东北 华北等

	// ========== 系统字段 ========================

	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================
	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public int getArea_sort() {
		return area_sort;
	}

	public void setArea_sort(int area_sort) {
		this.area_sort = area_sort;
	}

	public String getArea_parent_id() {
		return area_parent_id;
	}

	public void setArea_parent_id(String area_parent_id) {
		this.area_parent_id = area_parent_id;
	}

	public int getArea_deep() {
		return area_deep;
	}

	public void setArea_deep(int area_deep) {
		this.area_deep = area_deep;
	}

	public String getArea_region() {
		return area_region;
	}

	public void setArea_region(String area_region) {
		this.area_region = area_region;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getArea_name_all() {
		return area_name_all;
	}

	public void setArea_name_all(String area_name_all) {
		this.area_name_all = area_name_all;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
