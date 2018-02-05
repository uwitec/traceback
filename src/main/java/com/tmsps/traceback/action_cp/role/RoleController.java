package com.tmsps.traceback.action_cp.role;

import java.util.ArrayList;
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
import com.tmsps.traceback.model.t_fk_role;
import com.tmsps.traceback.service.AuthService;
import com.tmsps.traceback.service.RoleService;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.tree.TreeTools;

/**
 * 曹家榜 2016 7.19 //
 */

@Controller
@Scope("prototype")
@RequestMapping("/cp/role")
public class RoleController extends ProjBaseAction {

	@Autowired
	private RoleService rService;
	@Autowired
	private AuthService aService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {

		List<Map<String, Object>> list = rService.selectRoleList(srh);

		result.put("list", list);
	}

	@RequestMapping("/list_data_combox")
	@ResponseBody
	public void list_data_combox() {
		List<Map<String, Object>> list = rService.selectRoleList();
		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_role role) {
		String role_ids = role.getKid();
		if (role_ids == null || role_ids.equals("")) {
			bs.saveObj(role);
		} else {
			String[] kids = role_ids.split(",");
			for (int i = 0; i < kids.length; i++) {
				boolean flag = true;
				t_fk_role roleDb = (t_fk_role) bs.findById(kids[i], t_fk_role.class);
				String auth_codesDb = roleDb.getCodes();
				String[] auth_codesDbs = auth_codesDb.split(",");
				String[] codes = role.getCodes().split(",");
				for (int j = 0; j < codes.length; j++) {
					String auth_code = codes[j];
					for (int k = 0; k < auth_codesDbs.length; k++) {
						if (auth_code.equals(auth_codesDbs[k])) {
							flag = false;
							break;
						} else {
							flag = true;
						}
					}
					if (flag) {
						auth_codesDb += "," + auth_code;
					}
				}
				roleDb.setCodes(auth_codesDb);
				bs.updateObj(roleDb);
			}
		}

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_fk_role role = (t_fk_role) bs.findById(kid, t_fk_role.class);

		return JsonTools.toJson(role);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_role role) {
		t_fk_role roleDb = (t_fk_role) bs.findById(role.getKid(), t_fk_role.class);
		// DbLogTools.log(roleDb);
		roleDb.setName(role.getName());
		roleDb.setCodes(role.getCodes());
		bs.updateObj(roleDb);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/dels_form")
	@ResponseBody
	public String delAuths_form(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] kids = id.split(",");
		for (int i = 0; i < kids.length; i++) {
			// System.err.println("kid"+i+":"+kids[i]);
			t_fk_role roleDb = (t_fk_role) bs.findById(kids[i], t_fk_role.class);
			String[] codes = roleDb.getCodes().split(",");
			for (int j = 0; j < codes.length; j++) {
				// System.err.println("auth"+j+":"+auth_codes[j]);
				t_fk_auth auth = aService.findAuthByCode(codes[j]);
				if (auth != null) {
					Map<String, Object> map = JsonTools.objToMap(auth);
					list.add(map);
				}
			}
		}
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if (!tempList.contains(map)) {
				tempList.add(map);
			}
		}
		// System.err.println(TreeTools.turnListToTree(list, true));
		return TreeTools.turnListToTree(tempList, true);
	}

	@RequestMapping("/list_auths")
	@ResponseBody
	public void list_auths(String kid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		t_fk_role roleDb = (t_fk_role) bs.findById(kid, t_fk_role.class);
		String[] codes = roleDb.getCodes().split(",");
		for (int i = 0; i < codes.length; i++) {
			t_fk_auth auth = aService.findAuthByCode(codes[i]);
			if (auth != null && auth.getUrl() != null && !("".equals(auth.getUrl().trim()))) {
				Map<String, Object> map = JsonTools.objToMap(auth);
				list.add(map);
			}
		}
		result.put("list", list);
	}

	@RequestMapping("/dels")
	@ResponseBody
	public void delAuths(t_fk_role role) {
		String[] kids = role.getKid().split(",");
		for (int i = 0; i < kids.length; i++) {
			t_fk_role roleDb = (t_fk_role) bs.findById(kids[i], t_fk_role.class);
			String auth_codesDb = roleDb.getCodes();
			String[] auth_codesDbs = auth_codesDb.split(",");
			String[] codes = role.getCodes().split(",");
			for (int j = 0; j < codes.length; j++) {
				String auth_code = codes[j];
				for (int k = 0; k < auth_codesDbs.length; k++) {
					if (auth_code.equals(auth_codesDbs[k])) {
						t_fk_auth auth = aService.findAuthByCode(auth_code);
						if (auth != null) {
							if (auth.getUrl() != null && !("".equals((String) auth.getUrl().trim()))) {
								auth_codesDb = auth_codesDb.replaceAll("," + auth_code, "");
							}
						}
					}
				}
			}
			String[] newDbs = auth_codesDb.split(",");
			for (int n = 0; n < newDbs.length; n++) {
				t_fk_auth auth = aService.findAuthByCode(newDbs[n]);
				if (auth != null) {
					boolean flag = false;
					if (auth.getUrl() == null || "".equals((String) auth.getUrl().trim())) {
						for (int m = 0; m < newDbs.length; m++) {
							if (newDbs[n].length() == 6) {
								if ((newDbs[m].startsWith(newDbs[n]) && newDbs[m].length() == 9)) {
									flag = false;
									break;
								} else {
									flag = true;
								}
							}

						}
						if (flag) {
							auth_codesDb = auth_codesDb.replaceAll("," + newDbs[n], "");
						}

					}
				}
			}

			newDbs = auth_codesDb.split(",");
			for (int n = 0; n < newDbs.length; n++) {
				t_fk_auth auth = aService.findAuthByCode(newDbs[n]);
				if (auth != null) {
					boolean flag = false;
					if (auth.getUrl() == null || "".equals((String) auth.getUrl().trim())) {
						for (int m = 0; m < newDbs.length; m++) {
							if (newDbs[n].length() == 3) {
								if ((newDbs[m].startsWith(newDbs[n]) && newDbs[m].length() == 6)) {
									flag = false;
									break;
								} else {
									flag = true;
								}
							}

						}
						if (flag) {
							auth_codesDb = auth_codesDb.replaceAll("," + newDbs[n], "");
						}

					}
				}
			}

			roleDb.setCodes(auth_codesDb);

			bs.updateObj(roleDb);
		}

		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_fk_role role = bs.findById(kid, t_fk_role.class);
		role.setStatus(-100);
		// bs.delObjById(kid, t_fk_role.class);
		bs.updateObj(role);
		this.setTipMsg(true, "删除成功!", Tip.Type.success);

	}

}
