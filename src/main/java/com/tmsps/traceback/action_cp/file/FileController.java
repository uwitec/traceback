package com.tmsps.traceback.action_cp.file;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_file;
import com.tmsps.traceback.service.FileService;
import com.tmsps.traceback.util.json.JsonTools;

@Controller
@RequestMapping("/cp/file")
@Scope("prototype")
public class FileController extends ProjBaseAction {

	@Autowired
	FileService fileService;
//	@RequestMapping("/list")
//	public ModelAndView list(){
//		System.out.println("===========================");
//		ModelAndView mv = new ModelAndView("/view_cp/file/list");
//		return mv;
//	}
//	
	@RequestMapping("/list_data")
	@ResponseBody
	public String list_data(String query, String qtype){
		List<Map<String,Object>> list = fileService.selectFileList(query, qtype,srh, sort_params, page);
		JSONObject rows = new JSONObject();
		rows.put("page", page.getPageNumber());
		rows.put("total", page.getTotalRow());
		rows.put("list", list);
		return JsonTools.toJson(rows);
	}

	
	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid){
		t_fk_file fileDB = fileService.findFileByKid(kid);
		fileDB.setStatus(-100);
		bs.updateObj(fileDB);
		this.setTipMsg("文件删除成功！", Tip.Type.success);
	}
	
	
	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_fk_file fkFile = bs.findById(kid, t_fk_file.class);
		return JsonTools.toJson(fkFile);
	}
}

