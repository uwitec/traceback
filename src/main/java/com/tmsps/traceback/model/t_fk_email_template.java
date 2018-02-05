package com.tmsps.traceback.model;

import com.tmsps.ne4spring.annotation.NotMap;
import com.tmsps.ne4spring.annotation.PK;
import com.tmsps.ne4spring.annotation.Table;
import com.tmsps.ne4spring.orm.model.DataModel;

/**
 * 邮件模板表
 */

@Table(TableName = "t_fk_email_template")
public class t_fk_email_template extends DataModel {
	
	private String templateId;
	private String templateName;//模版名称
	private String subject;//邮件主题
	private String htmlBody;//邮件内容

	
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

	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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



	public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getHtmlBody() {
		return htmlBody;
	}



	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}
	
	

}