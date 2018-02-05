package com.tmsps.traceback.action_cp.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmsps.ne4spring.utils.MD5Util;
import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.base.tip.Tip;
import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.service.AdminService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

/**
 * 管理员管理
 * 
 * @author 张志亮
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/cp/admin")
public class AdminController extends ProjBaseAction {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/list_data")
	@ResponseBody
	public void list_data() {

		List<Map<String, Object>> list = adminService.selectAdminList(srh, sort_params, page);

		result.put("list", list);
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(t_fk_admin admin) {
		admin.setType("管理员");
		admin.setPwd(MD5Util.MD5(admin.getPwd()));
		bs.saveObj(admin);
		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/edit_form")
	@ResponseBody
	public String edit_form(String kid) {
		t_fk_admin admin = bs.findById(kid, t_fk_admin.class);

		result.put("admin", admin);
		return JsonTools.toJson(result);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(t_fk_admin admin) {
		t_fk_admin adminDb = bs.findById(admin.getKid(), t_fk_admin.class);
		adminDb.setUname(admin.getUname());
		adminDb.setName(admin.getName());
		adminDb.setSex(admin.getSex());
		adminDb.setRole_id(admin.getRole_id());
		bs.updateObj(adminDb);

		this.setTipMsg(true, "保存成功!", Tip.Type.success);
	}

	@RequestMapping("/del")
	@ResponseBody
	public void del(String kid) {
		t_fk_admin admin = bs.findById(kid, t_fk_admin.class);
		admin.setStatus(-100);
		bs.updateObj(admin);

		this.setTipMsg(true, "删除成功!", Tip.Type.success);
	}

	// 获取当前用户
	@RequestMapping("/get_current_user")
	@ResponseBody
	public String get_current_user() {
		if (ChkTools.isNotNull(SessionTools.getCurrentLoginCpAdmin())) {
			result.put("username", SessionTools.getCurrentLoginCpAdmin().getName());
			return JsonTools.toJson(result);
		}
		return "";
	}
	
	/**
	 * 修改密码
	 * @param currentpwd 当前密码
	 * @param newpwdone  修改密码
	 */
	@RequestMapping("/pwd_form")
	@ResponseBody
	public void pwd(String currentpwd, String newpwdone) {
		t_fk_admin currentUser = SessionTools.getCurrentLoginCpAdmin();
		if (MD5Util.MD5(currentpwd).equals(currentUser.getPwd())) {
			t_fk_admin userDb = (t_fk_admin) bs.findById(currentUser.getKid(), t_fk_admin.class);
			userDb.setPwd(MD5Util.MD5(newpwdone));
			bs.updateObj(userDb);
			super.setTipMsg(true, "修改密码成功", Tip.Type.success);
		} else {
			super.setTipMsg(true, "当前密码错误", Tip.Type.error);
		}

	}

	/**
	 * 张志亮2016.7.11
	 * 
	 * 限制登陆
	 * 
	 * @param admin
	 */
	@RequestMapping("/limit")
	@ResponseBody
	public void limit(String kid) {

		t_fk_admin adminDb = bs.findById(kid, t_fk_admin.class);

		if ("限制登陆".equals(adminDb.getStatus_sys())) {
			adminDb.setStatus_sys("正常状态");
			bs.updateObj(adminDb);
			this.setTipMsg(true, "解除登陆限制成功!", Tip.Type.success);
		} else {
			adminDb.setStatus_sys("限制登陆");
			bs.updateObj(adminDb);
			this.setTipMsg(true, "限制登陆成功!", Tip.Type.success);
		}
	}
}
