package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 企业销售数据
 */

@Table
public class t_shop_sell extends DataModel {

	private String shop_id;// 企业ID
	private String code;// 销货编号
	private String name;//产品名称
	private String barcode;//产品条码
	private int number;// 销货数量	
	private String unit;//单位
	private long sell_time;// 销货日期
	private long production_time;// 生产日期
	private String tixone_code;//一票通单号
	private long created_bill_time;//生成账单日期
	private String distributor_name;//分销商名称
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTixone_code() {
		return tixone_code;
	}

	public void setTixone_code(String tixone_code) {
		this.tixone_code = tixone_code;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public long getSell_time() {
		return sell_time;
	}

	public void setSell_time(long sell_time) {
		this.sell_time = sell_time;
	}

	public long getProduction_time() {
		return production_time;
	}

	public void setProduction_time(long production_time) {
		this.production_time = production_time;
	}

	public long getCreated_bill_time() {
		return created_bill_time;
	}

	public void setCreated_bill_time(long created_bill_time) {
		this.created_bill_time = created_bill_time;
	}
	
}
