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
import com.tmsps.traceback.model.t_shop_jianyan;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopJianyan")
@Scope("prototype")
@RequestMapping("/shop/jianyan")
public class jianyanController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopJianyanList(srh, sort_params, page, kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_jianyan shop_jianyan) {
		shop_jianyan.setJy_time(Timestamp.valueOf(req.getParameter("djy_time")).getTime());
		bs.saveObj(shop_jianyan);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_jianyan shop_jianyan = bs.findById(kid, t_shop_jianyan.class);
		shop_jianyan.setStatus(-100);
		bs.updateObj(shop_jianyan);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_jianyan shop_jianyan = bs.findById(kid, t_shop_jianyan.class);
		return JsonTools.toJson(shop_jianyan);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_jianyan shop_jianyan) {
		t_shop_jianyan shop_jianyanDb = bs.findById(shop_jianyan.getKid(), t_shop_jianyan.class);
		shop_jianyanDb.setName(shop_jianyan.getName());
		shop_jianyanDb.setType(shop_jianyan.getType());
		shop_jianyanDb.setVal(shop_jianyan.getVal());
		shop_jianyanDb.setStaff(shop_jianyan.getStaff());
		shop_jianyanDb.setJy_time(Timestamp.valueOf(req.getParameter("djy_time")).getTime());
		shop_jianyanDb.setResult(shop_jianyan.getResult());
		bs.updateObj(shop_jianyanDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
}
