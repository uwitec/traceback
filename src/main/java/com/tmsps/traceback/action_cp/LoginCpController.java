package com.tmsps.traceback.action_cp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.model.t_fk_feedback;
import com.tmsps.traceback.service.AdminService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;
import com.tmsps.traceback.web.WebTools;

@Controller
@Scope("prototype")
public class LoginCpController extends ProjBaseAction {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login_cp", method = RequestMethod.POST)
	@ResponseBody
	public String login_cp(String uname, String pwd, String captcha) {
		String codeText = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

		// 校验验证码是否正确
		// if (captcha == null || !captcha.equals(codeText)) {
		// super.setTipMsg(false, "验证码错误！！！", Tip.Type.error);
		// return JsonTools.toJson(result);
		// }	
		req.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (ChkTools.isNull(uname) && ChkTools.isNull(pwd)) {
			super.setTipMsg(false, "登录失败，账号密码错误！", Tip.Type.error);
		}
		t_fk_admin user = adminService.selectUserByEmailAndPwd(uname, pwd);
		if (user != null) {
			if ("限制登陆".equals(user.getStatus_sys())) {
				super.setTipMsg(false, "登录失败，该账户已经被限制登陆！", Tip.Type.error);
				return JsonTools.toJson(result);
			}

			SessionTools.put(SessionTools.LOGIN_USER, user);
			super.setTipMsg(true, "登录成功！", Tip.Type.success);
		} else {
			super.setTipMsg(false, "登录失败，账号密码错误！", Tip.Type.error);
		}
		return JsonTools.toJson(result);
	}

	@RequestMapping("/logout_cp")
	public String logout_cp() {
		t_fk_admin user = SessionTools.getCurrentLoginCpAdmin();
		if (user != null) {
			logger.info("---退出--->" + user.getUname());
		}
		WebTools.getSession().invalidate();
		// req.getSession().invalidate();
		super.setTipMsg("退出成功！", Tip.Type.success);
		return "redirect:login_cp.html";
	}
	
	// 系统反馈提交
	@RequestMapping("/feedback")
	@ResponseBody
	public void feedback(String content) {
		t_fk_feedback feedback = new t_fk_feedback();
		feedback.setContent(content);
		bs.saveObj(feedback);
		this.setTipMsg("操作成功!", Tip.Type.success);
	}
}
