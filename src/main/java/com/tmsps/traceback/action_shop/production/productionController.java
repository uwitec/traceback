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
import com.tmsps.traceback.model.t_shop_production;
import com.tmsps.traceback.model.t_shop_tag;
import com.tmsps.traceback.service.JdbcService;
import com.tmsps.traceback.service.ShopProductionService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopProduction")
@Scope("prototype")
@RequestMapping("/shop/production")
public class productionController extends ProjBaseAction {

	@Autowired
	private ShopProductionService shopProductionService;
	@Autowired
	private JdbcService jdbcService;
	
	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopProductionService.selectShopProductionList(srh, sort_params, page);
		result.put("list", list);
	}

	@RequestMapping("/Super/list_data")
	@ResponseBody
	public void Super_list_data(String shop_id) {
		List<Map<String, Object>> list = shopProductionService.selectSuperShopProduction(srh, sort_params, page, shop_id);
		result.put("list", list);
	}
	
	@RequestMapping("/tag/list_data")
	@ResponseBody
	public void tag_list_data(String kid) {
		List<Map<String, Object>> list = shopProductionService.selectShopTagList(srh, sort_params, page,kid);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_production shop_production) {
		shop_production.setShop_id(SessionTools.getCurrentShopId());
		shop_production.setProduction_time(Timestamp.valueOf(req.getParameter("dproduction_time")).getTime());
		shop_production.setCertificate_start_time(Timestamp.valueOf(req.getParameter("dcertificate_start_time")).getTime());
		bs.saveObj(shop_production);
		
		//t_shop_product shop_product = shopProductionService.get_shop_product(shop_production.getName());
		//t_shop_touliao_template shop_touliao_template = bs.findById(shop_product.getKid(), t_shop_touliao_template.class); 
		
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_production shop_production = bs.findById(kid, t_shop_production.class);
		shop_production.setStatus(-100);
		bs.updateObj(shop_production);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_production shop_production = bs.findById(kid, t_shop_production.class);
		return JsonTools.toJson(shop_production);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_production shop_production) {
		t_shop_production shop_productionDb = bs.findById(shop_production.getKid(), t_shop_production.class);
		shop_productionDb.setBarcode(shop_production.getBarcode());
		shop_productionDb.setName(shop_production.getName());
		shop_productionDb.setNumber(shop_production.getNumber());
		shop_productionDb.setUnit(shop_production.getUnit());
		shop_productionDb.setSupplier_name(shop_production.getSupplier_name());
		shop_productionDb.setProduction_time(Timestamp.valueOf(req.getParameter("dproduction_time")).getTime());
		shop_productionDb.setCertificate_name(shop_production.getCertificate_name());
		shop_productionDb.setCertificate_start_time(Timestamp.valueOf(req.getParameter("dcertificate_start_time")).getTime());
		shop_productionDb.setCertificate_status(shop_production.getCertificate_status());
		bs.updateObj(shop_productionDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
	
	@RequestMapping("/new_tag")
	@ResponseBody
	public void new_tag(t_shop_tag shop_tag) {
		shop_tag.setProduction_id(req.getParameter("kid"));
		bs.saveObj(shop_tag);
		this.setTipMsg(true, "分配成功!", Tip.Type.success);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update() {
		List<Map<String, Object>> gsscdblist = jdbcService.getProducefood();
		
		System.out.println("总数据："+gsscdblist.size());
		for(int i=0;i<gsscdblist.size();i++) {
				String id = gsscdblist.get(i).get("id").toString();//生产编码
				String mdsename = gsscdblist.get(i).get("mdsename").toString();//产品名称
				String barcode = gsscdblist.get(i).get("barcode").toString();//产品条码
				String quan = gsscdblist.get(i).get("quan").toString();//生产数量
				String packunit = gsscdblist.get(i).get("packunit").toString();//包装单位
				String regdate = gsscdblist.get(i).get("regdate").toString()+" 00:00:00";//生产日期
				String createtime = gsscdblist.get(i).get("createtime").toString()+" 00:00:00";//登记日期
				String licname = gsscdblist.get(i).get("licname").toString();//合格证名称
				String issuetime = gsscdblist.get(i).get("issuetime").toString();//合格证签发日期
				String isvalid = gsscdblist.get(i).get("isvalid").toString();//合格证明是否有效-0、无效；1、有效

				t_shop_production shop_production = new t_shop_production();
				shop_production.setShop_id("R71C4v2bKwQLojquytxUoV");
				shop_production.setCode(id);
				shop_production.setName(mdsename);
				shop_production.setBarcode(barcode);
				shop_production.setNumber(Integer.parseInt(quan.replace(".0", "")));
				shop_production.setUnit(packunit);
				shop_production.setProduction_time(Timestamp.valueOf(regdate).getTime());
				shop_production.setRegister_time(Timestamp.valueOf(createtime).getTime());
				shop_production.setCertificate_name(licname);
				shop_production.setCertificate_start_time(Timestamp.valueOf(issuetime).getTime());
				shop_production.setCertificate_status(isvalid);
				System.out.println("add- 已更新"+i);
				bs.saveObj(shop_production);
		}
		this.setTipMsg(true, "更新成功!", Tip.Type.success);
	}
}
