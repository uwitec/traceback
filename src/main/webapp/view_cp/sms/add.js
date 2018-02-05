

var add_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp() + "/cp/sms/template/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	width : 400,
	items : [ {
		fieldLabel : "模板编号",
		name : "smsTempCode",
		allowBlank : false
	},{
		fieldLabel : "模板Id",
		name : "smsTempId",
		allowBlank : false
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
	title : "短信模板添加",
	closeAction : "hide",
	items : add_form_panel
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}
