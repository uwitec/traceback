package com.tmsps.traceback.action_shop.frame;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.model.t_shop;
import com.tmsps.traceback.model.t_shop_admin;
import com.tmsps.traceback.model.t_shop_menu_auth;
import com.tmsps.traceback.model.t_shop_production;
import com.tmsps.traceback.model.t_shop_role;
import com.tmsps.traceback.model.t_shop_shengcanlicense;
import com.tmsps.traceback.model.t_shop_yingyelicense;
import com.tmsps.traceback.service.ShopMenuService;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopFrame")
@Scope("prototype")
@RequestMapping("/shop/frame")
public class FrameController extends ProjBaseAction {

	@Autowired
	private ShopMenuService menuService;
	@Autowired
	private ShopProductionService shopProductionService;

	@RequestMapping("/frame")
	public ModelAndView frame() {
		t_shop shop = SessionTools.getCurrentShop();
		t_shop_admin admin = SessionTools.getCurrentShopAdmin();
		List<Map<String, Object>> sublist;
		if (admin.getKid().equals(shop.getAdmin_id())) {
			sublist = menuService.selectAllMenuList(shop.getShop_menu_id());
		} else {
			t_shop_role role = bs.findById(admin.getRole_id(), t_shop_role.class);
			logger.info(role.getCodes());
			sublist = menuService.selectAllMenuList(shop.getShop_menu_id(),role.getCodes());
		}

		logger.info(sublist.toString());
		ModelAndView mv = new ModelAndView("/jsp_shop/frame/frame");
		mv.addObject("sublist", sublist);
		mv.addObject("index", sublist.get(0));
		mv.addObject("admin", admin);
		mv.addObject("shop", shop);
		return mv;
	}
	
	@RequestMapping("/index_v1")
	public ModelAndView index() {
		t_shop shop = SessionTools.getCurrentShop();
		t_shop_shengcanlicense sclicense = bs.findById(shop.getShengcan_id(), t_shop_shengcanlicense.class);
		t_shop_yingyelicense yylicense = bs.findById(shop.getYingye_id(), t_shop_yingyelicense.class);
		ModelAndView mv = new ModelAndView("/jsp_shop/frame/index_v1");
		mv.addObject("sclicense", sclicense);
		mv.addObject("yylicense", yylicense);
		mv.addObject("shop", shop);
		return mv;
	}
	
	@RequestMapping("/password")
	public ModelAndView password() {
		t_shop shop = SessionTools.getCurrentShop();
		t_shop_admin admin = SessionTools.getCurrentShopAdmin();
		ModelAndView mv = new ModelAndView("/jsp_shop/frame/password");
		mv.addObject("admin", admin);
		mv.addObject("shop", shop);
		return mv;
	}

	@RequestMapping("/table")
	public ModelAndView table(String menu) {
		t_shop_menu_auth shop_menu_auth = bs.findById(menu, t_shop_menu_auth.class);
		List<Map<String, Object>> table_list = menuService.selectMenuRowList(shop_menu_auth.getKid());
		ModelAndView mv = new ModelAndView("/jsp_shop/teble/list");
		System.out.println(table_list.toString().replace("t=", "t:'").replace(", d", "', d").replace("dex=", "dex='").replace(", f", "', align:'center', f").replace("=", ":"));
		mv.addObject("table_list" , table_list.toString().replace("t=", "t:'").replace(", d", "', d").replace("dex=", "dex='").replace(", f", "', align:'center', f").replace("=", ":"));
		return mv;
	}

	
	@RequestMapping("/trace/index")
	public ModelAndView traceindex(String kid) {
		t_shop_production shop_production = bs.findById(kid, t_shop_production.class);
		
		List<Map<String, Object>> shop_touliao = shopProductionService.selectShopTouliaoList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_guanjiankzd = shopProductionService.selectShopGuanjiankzdList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_jianyan = shopProductionService.selectShopJianyanList(srh, sort_params, page, shop_production.getKid());
		sort_params.put("step", "ASC");
		List<Map<String, Object>> shop_shengcangc = shopProductionService.selectShopShengcangcList(srh, sort_params, page, shop_production.getKid());
		ModelAndView mv = new ModelAndView("/jsp_shop/trace/index");
		mv.addObject("production", shop_production);
		mv.addObject("touliao", shop_touliao);
		mv.addObject("guanjiankzd", shop_guanjiankzd);
		mv.addObject("jianyan", shop_jianyan);
		mv.addObject("shengcangc", shop_shengcangc);
		return mv;
	}
	
	@RequestMapping("/trace/inquiry")
	public ModelAndView trace_inquiry(String trace_code) {
		ModelAndView mv = new ModelAndView("/jsp_shop/trace/inquiry");
		return mv;
	}

	@RequestMapping("/trace/inquirypc")
	public ModelAndView trace_inquirypc(String trace_code) {
		ModelAndView mv = new ModelAndView("/jsp_shop/trace/inquirypc");
		return mv;
	}

	@RequestMapping("/trace/inquiry_detail")
	public ModelAndView trace_inquiry_detail(String trace_code) {
		t_shop_production shop_production = shopProductionService.find_trace_code(trace_code);
		t_shop shop = bs.findById(shop_production.getShop_id(), t_shop.class);
		t_shop_shengcanlicense shengcanlicense = bs.findById(shop.getShengcan_id(), t_shop_shengcanlicense.class);
		t_shop_yingyelicense yingyelicense = bs.findById(shop.getYingye_id(), t_shop_yingyelicense.class);
		
		List<Map<String, Object>> shop_touliao = shopProductionService.selectShopTouliaoList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_guanjiankzd = shopProductionService.selectShopGuanjiankzdList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_jianyan = shopProductionService.selectShopJianyanList(srh, sort_params, page, shop_production.getKid());
		sort_params.put("step", "ASC");
		List<Map<String, Object>> shop_shengcangc = shopProductionService.selectShopShengcangcList(srh, sort_params, page, shop_production.getKid());
		ModelAndView mv = new ModelAndView("/jsp_shop/trace/inquiry_detail");
		mv.addObject("shop", shop);
		mv.addObject("production", shop_production);
		mv.addObject("touliao", shop_touliao);
		mv.addObject("guanjiankzd", shop_guanjiankzd);
		mv.addObject("jianyan", shop_jianyan);
		mv.addObject("shengcangc", shop_shengcangc);
		mv.addObject("shengcanlicense", shengcanlicense);
		mv.addObject("yingyelicense", yingyelicense);
		return mv;
	}
	
	@RequestMapping("/trace/inquiry_detial_pc")
	public ModelAndView trace_inquiry_detail_pc(String trace_code) {
		t_shop_production shop_production = shopProductionService.find_trace_code(trace_code);
		t_shop shop = bs.findById(shop_production.getShop_id(), t_shop.class);
		t_shop_shengcanlicense shengcanlicense = bs.findById(shop.getShengcan_id(), t_shop_shengcanlicense.class);
		t_shop_yingyelicense yingyelicense = bs.findById(shop.getYingye_id(), t_shop_yingyelicense.class);
		
		List<Map<String, Object>> shop_touliao = shopProductionService.selectShopTouliaoList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_guanjiankzd = shopProductionService.selectShopGuanjiankzdList(srh, sort_params, page, shop_production.getKid());
		List<Map<String, Object>> shop_jianyan = shopProductionService.selectShopJianyanList(srh, sort_params, page, shop_production.getKid());
		sort_params.put("step", "ASC");
		List<Map<String, Object>> shop_shengcangc = shopProductionService.selectShopShengcangcList(srh, sort_params, page, shop_production.getKid());
		ModelAndView mv = new ModelAndView("/jsp_shop/trace/inquiry_detial_pc");
		mv.addObject("shop", shop);
		mv.addObject("production", shop_production);
		mv.addObject("touliao", shop_touliao);
		mv.addObject("guanjiankzd", shop_guanjiankzd);
		mv.addObject("jianyan", shop_jianyan);
		mv.addObject("shengcangc", shop_shengcangc);
		mv.addObject("shengcanlicense", shengcanlicense);
		mv.addObject("yingyelicense", yingyelicense);
		return mv;
	}
}