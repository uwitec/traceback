package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 系统反馈
 */

@Table
public class t_fk_feedback extends DataModel {
	
	private String content;// 内容


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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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