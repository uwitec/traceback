package com.tmsps.traceback.action_shop.frame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.service.DictService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

/**
 * 唯一性校验通用函数
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/shop/valid")
public class ShopUniqueCheckController extends ProjBaseAction {

	@Autowired
	private DictService dictService;

	@RequestMapping("/check_unique")
	@ResponseBody
	public String check_unique(String table, String field, String value) throws Exception {
		String shop_id = SessionTools.getCurrentShopId();
		boolean b = dictService.selectTableFindValue(shop_id , table, field, value);
		result.put("fail", b + "");
		return JsonTools.toJson(result);
	}

	@RequestMapping("/check_unique_notme")
	@ResponseBody
	public String check_unique_notme(String table, String field, String value, String kid) throws Exception {
		String shop_id = SessionTools.getCurrentShopId();
		boolean b = dictService.selectTableFindValueNotme(shop_id, table, field, value, kid);
		result.put("fail", b + "");
		return JsonTools.toJson(result);
	}
}