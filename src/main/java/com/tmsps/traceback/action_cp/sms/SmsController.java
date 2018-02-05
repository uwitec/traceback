package com.tmsps.traceback.action_cp.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_sms;
import com.tmsps.traceback.service.SmsService;
import com.tmsps.traceback.util.json.JsonTools;


/**
 * 短信设置
 * 
 * @author 闫无恙
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/sms")
public class SmsController extends ProjBaseAction {
	@Autowired
	private SmsService smsService;

	 //获取短信设置配置信息	
	@RequestMapping("/list_data")
	@ResponseBody
	public String list_data() {
		t_fk_sms list = smsService.findSms();
		result.put("list", list);
		return JsonTools.toJson(result);
	}

	 //保存短信模板
	@RequestMapping("/save_sms")
	@ResponseBody
	public void save_sms(t_fk_sms sms) {
		t_fk_sms smsDB = smsService.findSms();
		smsDB.setAccountsid(sms.getAccountsid());
		smsDB.setAuthtoken(sms.getAuthtoken());
		smsDB.setAppid(sms.getAppid());
		bs.updateObj(smsDB);
		this.setTipMsg("保存成功!", Tip.Type.success);
	}

	/**
	 * 发送测试短信，测试短信参数是否正确
	 * 
	 * @param send_test_sms
	 *            手机号
	 * @param templatecode
	 *            模板编号
	 */
	@RequestMapping("/sms_test")
	@ResponseBody
	public void sms_test(String send_test_sms, String templatecode) {
      
/*		t_sms smsDB = smsService.findSms();
		smsDB.setTemplatecode(templatecode);
		bs.updateObj(smsDB);*/
		boolean result = smsService.sendSms(send_test_sms, templatecode, "0000");

		if (result) {
			this.setTipMsg("发送成功!", Tip.Type.success);
		} else {
			this.setTipMsg("发送失败!", Tip.Type.error);
		}
	}

}
