package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 生产许可证表
 */

@Table
public class t_shop_shengcanlicense extends DataModel {

	
	private String code;// 许可证号
	private long start_time;// 注册时间
	private long end_time;// 有效期至
	private String img_url;// 图片链接

	// ========== 系统字段 ========================

	@PK
	private String kid;
	private int status;
	private long created = System.currentTimeMillis();

	@NotMap
	private static final long serialVersionUID = 1L;

	// ======= get / set ()=====================

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

	public long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}


	
}