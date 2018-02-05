package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 企业生产数据
 */

@Table
public class t_shop_production extends DataModel {

	private String shop_id;// 企业ID
	private String code;// 生产编号
	private String barcode;//产品条码
	private String name;//产品名称
	private int number;// 生产数量
	private String unit;//包装单位
	private String supplier_name;//供货商名称
	private long production_time;// 生产日期
	private long register_time;// 登记日期
	
	private String certificate_name;//合格证名称
	private long certificate_start_time;//合格证签发日期
	private String certificate_status;//合格证状态

	private int trace_code;//追溯码
	
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

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getCertificate_name() {
		return certificate_name;
	}

	public void setCertificate_name(String certificate_name) {
		this.certificate_name = certificate_name;
	}

	public String getCertificate_status() {
		return certificate_status;
	}

	public long getProduction_time() {
		return production_time;
	}

	public void setProduction_time(long production_time) {
		this.production_time = production_time;
	}

	public long getRegister_time() {
		return register_time;
	}

	public void setRegister_time(long register_time) {
		this.register_time = register_time;
	}

	public long getCertificate_start_time() {
		return certificate_start_time;
	}

	public void setCertificate_start_time(long certificate_start_time) {
		this.certificate_start_time = certificate_start_time;
	}

	public void setCertificate_status(String certificate_status) {
		this.certificate_status = certificate_status;
	}

	public int getTrace_code() {
		return trace_code;
	}

	public void setTrace_code(int trace_code) {
		this.trace_code = trace_code;
	}
	
}
