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
import com.tmsps.traceback.model.t_shop_shengcangc;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopShengcangc")
@Scope("prototype")
@RequestMapping("/shop/shengcangc")
public class shengcangcController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopShengcangcList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_shengcangc shop_shengcangc) {
		shop_shengcangc.setStart_time(Timestamp.valueOf(req.getParameter("dstart_time")).getTime());
		shop_shengcangc.setEnd_time(Timestamp.valueOf(req.getParameter("dend_time")).getTime());
		bs.saveObj(shop_shengcangc);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_shengcangc shop_shengcangc = bs.findById(kid, t_shop_shengcangc.class);
		shop_shengcangc.setStatus(-100);
		bs.updateObj(shop_shengcangc);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_shengcangc shop_shengcangc = bs.findById(kid, t_shop_shengcangc.class);
		return JsonTools.toJson(shop_shengcangc);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_shengcangc shop_shengcangc) {
		t_shop_shengcangc shop_shengcangcDb = bs.findById(shop_shengcangc.getKid(), t_shop_shengcangc.class);
		shop_shengcangcDb.setName(shop_shengcangc.getName());
		shop_shengcangcDb.setStep(new Integer(shop_shengcangc.getStep()));
		shop_shengcangcDb.setStart_time(Timestamp.valueOf(req.getParameter("dstart_time")).getTime());
		shop_shengcangcDb.setEnd_time(Timestamp.valueOf(req.getParameter("dend_time")).getTime());
		shop_shengcangcDb.setOperator(shop_shengcangc.getOperator());
		bs.updateObj(shop_shengcangcDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
