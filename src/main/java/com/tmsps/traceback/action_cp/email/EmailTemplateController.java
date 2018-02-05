package com.tmsps.traceback.action_cp.email;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_email_template;
import com.tmsps.traceback.service.EmailService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller
@Scope("prototype")
@RequestMapping("/cp/email/template")
public class EmailTemplateController extends ProjBaseAction {
	@Autowired
	EmailService emailService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {

		List<Map<String, Object>> list = emailService.selectEmailList();

		result.put("list", list);
	}

	@RequestMapping("/add_template")
	@ResponseBody
	public void add_template(t_fk_email_template emailTemplate) {
		bs.saveObj(emailTemplate);

		this.setTipMsg("保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form_data")
	@ResponseBody
	public String edit_form_data(String kid) {
		t_fk_email_template template = bs.findById(kid, t_fk_email_template.class);
		result.put("template", template);
		return JsonTools.toJson(result);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_email_template template) {
		t_fk_email_template templateDb = emailService.selectEmail(template.getKid());

		templateDb.setTemplateId(template.getTemplateId());
		templateDb.setTemplateName(template.getTemplateName());
		bs.updateObj(templateDb);

		this.setTipMsg("保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		logger.info("del-->" + kid);
		t_fk_email_template templateDb = bs.findById(kid, t_fk_email_template.class);
		templateDb.setStatus(-100);
		bs.updateObj(templateDb);
		this.setTipMsg("删除成功!", Tip.Type.success);
	}

}