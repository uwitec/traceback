package com.tmsps.traceback.action_cp.area;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.service.AreaService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.tree.TreeTools;

/**
 * 区域列表
 * 
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("/cp/area")
public class AreaController extends ProjBaseAction {

	@Autowired
	private AreaService areaService;

	@RequestMapping("/area_data")
	@ResponseBody
	public String area_data(int deep, int parent_id) {

		List<Map<String, Object>> list = areaService.selectAreaRootList(deep, parent_id);
		return JsonTools.toJson(TreeTools.handleAreaTree(list, false, deep));
	}

	@RequestMapping("/area_list_data")
	@ResponseBody
	public String area_list_data(int parent_id, int deep) {

		List<Map<String, Object>> list = areaService.selectAreaList(parent_id, deep);

		return JsonTools.toJson(list);
	}

	private static final String area_code="";
	@RequestMapping("/area_data_all")
	@ResponseBody
	public String area_data_all() {
//		List<Map<String, Object>> rootList = areaService.selectAllAreaList();
//		System.err.println(JsonTools.toJson(AreaTreeTools.turnListToTree(rootList)));
		return area_code;
	}
}
