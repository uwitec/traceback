package com.tmsps.traceback.action_cp.shop;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_menu_auth;
import com.tmsps.traceback.service.ShopMenuService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.tree.TreeTools;

/**
 * 菜单管理
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/shop/menu/auth")
public class ShopMenuAuthController extends ProjBaseAction {

	@Autowired
	private ShopMenuService shopMenuService;

	@RequestMapping("/auth_data_shop")
	@ResponseBody
	public String auth_data_shop(String menu_id) {
		List<Map<String, Object>> list = shopMenuService.selectMenuAuthShopList(menu_id);
		return TreeTools.turnListToTree(list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_menu_auth shop_menu_auth, String up_code) {
		if("root".equals(up_code)) {
			shop_menu_auth.setCode(shop_menu_auth.getCode());
		}else {
			shop_menu_auth.setCode(up_code + shop_menu_auth.getCode());
		}
		bs.saveObj(shop_menu_auth);
		
		// logT.logH("保存权限", t_fk_auth.class, auth.getKid());

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_menu_auth shop_menu_auth = (t_shop_menu_auth) bs.findById(kid, t_shop_menu_auth.class);
		return JsonTools.toJson(shop_menu_auth);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_menu_auth shop_menu_auth) {
		t_shop_menu_auth shop_menu_authDb = (t_shop_menu_auth) bs.findById(shop_menu_auth.getKid(), t_shop_menu_auth.class);
		// DbLogTools.log(authDb);
		shop_menu_authDb.setName(shop_menu_auth.getName());
		shop_menu_authDb.setCode(shop_menu_auth.getCode());
		shop_menu_authDb.setIcon(shop_menu_auth.getIcon());
		shop_menu_authDb.setType(shop_menu_auth.getType());
		shop_menu_authDb.setUrl(shop_menu_auth.getUrl());
		bs.updateObj(shop_menu_authDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_menu_auth shop_menu_auth = bs.findById(kid, t_shop_menu_auth.class);
		shop_menu_auth.setStatus(-100);
		bs.updateObj(shop_menu_auth);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/auth_unique")
	@ResponseBody
	public String auth_unique(String kid, String code, String menu_id) {
		t_shop_menu_auth shop_menu_auth = null;

		if (ChkTools.isNull(kid)) {
			shop_menu_auth = shopMenuService.findAuthByCode(code,menu_id);
		} else {
			shop_menu_auth = shopMenuService.findAuthByCode(kid, code ,menu_id);
		}

		if (shop_menu_auth == null) {
			result.put("success", "true");
		} else {
			result.put("fail", "true");
		}

		return JsonTools.toJson(result);
	}

}
