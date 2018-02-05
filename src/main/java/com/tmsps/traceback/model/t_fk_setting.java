package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 系统参数设置
 */

@Table
public class t_fk_setting extends DataModel {
	
	private String field;// 属性
	private String val;// 值

	private String note;// 备注

	
	
	// ========== 系统字段 ========================
	
	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();
	
	@NotMap
	private static final long serialVersionUID = 1L;
	
	
	
	// ======= get / set ()=====================
	
	public int getStatus() {
		return status;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
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

	public void setStatus(int status) {
		this.status = status;
	}

}