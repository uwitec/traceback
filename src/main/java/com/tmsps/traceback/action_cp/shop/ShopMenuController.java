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
import com.tmsps.traceback.model.t_shop_menu;
import com.tmsps.traceback.service.ShopMenuService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.tree.TreeTools;

/**
 * 菜单管理
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/shop/menu")
public class ShopMenuController extends ProjBaseAction {

	@Autowired
	private ShopMenuService shopMenuService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopMenuService.selectMenuList(srh, sort_params, page);
		result.put("list", list);
	};
	
	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_menu shop_menu) {
		bs.saveObj(shop_menu);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_menu shop_menu = bs.findById(kid, t_shop_menu.class);
		return JsonTools.toJson(shop_menu);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_menu shop_menu) {
		t_shop_menu shop_menuDb = bs.findById(shop_menu.getKid(), t_shop_menu.class);
		shop_menuDb.setName(shop_menu.getName());
		bs.updateObj(shop_menuDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_menu shop_menu = bs.findById(kid, t_shop_menu.class);
		shop_menu.setStatus(-100);
		bs.updateObj(shop_menu);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}
}
