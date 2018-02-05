package com.tmsps.traceback.action_cp.sms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_sms_template;
import com.tmsps.traceback.service.SmsService;
import com.tmsps.traceback.util.json.JsonTools;

/**
 * 短信模板
 * 
 * @author 闫无恙
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/sms/template")
public class SmsTemplateController extends ProjBaseAction {

	@Autowired
	private SmsService smsService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = smsService.selectSmsTemplateList(srh);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_sms_template sms_temp) {
		bs.saveObj(sms_temp);
		this.setTipMsg("保存成功！", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_fk_sms_template smsDB = bs.findById(kid, t_fk_sms_template.class);
		smsDB.setStatus(-100);
		bs.updateObj(smsDB);
		this.setTipMsg("删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form_data")
	@ResponseBody
	public String edit_form_data(String kid) {
		t_fk_sms_template sms_temp = bs.findById(kid, t_fk_sms_template.class);
		result.put("sms_temp", sms_temp);
		return JsonTools.toJson(result);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_sms_template sms_temp) {
		t_fk_sms_template smsTempEdit = bs.findById(sms_temp.getKid(), t_fk_sms_template.class);
		smsTempEdit.setSmsTempCode(sms_temp.getSmsTempCode());
		smsTempEdit.setSmsTempId(sms_temp.getSmsTempId());
		bs.updateObj(smsTempEdit);
		this.setTipMsg("更新成功", Tip.Type.success);
	}

}
