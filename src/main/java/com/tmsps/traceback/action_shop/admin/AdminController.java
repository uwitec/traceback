package com.tmsps.traceback.action_shop.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.ne4spring.utils.ChkUtil;
import com.tmsps.ne4spring.utils.MD5Util;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_admin;
import com.tmsps.traceback.service.ShopAdminService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopAdmin")
@Scope("prototype")
@RequestMapping("/shop/admin")
public class AdminController extends ProjBaseAction {
	@Autowired
	private ShopAdminService shopAdminService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopAdminService.selectShopAdminList(srh, sort_params, page);
		result.put("list", list);
	}

	@RequestMapping("/list_data_combox")
	@ResponseBody
	public void list_data_combox() {
		
		List<Map<String, Object>> list = shopAdminService.selectShopAdminList();
		
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_admin admin) {
		admin.setType("管理员");
		admin.setShop_id(SessionTools.getCurrentShopId());
		admin.setPwd(MD5Util.MD5(admin.getPwd()));
		bs.saveObj(admin);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_admin admin = bs.findById(kid, t_shop_admin.class);

		admin.setStatus(-100);
		bs.updateObj(admin);

		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	/**
	 * 修改密码
	 * 
	 * @author chaixin
	 * @param currentpwd
	 *            当前密码
	 * @param newpwdone
	 *            修改密码
	 */
	@RequestMapping("/pwd_form")
	@ResponseBody
	public void pwd(String currentpwd, String confirm_password) {
		t_shop_admin currentUser = SessionTools.getCurrentShopAdmin();
		if (MD5Util.MD5(currentpwd).equals(currentUser.getPwd())) {
			t_shop_admin userDb = (t_shop_admin) bs.findById(currentUser.getKid(), t_shop_admin.class);
			userDb.setPwd(MD5Util.MD5(confirm_password));
			bs.updateObj(userDb);
			super.setTipMsg(true, "修改密码成功", Tip.Type.success);
		} else {
			super.setTipMsg(true, "当前密码错误", Tip.Type.error);
		}

	}

	@RequestMapping("/limit")
	@ResponseBody
	public void limit(String kid) {

		t_shop_admin adminDb = bs.findById(kid, t_shop_admin.class);

		if ("限制登陆".equals(adminDb.getStatus_sys())) {
			adminDb.setStatus_sys("正常状态");
			bs.updateObj(adminDb);
			this.setTipMsg(true, "解除登陆限制成功!", Tip.Type.success);
		} else {
			adminDb.setStatus_sys("限制登陆");
			bs.updateObj(adminDb);
			this.setTipMsg(true, "限制登陆成功!", Tip.Type.success);
		}
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_admin admin = bs.findById(kid, t_shop_admin.class);
		admin.setPwd(null);

		result.put("admin", admin);
		return JsonTools.toJson(result);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_admin admin) {
		t_shop_admin adminDb = bs.findById(admin.getKid(), t_shop_admin.class);
		adminDb.setName(admin.getName());
		adminDb.setMobile(admin.getMobile());
		adminDb.setRole_id(admin.getRole_id());
		if (ChkUtil.isNotNull(admin.getPwd())) {
			adminDb.setPwd(MD5Util.MD5(admin.getPwd()));
		}
		bs.updateObj(adminDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

}
