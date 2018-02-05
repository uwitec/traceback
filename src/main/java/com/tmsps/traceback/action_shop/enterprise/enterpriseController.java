package com.tmsps.traceback.action_shop.enterprise;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.model.t_shop_shengcanlicense;
import com.tmsps.traceback.model.t_shop_yingyelicense;
import com.tmsps.traceback.service.ShopService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller("shopEnterprise")
@Scope("prototype")
@RequestMapping("/shop/enterprise")
public class enterpriseController extends ProjBaseAction {

	@Autowired
	private ShopService shopService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopService.selectTaskShopList(srh, sort_params, page);
		result.put("list", list);
	}
	
	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop shop = bs.findById(kid, t_shop.class);
		t_shop_yingyelicense shop_yingyelicense = bs.findById(shop.getYingye_id(), t_shop_yingyelicense.class);
		t_shop_shengcanlicense shop_shengcanlicense = bs.findById(shop.getShengcan_id(), t_shop_shengcanlicense.class);
		
		result.put("shop", shop);
		result.put("shop_yingyelicense", shop_yingyelicense);
		result.put("shop_shengcanlicense", shop_shengcanlicense);
		return JsonTools.toJson(result);
	}

//	@RequestMapping("/edit")
//	@ResponseBody
//	public void edit(t_shop_ingoods shop_ingoods) {
//		t_shop_ingoods shop_ingoodsDb = bs.findById(shop_ingoods.getKid(), t_shop_ingoods.class);
//		bs.updateObj(shop_ingoodsDb);
//		this.setTipMsg(true, "保存成功!", Tip.Type.success);
//	}

}
