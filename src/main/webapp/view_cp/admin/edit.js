var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/admin/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [
			{
				fieldLabel : "姓名",
				name : "name",
				allowBlank : false
			},
			{
				fieldLabel : "用户名",
				name : "uname",
				id : 'uname',
				allowBlank : false,
				validator : function() {
					var error = true;
					Ext.Ajax.request({
								url : getServerHttp()+'/valid/check_unique_notme.htm',
								params : {
									table : 't_fk_admin',
									kid : edit_form_panel.getForm().findField('kid').getValue(),
									field : 'uname',
									value : this.value
								},
								scope : this,
								async : false,
								success : function(response) {
									var result = Ext.JSON
											.decode(response.responseText);
									if ("true" == result.fail) {
										error = "该用户名己经存在,请重新输入！";
									}
								}
							});
					return error;
				}
			},{
				id : "roleCombo",
				xtype : 'combo',
				editable:false,
				minChars : 1,
				fieldLabel : "权限组",
				name : "role_id",
				emptyText : '请选择权限组',
				store : comboxStore,
				displayField : 'name',
				valueField : 'kid',
				hiddenName : 'role_id',
				multiSelect:false,
				mode : 'remote',
				queryParam : 'srh_uname',
				triggerAction : 'all',
				selectOnFocus : true,
				forceSelection : true,
				allowBlank : false
			}
			, {
				xtype : "radiogroup",
				fieldLabel : "性别",
				value : 1,
				items : [ {
					boxLabel : "男",
					name : "sex",
					checked : true,
					inputValue : "男"
				}, {
					boxLabel : "女",
					name : "sex",
					inputValue : "女"
				} ]
			}, {
				fieldLabel : "id",
				name : "kid",
				hidden : true
			} ],
	buttons : [ {
		text : "保存",
		formBind : true, // only enabled once the form is valid
		disabled : true,
		handler : function() {
			var form = this.up("form").getForm();
			if (form.isValid()) {
				form.submit({
					waitMsg : "保存中...",
					success : function(form, action) {
						Ext.Msg.alert("提示", action.result.tip.msg);
						edit_form_panel_win.close();
						dataStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", action.result.tip.msg);
					}
				});
			}
		}
	} ]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "管理员编辑",
	closeAction : "hide",
	items : edit_form_panel
});

function myEdit(kid) {
	Ext.Ajax.request({
		url : getServerHttp()+"/cp/admin/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();

			edit_form_panel.getForm().setValues(json.admin);
			edit_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit
