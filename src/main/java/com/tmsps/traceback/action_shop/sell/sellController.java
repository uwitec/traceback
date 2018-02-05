package com.tmsps.traceback.action_shop.sell;

import java.io.IOException;
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
import com.tmsps.traceback.model.t_shop_sell;
import com.tmsps.traceback.service.JdbcService;
import com.tmsps.traceback.service.ShopSellService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

@Controller("shopSell")
@Scope("prototype")
@RequestMapping("/shop/sell")
public class sellController extends ProjBaseAction {

	@Autowired
	private ShopSellService shopSellService;
	@Autowired
	private JdbcService jdbcService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {
		List<Map<String, Object>> list = shopSellService.selectShopSellList(srh, sort_params, page);
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_shop_sell shop_sell) {
		shop_sell.setShop_id(SessionTools.getCurrentShopId());
		shop_sell.setProduction_time(Timestamp.valueOf(req.getParameter("dproduction_time")).getTime());
		bs.saveObj(shop_sell);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_shop_sell shop_sell = bs.findById(kid, t_shop_sell.class);
		shop_sell.setStatus(-100);
		bs.updateObj(shop_sell);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_shop_sell shop_sell = bs.findById(kid, t_shop_sell.class);
		return JsonTools.toJson(shop_sell);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_shop_sell shop_sell) {
		t_shop_sell shop_sellDb = bs.findById(shop_sell.getKid(), t_shop_sell.class);
		shop_sellDb.setBarcode(shop_sell.getBarcode());
		shop_sellDb.setName(shop_sell.getName());
		shop_sellDb.setNumber(shop_sell.getNumber());
		shop_sellDb.setUnit(shop_sell.getUnit());
		shop_sellDb.setSell_time(Timestamp.valueOf(req.getParameter("dsell_time")).getTime());
		shop_sellDb.setProduction_time(Timestamp.valueOf(req.getParameter("dproduction_time")).getTime());
		shop_sellDb.setTixone_code(shop_sell.getTixone_code());
		shop_sellDb.setCreated_bill_time(Timestamp.valueOf(req.getParameter("dcreated_bill_time")).getTime());
		shop_sellDb.setDistributor_name(shop_sell.getDistributor_name());
		bs.updateObj(shop_sellDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update() {
		List<Map<String, Object>> gsscdblist = jdbcService.getSalefood();
		
		System.out.println("总数据："+gsscdblist.size());
		for(int i=0;i<gsscdblist.size();i++) {
				String id = gsscdblist.get(i).get("id").toString();//生产编码
				String mdsename = gsscdblist.get(i).get("mdsename").toString();//产品名称
				String barcode = gsscdblist.get(i).get("barcode").toString();//产品条码
				String quan = gsscdblist.get(i).get("quan").toString();//生产数量
				String packunit = gsscdblist.get(i).get("unit").toString();//包装单位
				String regdate = gsscdblist.get(i).get("regdate").toString()+" 00:00:00";//登记日期
				String lotnumber = gsscdblist.get(i).get("lotnumber").toString()+" 00:00:00";//批次

				String billno = gsscdblist.get(i).get("billno").toString();//账单号
				String createtime = gsscdblist.get(i).get("createtime").toString();//创建时间
				String supplyenter = gsscdblist.get(i).get("supplyenter").toString();//供货商

				t_shop_sell shop_sell = new t_shop_sell();
				shop_sell.setShop_id("R71C4v2bKwQLojquytxUoV");
				shop_sell.setCode(id);
				shop_sell.setName(mdsename);
				shop_sell.setBarcode(barcode);
				shop_sell.setNumber(Integer.parseInt(quan.replace(".0", "")));
				shop_sell.setUnit(packunit);
				shop_sell.setSell_time(Timestamp.valueOf(regdate).getTime());
				shop_sell.setProduction_time(Timestamp.valueOf(lotnumber).getTime());
				shop_sell.setTixone_code(billno);
				shop_sell.setCreated_bill_time(Timestamp.valueOf(createtime).getTime());
				shop_sell.setDistributor_name(supplyenter);
				bs.saveObj(shop_sell);
				System.out.println("add- 已更新"+i);
		}
		this.setTipMsg(true, "更新成功!", Tip.Type.success);
	}
	
	@RequestMapping("/qrcode")
	@ResponseBody
	public String qrcode(String tixone_code) throws IOException {
		String img_id = shopSellService.getQRcodeFile(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/traceback/moblie/sell/tixone.htm?tixone_code="+tixone_code);
		return img_id;
	}

}
