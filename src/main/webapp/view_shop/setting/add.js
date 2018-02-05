// 曹家榜添加 系统参数配置功能 2016年7 月9号 
var add_form_panel = Ext.create("Ext.form.Panel", {
	url : "/shop/setting/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [ {
		fieldLabel : "属性",
		name : "field",
		allowBlank : false
	}, {
		xtype : 'textareafield',
		fieldLabel : "值",
		name : "val"
	}, {
		xtype : 'textareafield',
		fieldLabel : "备注",
		name : "note"
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
	title : "配置添加",
	closeAction : "hide",
	items : add_form_panel
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}