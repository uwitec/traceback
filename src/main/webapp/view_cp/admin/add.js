Ext.apply(Ext.form.VTypes, {
	password : function(val, field) {
		if (field.initialPassField) {
			var pwd = Ext.getCmp(field.initialPassField);
			return (val == pwd.getValue());
		}
		return true;
	},
	passwordText : "两次输入的密码不一致!"
});

var add_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/admin/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	width : 400,
	items : [ {
		fieldLabel : "姓名",
		name : "name",
		allowBlank : false,
	}, {
		fieldLabel : "用户名",
		name : "uname",
		allowBlank : false,
		validator : function() {
			return uniqueValidator('t_fk_admin','uname',this.value)
		}
	},{
		xtype : 'combo',
		editable:false,
		multiSelect:true,
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
	},
	{
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
		xtype : "textfield",
		inputType : "password",
		fieldLabel : "密码",
		id : "pass1",
		name : "pwd",
		allowBlank : false
	}, {
		vtype : "password",
		inputType : "password",
		fieldLabel : "确认密码",
		name : "npwd",
		allowBlank : false,
		initialPassField : "pass1"
	}
	],
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
						Ext.Msg.alert("提示", action.result.tip.msg);
					}
				});
			}
		}
	} ]
});

var add_form_panel_win = Ext.create("Ext.Window", {
	title : "管理员添加",
	closeAction : "hide",
	items : add_form_panel
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}
