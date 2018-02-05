package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 产品投料数据
 */

@Table
public class t_shop_touliao extends DataModel {

	private String parent_id;// 父产品ID
	private String name;// 原料名称
	private String batch;//原料进货批次
	private String number;// 投料数量
	private String unit;// 单位
	private String scaleman;//配料员
	private long dosing_time;// 配料时间
	
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getScaleman() {
		return scaleman;
	}

	public void setScaleman(String scaleman) {
		this.scaleman = scaleman;
	}

	public long getDosing_time() {
		return dosing_time;
	}

	public void setDosing_time(long dosing_time) {
		this.dosing_time = dosing_time;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}
