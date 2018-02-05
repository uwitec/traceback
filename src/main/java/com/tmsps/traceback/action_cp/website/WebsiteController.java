package com.tmsps.traceback.action_cp.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_website;
import com.tmsps.traceback.service.DbService;
import com.tmsps.traceback.service.WebsiteService;
import com.tmsps.traceback.util.json.JsonTools;


/**
 * 站点设置
 * @author 张志亮
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/website")
public class WebsiteController extends ProjBaseAction {
	@Autowired
	WebsiteService websiteService;
	
	@Autowired
	DbService dbService; 
	
	// 获取数据
	@RequestMapping("/edit_form")
	@ResponseBody
	public void edit_form() {
		t_fk_website website = websiteService.findWebsite();
		result.put("website", website);
	}
	// 修改站点数据
	@RequestMapping("/update")
	@ResponseBody
	public void update(t_fk_website web, String main_file_id) {

		t_fk_website website = websiteService.findWebsite();
		website.setLogo_file_id(main_file_id);
		website.setName(web.getName());
		website.setCnzz(web.getCnzz());
		website.setIcp(web.getIcp());
		website.setNote(web.getNote());
		website.setText(web.getText());
		bs.updateObj(website);
		this.setTipMsg("修改成功!", Tip.Type.success);
	}
	
	//校验 Db 与 model 的对应关系
	@RequestMapping("/valid_db")
	@ResponseBody
	public String valid_db() {

		dbService.valildDbAndModel();
		List<String> errorList = dbService.valildDbAndModel();
		result.put("errorList", errorList);
		
		return JsonTools.toJson(result); 
		
	}

}