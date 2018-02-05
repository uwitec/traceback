package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 企业表
 */

@Table
public class t_shop extends DataModel {

	private String type;// 企业类型
	
	private String code;// 企业编号
	private String name;// 企业名称
	private String link_man;// 负责人姓名
	private String link_mobile;// 负责人手机电话
	private String admin_id;// adminkid
	private String address; // 地方管理局 or 地址
	private String yingye_id; // 营业执照id
	private String shengcan_id; // 生产许可证id

	private String shop_menu_id;// 模版ID

	private String level = "B";// 信用等级
	private String logo_file_id;// 企业logo


	// ========== 系统字段 ========================

	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShop_menu_id() {
		return shop_menu_id;
	}

	public void setShop_menu_id(String shop_menu_id) {
		this.shop_menu_id = shop_menu_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getYingye_id() {
		return yingye_id;
	}

	public void setYingye_id(String yingye_id) {
		this.yingye_id = yingye_id;
	}

	public String getShengcan_id() {
		return shengcan_id;
	}

	public void setShengcan_id(String shengcan_id) {
		this.shengcan_id = shengcan_id;
	}

	public String getLink_man() {
		return link_man;
	}

	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}

	public String getLink_mobile() {
		return link_mobile;
	}

	public void setLink_mobile(String link_mobile) {
		this.link_mobile = link_mobile;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLogo_file_id() {
		return logo_file_id;
	}

	public void setLogo_file_id(String logo_file_id) {
		this.logo_file_id = logo_file_id;
	}

	
}