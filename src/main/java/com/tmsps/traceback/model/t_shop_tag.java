package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 企业产品标签
 */

@Table
public class t_shop_tag extends DataModel {

	private String production_id;// 产品ID
	private String start_code;// 开始产品标签码
	private String end_code;// 结束产品标签码
	
	// ========== 系统字段 ========================
	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

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

	public String getProduction_id() {
		return production_id;
	}

	public void setProduction_id(String production_id) {
		this.production_id = production_id;
	}

	public String getStart_code() {
		return start_code;
	}

	public void setStart_code(String start_code) {
		this.start_code = start_code;
	}

	public String getEnd_code() {
		return end_code;
	}

	public void setEnd_code(String end_code) {
		this.end_code = end_code;
	}
	
	
}
