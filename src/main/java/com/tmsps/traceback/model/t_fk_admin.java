package com.tmsps.traceback.model;

import java.sql.Timestamp;
import java.util.Date;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * cp端登陆用户表
 */

@Table
public class t_fk_admin extends DataModel {

	private java.lang.String uname; // 用户名
	private java.lang.String pwd; // 密码
	private java.lang.String name; // 姓名
	private java.lang.String sex; // 性别
	private java.lang.String role_id; // 角色
	private java.lang.String status_sys; // 状态:正常状态 or 限制登陆
	private java.lang.String type; // 类型

	// ========== 系统字段 ========================

	@PK
	private String kid;
	private int status;
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private String creator; // 创建人
	private String updator; // 更新人
	private Date updated; // 更新时间

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

	public void setUname(java.lang.String uname) {
		this.uname = uname;
	}

	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public void setRole_id(java.lang.String role_id) {
		this.role_id = role_id;
	}

	public void setStatus_sys(java.lang.String status_sys) {
		this.status_sys = status_sys;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public void setKid(java.lang.String kid) {
		this.kid = kid;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUpdator(java.lang.String updator) {
		this.updator = updator;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	public java.lang.String getUname() {
		return uname;
	}

	public java.lang.String getPwd() {
		return pwd;
	}

	public java.lang.String getName() {
		return name;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public java.lang.String getRole_id() {
		return role_id;
	}

	public java.lang.String getStatus_sys() {
		return status_sys;
	}

	public java.lang.String getType() {
		return type;
	}

	public java.lang.String getKid() {
		return kid;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public java.lang.String getCreator() {
		return creator;
	}

	public java.lang.String getUpdator() {
		return updator;
	}

	public java.util.Date getUpdated() {
		return updated;
	}

}