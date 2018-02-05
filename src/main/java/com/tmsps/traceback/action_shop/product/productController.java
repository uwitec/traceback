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
import com.tmsps.traceback.model.t_shop_product;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopProduct")
@Scope("prototype")
@RequestMapping("/shop/product")
public class productController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopProductionService.selectShopProductList(srh, sort_params, page);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_product shop_product) {
		shop_product.setShop_id(SessionTools.getCurrentShopId());
		bs.saveObj(shop_product);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_product shop_product = bs.findById(kid, t_shop_product.class);
		shop_product.setStatus(-100);
		bs.updateObj(shop_product);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_product shop_product = bs.findById(kid, t_shop_product.class);
		return JsonTools.toJson(shop_product);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_product shop_product) {
		t_shop_product shop_productDb = bs.findById(shop_product.getKid(), t_shop_product.class);
		shop_productDb.setName(shop_product.getName());
		shop_productDb.setUnit(shop_product.getUnit());
		bs.updateObj(shop_productDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}	

}
