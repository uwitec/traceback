package com.tmsps.traceback.action_moblie;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.service.ShopSellService;

@Controller("moblieSell")
@Scope("prototype")
@RequestMapping("/moblie/sell")
public class MFrameController extends ProjBaseAction {
	
	@Autowired
	private ShopSellService shopSellService;
	
	@RequestMapping("/tixone")
	public ModelAndView tixone(String tixone_code) {
		List<Map<String, Object>> list = shopSellService.selectSellTixoneList(tixone_code);
		t_shop shop = bs.findById(list.get(0).get("shop_id"), t_shop.class);
		ModelAndView mv = new ModelAndView("/jsp_moblie/frame/menu");
		mv.addObject("list", list);
		mv.addObject("list1", list.get(0));
		mv.addObject("shop", shop);
		return mv;
	}

}