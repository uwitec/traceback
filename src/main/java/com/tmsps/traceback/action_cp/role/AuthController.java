package com.tmsps.traceback.action_cp.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_auth;
import com.tmsps.traceback.service.AuthService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.tree.TreeTools;

/**
 * 权限列表
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/auth")
public class AuthController extends ProjBaseAction {

	@Autowired
	private AuthService authService;

	@RequestMapping("/auth_data_sys")
	@ResponseBody
	public String auth_data_sys() {
		List<Map<String, Object>> list = authService.selectAuthSysList();
		return TreeTools.turnListToTree(list);
	}

	@RequestMapping("/auth_data_shop")
	@ResponseBody
	public String auth_data_shop() {
		List<Map<String, Object>> list = authService.selectAuthShopList();
		return TreeTools.turnListToTree(list);
	}

	@RequestMapping("/auth_data_checkbox")
	@ResponseBody
	public String auth_data_checkbox() {
		List<Map<String, Object>> list = authService.selectSystemAuthList("cp系统");
		return TreeTools.turnListToTree(list, true);
	}

	@RequestMapping("/list_data_combox")
	@ResponseBody
	public void list_data_combox() {
		List<Map<String, Object>> list = authService.selectSystemAuthList("cp系统");
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_auth auth, String up_code) {
		auth.setCode(up_code + auth.getCode());
		bs.saveObj(auth);

		// logT.logH("保存权限", t_fk_auth.class, auth.getKid());

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_fk_auth auth = (t_fk_auth) bs.findById(kid, t_fk_auth.class);
		return JsonTools.toJson(auth);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_auth auth) {
		t_fk_auth authDb = (t_fk_auth) bs.findById(auth.getKid(), t_fk_auth.class);
		// DbLogTools.log(authDb);
		authDb.setName(auth.getName());
		authDb.setCode(auth.getCode());
		authDb.setIcon(auth.getIcon());
		authDb.setType(auth.getType());
		authDb.setSystem(auth.getSystem());
		authDb.setUrl(auth.getUrl());
		bs.updateObj(authDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_fk_auth auth = bs.findById(kid, t_fk_auth.class);
		auth.setStatus(-100);
		bs.updateObj(auth);
		// bs.deleteObjById(kid, t_fk_auth.class);

		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/auth_unique")
	@ResponseBody
	public String auth_unique(String kid, String code) {
		t_fk_auth auth = null;

		if (ChkTools.isNull(kid)) {
			auth = authService.findAuthByCode(code);
		} else {
			auth = authService.findAuthByCode(kid, code);
		}

		if (auth == null) {
			result.put("success", "true");
		} else {
			result.put("fail", "true");
		}

		return JsonTools.toJson(result);
	}

}
