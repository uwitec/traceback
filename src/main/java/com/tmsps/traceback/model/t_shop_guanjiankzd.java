package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 产品关键控制点
 */

@Table
public class t_shop_guanjiankzd extends DataModel {

	private String parent_id;// 父产品ID
	private String name;// 名称
	private String val;//值
	private String unit;//单位
	
	// ========== 系统字段 ========================
	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
