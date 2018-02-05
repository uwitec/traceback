package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/*
 * 短信模板
 */

@Table(TableName = "t_fk_sms_template")
public class t_fk_sms_template extends DataModel {
	
	private String smsTempCode;	//模板编码
	private String smsTempId;	//模板id

	// ========== 系统字段 ========================
	
	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();
	
	@NotMap
	private static final long serialVersionUID = 1L;
	
	
	// ======= get / set ()=====================

	public void setCreated(long created) {
		this.created = created;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSmsTempCode() {
		return smsTempCode;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public void setSmsTempCode(String smsTempCode) {
		this.smsTempCode = smsTempCode;
	}

	public String getSmsTempId() {
		return smsTempId;
	}

	public void setSmsTempId(String smsTempId) {
		this.smsTempId = smsTempId;
	}
	

	public int getStatus() {
		return status;
	}

	public long getCreated() {
		return created;
	}

}
