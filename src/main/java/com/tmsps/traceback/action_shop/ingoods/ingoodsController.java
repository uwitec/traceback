package com.tmsps.traceback.action_shop.ingoods;

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
import com.tmsps.traceback.model.t_shop_ingoods;
import com.tmsps.traceback.service.ShopIngoodsService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopIngoods")
@Scope("prototype")
@RequestMapping("/shop/ingoods")
public class ingoodsController extends ProjBaseAction {

	@Autowired
	private ShopIngoodsService shopIngoodsService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopIngoodsService.selectShopIngoodsList(srh, sort_params, page);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_ingoods shop_ingoods) {
		shop_ingoods.setShop_id(SessionTools.getCurrentShopId());
		shop_ingoods.setIngoods_time(Timestamp.valueOf(req.getParameter("dingoods_time")).getTime());
		shop_ingoods.setCertificate_start_time(Timestamp.valueOf(req.getParameter("dcertificate_start_time")).getTime());
		bs.saveObj(shop_ingoods);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_ingoods shop_ingoods = bs.findById(kid, t_shop_ingoods.class);
		shop_ingoods.setStatus(-100);
		bs.updateObj(shop_ingoods);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_ingoods shop_ingoods = bs.findById(kid, t_shop_ingoods.class);
		return JsonTools.toJson(shop_ingoods);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_ingoods shop_ingoods) {
		t_shop_ingoods shop_ingoodsDb = bs.findById(shop_ingoods.getKid(), t_shop_ingoods.class);
		shop_ingoodsDb.setBarcode(shop_ingoods.getBarcode());
		shop_ingoodsDb.setName(shop_ingoods.getName());
		shop_ingoodsDb.setIngoods_time(Timestamp.valueOf(req.getParameter("dingoods_time")).getTime());
		shop_ingoodsDb.setBatch(shop_ingoods.getBatch());
		shop_ingoodsDb.setNumber(shop_ingoods.getNumber());
		shop_ingoodsDb.setUnit(shop_ingoods.getUnit());
		shop_ingoodsDb.setSupplier_name(shop_ingoods.getSupplier_name());
		shop_ingoodsDb.setCertificate_name(shop_ingoods.getCertificate_name());
		shop_ingoodsDb.setCertificate_start_time(Timestamp.valueOf(req.getParameter("dcertificate_start_time")).getTime());
		shop_ingoodsDb.setCertificate_status(shop_ingoods.getCertificate_status());
		bs.updateObj(shop_ingoodsDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
