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
import com.tmsps.traceback.model.t_shop_touliao_template;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopProductTouliao")
@Scope("prototype")
@RequestMapping("/shop/product/touliao")
public class touliaoController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopTouliaoTemplateList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_touliao_template shop_touliao_template) {
		bs.saveObj(shop_touliao_template);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
		public void del(String kid) {
		t_shop_touliao_template shop_touliao_template = bs.findById(kid, t_shop_touliao_template.class);
		shop_touliao_template.setStatus(-100);
		bs.updateObj(shop_touliao_template);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_touliao_template shop_touliao_template = bs.findById(kid, t_shop_touliao_template.class);
		return JsonTools.toJson(shop_touliao_template);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_touliao_template shop_touliao_template) {
		t_shop_touliao_template shop_touliao_templateDb = bs.findById(shop_touliao_template.getKid(), t_shop_touliao_template.class);
		shop_touliao_templateDb.setName(shop_touliao_template.getName());
		shop_touliao_templateDb.setNumber(shop_touliao_template.getNumber());
		shop_touliao_templateDb.setUnit(shop_touliao_template.getUnit());
		bs.updateObj(shop_touliao_templateDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
