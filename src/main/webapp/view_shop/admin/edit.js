var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/admin/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [ {
		fieldLabel : "手机号(账号)",
		name : "mobile",
		editable : false
	}, {
		fieldLabel : "密码",
		name : "pwd",
		inputType: 'password',
		allowBlank : false
	},{
		xtype : 'combo',
		editable:false,
		multiSelect:false,
		minChars : 1,
		fieldLabel : "权限组",
		name : "role_id",
		emptyText : '请选择权限组',
		store : comboxStore,
		displayField : 'name',
		valueField : 'kid',
		hiddenName : 'role_id',
		triggerAction : 'all',
		selectOnFocus : true,
		forceSelection : true,
		allowBlank : false
	},{
		fieldLabel : "姓名",
		name : "name",
		allowBlank : false
	}, {
		fieldLabel : "id",
		name : "kid",
		hidden : true
	}],
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
						add_form_panel_win.close();
						dataStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存失败");
					}
				});
			}
		}
	} ]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "管理员编辑",
	closeAction : "hide",
	items : edit_form_panel,
	modal:true
});

function myEdit(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/admin/edit_form.htm?kid=" + kid,
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