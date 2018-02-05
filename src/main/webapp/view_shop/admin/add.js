var add_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/admin/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [ {
		fieldLabel : "手机号(账号)",
		name : "mobile",
		allowBlank : false,
		validator : function() {
			return uniqueValidator('t_shop_admin','mobile',this.value)
		}
	}, {
		fieldLabel : "密码",
		name : "pwd",
		allowBlank : false,
		inputType: 'password',
	}, {
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
						Ext.Msg.alert("提示", "添加失败");
					}
				});
			}
		}
	} ]
});

var add_form_panel_win = Ext.create("Ext.Window", {
	title : "管理员添加",
	closeAction : "hide",
	items : add_form_panel,
	modal:true
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}