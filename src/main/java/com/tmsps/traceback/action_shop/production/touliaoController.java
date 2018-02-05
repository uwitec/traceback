package com.tmsps.traceback.action_shop.production;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_shop_touliao;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopTouliao")
@Scope("prototype")
@RequestMapping("/shop/touliao")
public class touliaoController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopTouliaoList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_touliao shop_touliao) {
		shop_touliao.setDosing_time(Timestamp.valueOf(req.getParameter("ddosing_time")).getTime());
		bs.saveObj(shop_touliao);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_touliao shop_touliao = bs.findById(kid, t_shop_touliao.class);
		shop_touliao.setStatus(-100);
		bs.updateObj(shop_touliao);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_touliao shop_touliao = bs.findById(kid, t_shop_touliao.class);
		return JsonTools.toJson(shop_touliao);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_touliao shop_touliao) {
		t_shop_touliao shop_touliaoDb = bs.findById(shop_touliao.getKid(), t_shop_touliao.class);
		shop_touliaoDb.setName(shop_touliao.getName());
		shop_touliaoDb.setBatch(shop_touliao.getBatch());
		shop_touliaoDb.setNumber(shop_touliao.getNumber());
		shop_touliaoDb.setUnit(shop_touliao.getUnit());
		shop_touliaoDb.setDosing_time(Timestamp.valueOf(req.getParameter("ddosing_time")).getTime());
		shop_touliaoDb.setScaleman(shop_touliao.getScaleman());
		bs.updateObj(shop_touliaoDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
