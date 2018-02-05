package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_email;
import com.tmsps.traceback.model.t_fk_email_template;

@Service
public class EmailService extends BaseService {

	public List<Map<String, Object>> selectEmailList() {
		String sql = "select * from t_fk_email_template t where t.status=0 ";
		
		List<Map<String, Object>> list = bs.findList(sql);
		return list;
	}
	

	
	/**
	 * 根据输入的模板编号查询相应模板内容
	 * @param templateId
	 * @return
	 */

	public t_fk_email_template selectEmail(String kid) {
		String sql = "select * from t_fk_email_template t where t.status=0 and t.kid=? ";
		t_fk_email_template template = bs.findObj(sql,new Object[] { kid }, t_fk_email_template.class);
		return template;
	}

	// 查找系统的邮件设置
	public t_fk_email findEmail() {
		
		return findEmail("email");
	}

	public t_fk_email findEmail(String code) {
		String sql = "select * from t_fk_email t where t.status=0 and t.code=? ";
		t_fk_email email = bs.findObj(sql, new String[]{code}, t_fk_email.class);
		if(email==null){
			email = new t_fk_email();
			email.setCode(code);
			bs.saveObj(email);
		}
		return email;
	}
	

	
	
	public t_fk_email editEmail() {
		String sql = "select * from t_fk_email t where t.status=0 ";
		t_fk_email email = bs.findObj(sql, t_fk_email.class);
		if(email==null){
			email=new t_fk_email();
			bs.saveObj(email);
		}
		return email;
	}
	

}
