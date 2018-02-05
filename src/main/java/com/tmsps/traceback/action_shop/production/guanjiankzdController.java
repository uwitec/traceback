package com.tmsps.traceback.action_shop.production;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_guanjiankzd;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopGuanjiankzd")
@Scope("prototype")
@RequestMapping("/shop/guanjiankzd")
public class guanjiankzdController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopGuanjiankzdList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_guanjiankzd shop_guanjiankzd) {
		bs.saveObj(shop_guanjiankzd);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_guanjiankzd shop_guanjiankzd = bs.findById(kid, t_shop_guanjiankzd.class);
		shop_guanjiankzd.setStatus(-100);
		bs.updateObj(shop_guanjiankzd);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_guanjiankzd shop_guanjiankzd = bs.findById(kid, t_shop_guanjiankzd.class);
		return JsonTools.toJson(shop_guanjiankzd);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_guanjiankzd shop_guanjiankzd) {
		t_shop_guanjiankzd shop_guanjiankzdDb = bs.findById(shop_guanjiankzd.getKid(), t_shop_guanjiankzd.class);
		shop_guanjiankzdDb.setName(shop_guanjiankzd.getName());
		shop_guanjiankzdDb.setVal(shop_guanjiankzd.getVal());
		shop_guanjiankzdDb.setUnit(shop_guanjiankzd.getUnit());
		bs.updateObj(shop_guanjiankzdDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
