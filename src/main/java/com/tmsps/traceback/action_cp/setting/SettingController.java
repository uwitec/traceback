package com.tmsps.traceback.action_cp.setting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_setting;
import com.tmsps.traceback.service.SettingService;
import com.tmsps.traceback.util.json.JsonTools;

// 曹家榜添加 系统参数配置功能 2016年7 月9号 

@Controller
@Scope("prototype")
@RequestMapping("/cp/setting")
public class SettingController extends ProjBaseAction {

	@Autowired
	private SettingService settingService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {

		List<Map<String, Object>> list = settingService.selectSettingListNoParam();

		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_setting set) {
		bs.saveObj(set);
		
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_fk_setting set = bs.findById(kid, t_fk_setting.class);
		set.setStatus(-100);
		// bs.deleteObjById(kid, t_fk_setting.class);
		bs.updateObj(set);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_fk_setting set = bs.findById(kid, t_fk_setting.class);
		return JsonTools.toJson(set);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_setting set) {
		t_fk_setting setDb = bs.findById(set.getKid(), t_fk_setting.class);
		setDb.setField(set.getField());
		setDb.setVal(set.getVal());
		setDb.setNote(set.getNote());
		bs.updateObj(setDb);

		// if ("CLIENT_VERSION".equals(setDb.getField())) {
		// UpgradeTools.makeVersion(ChkTools.getInteger(setDb.getVal()));
		// }

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

}
