package com.tmsps.traceback.action_shop.setting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_setting;
import com.tmsps.traceback.service.ShopService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopSetting")
@Scope("prototype")
@RequestMapping("/shop/setting")
public class SettingController extends ProjBaseAction {

	@Autowired
	private ShopService shopService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {

		List<Map<String, Object>> list = shopService.selectSettingList();

		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_setting set) {
		set.setShop_id(SessionTools.getCurrentShopId());
		bs.saveObj(set);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_setting set = bs.findById(kid, t_shop_setting.class);
		set.setStatus(-100);
		bs.updateObj(set);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_setting set = bs.findById(kid, t_shop_setting.class);
		return JsonTools.toJson(set);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_setting set) {
		t_shop_setting setDb = bs.findById(set.getKid(), t_shop_setting.class);
		setDb.setField(set.getField());
		setDb.setVal(set.getVal());
		setDb.setNote(set.getNote());
		bs.updateObj(setDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

}
