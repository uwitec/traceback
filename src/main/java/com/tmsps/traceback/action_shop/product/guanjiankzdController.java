package com.tmsps.traceback.action_shop.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_guanjiankzd_template;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopProductGuanjiankzd")
@Scope("prototype")
@RequestMapping("/shop/product/guanjiankzd")
public class guanjiankzdController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopGuanjiankzdTemplateList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_guanjiankzd_template shop_guanjiankzd_template) {
		bs.saveObj(shop_guanjiankzd_template);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_guanjiankzd_template shop_guanjiankzd_template = bs.findById(kid, t_shop_guanjiankzd_template.class);
		shop_guanjiankzd_template.setStatus(-100);
		bs.updateObj(shop_guanjiankzd_template);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_guanjiankzd_template shop_guanjiankzd_template = bs.findById(kid, t_shop_guanjiankzd_template.class);
		return JsonTools.toJson(shop_guanjiankzd_template);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_guanjiankzd_template shop_guanjiankzd_template) {
		t_shop_guanjiankzd_template shop_guanjiankzd_templateDb = bs.findById(shop_guanjiankzd_template.getKid(), t_shop_guanjiankzd_template.class);
		shop_guanjiankzd_templateDb.setName(shop_guanjiankzd_template.getName());
		shop_guanjiankzd_templateDb.setUnit(shop_guanjiankzd_template.getUnit());
		bs.updateObj(shop_guanjiankzd_templateDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
