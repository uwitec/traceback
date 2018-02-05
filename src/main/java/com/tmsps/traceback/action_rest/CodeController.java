package com.tmsps.traceback.action_rest;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tmsps.traceback.base.action.ProjBaseAction;


@Controller
@Scope("prototype")
@RequestMapping("/code")
public class CodeController extends ProjBaseAction {

	@Autowired
	private Producer captchaProducer = null;

	@RequestMapping("/get")
	public ModelAndView get() throws Exception {
		HttpSession session = req.getSession();

		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		resp.setHeader("Pragma", "no-cache");
		resp.setContentType("image/jpeg");

		String codeText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, codeText);
		BufferedImage bi = captchaProducer.createImage(codeText);
		ServletOutputStream out = resp.getOutputStream();

		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping("/check")
	@ResponseBody
	public String check(String captcha) {
		// 用户输入的验证码的值
		String codeText = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		// System.err.println("session code -->"+codeText+",session id-->
		// "+req.getSession().getId());
		// 校验验证码是否正确
		if (captcha == null || !captcha.equals(codeText)) {
			return "false";// 返回验证码错误
		}

		return "true"; // 校验通过返回成功
	}

	
}
