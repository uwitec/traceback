package com.tmsps.traceback.action_cp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_email;
import com.tmsps.traceback.model.t_fk_email_template;
import com.tmsps.traceback.service.EmailService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.mail.MailAliyunTools;


@Controller
@Scope("prototype")
@RequestMapping("/cp/email")
public class EmailController extends ProjBaseAction {

	@Autowired
	EmailService emailService;
	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_email email) {
		if (ChkTools.isNotNull(email)) {
			t_fk_email before_email = emailService.findEmail();
			before_email.setAccesskey(email.getAccesskey());
			before_email.setAccessSecret(email.getAccessSecret());
			before_email.setAccountName(email.getAccountName());
			before_email.setDomainName(email.getDomainName());
               
			bs.updateObj(before_email);
		}

		this.setTipMsg("保存成功!", Tip.Type.success);

	}
	
	@RequestMapping("/send_email")
	@ResponseBody
	public void send_email(String domainName, String templatecode) {
		t_fk_email email = emailService.findEmail();
		t_fk_email_template templateCode = emailService.selectEmail(templatecode);
		
		boolean b = MailAliyunTools.sendMail(domainName, email, templateCode);
		if (b) {
			this.setTipMsg("发送成功!", Tip.Type.success);
		} else {
			this.setTipMsg("发送失败!", Tip.Type.error);
		}

	}

	/**
	 * 根据code把邮件的基本设置返回
	 * 
	 * @param email
	 */

	@RequestMapping("/edit_email")
	@ResponseBody
	public String edit_email() {
		t_fk_email email = emailService.editEmail();
		result.put("email", email);
		return JsonTools.toJson(result);
	}

}