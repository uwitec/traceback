package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 站点设置
 */

@Table
public class t_fk_website extends DataModel {
	
	private String name;// 站点名称
	private String logo_file_id;// 站点logo
  
	private String icp;// ICP证书号
	private String cnzz;// 第三方流量统计代码

	private String note;// 站点说明
	 private  String text;//站点服务
	private String code; 


	
	// ========== 系统字段 ========================
	
	

	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();
	
	@NotMap
	private static final long serialVersionUID = 1L;
	
	

	// ======= get / set ()=====================
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public int getStatus() {
		return status;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo_file_id() {
		return logo_file_id;
	}

	public void setLogo_file_id(String logo_file_id) {
		this.logo_file_id = logo_file_id;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getCnzz() {
		return cnzz;
	}

	public void setCnzz(String cnzz) {
		this.cnzz = cnzz;
	}

}