package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 短信表
 *
 *@author 闫无恙
 */

@Table(TableName = "t_fk_sms")
public class t_fk_sms extends DataModel {

	private String accountsid;
	private String authtoken;
	private String appid;
	private String code;

	// ========== 系统字段 ========================

	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getAccountsid() {
		return accountsid;
	}

	public void setAccountsid(String accountsid) {
		this.accountsid = accountsid;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "t_fk_sms [kid=" + kid + ", accountsid=" + accountsid + ", authtoken=" + authtoken + ", appid=" + appid + "]";
	}

}
