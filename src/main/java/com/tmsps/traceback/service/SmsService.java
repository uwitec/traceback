package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_sms;
import com.tmsps.traceback.model.t_fk_sms_template;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.sms.SmsTest;

@Service
public class SmsService extends BaseService {

	// 查找sms记录。如果记录不存在，则新建并保存到数据库
	public t_fk_sms findSms() {
		String sql = "select * from t_fk_sms t where t.code='sms' and t.status=0 ";
		t_fk_sms sms = bs.findObj(sql, t_fk_sms.class);
		if (sms == null) {
			sms = new t_fk_sms();
			sms.setCode("sms");
			bs.saveObj(sms);
		}
		return sms;
	}

	// 查询短信模板列表
	public List<Map<String, Object>> selectSmsTemplateList(JSONObject srh) {
		String sql = "select * from t_fk_sms_template t where t.status=0 ";
		List<Map<String, Object>> list = bs.findList(sql);
		return list;
	}

	// 通过短信模板编号查询对应的短信模板记录
	public t_fk_sms_template findSmsTemplateByCode(String smsTempCode) {
		String sql = "select * from t_fk_sms_template t where t.smsTempCode=? and t.status=0 ";
		t_fk_sms_template sms_template = bs.findObj(sql, new Object[] { smsTempCode }, t_fk_sms_template.class);
		if (sms_template == null) {
			sms_template = new t_fk_sms_template();
			sms_template.setSmsTempCode(smsTempCode);
			bs.saveObj(sms_template);
		}
		return sms_template;
	}

	/**
	 * 发送信息
	 * 
	 * @param mobile
	 *            手机号
	 * @param smsTempCode
	 *            模板编号，用于获取模板ID
	 * @return 是否发送成功
	 */
	public boolean sendSms(String mobile, String smsTempCode, String code) {
		t_fk_sms sms = this.findSms();
		t_fk_sms_template template = this.findSmsTemplateByCode(smsTempCode);
		// 获取服务器返回的发送状态
		String result = SmsTest.templateSMS(mobile, template.getSmsTempId(), sms, code);
		// 获取发送状态码 000000表示发送成功 其他失败
		JSONObject end = JsonTools.jsonStrToJsonObject(result);
		if ("000000".equals(end.getJSONObject("resp").getString("respCode"))) {
			return true;
		} else {
			return false;
		}
	}

}
