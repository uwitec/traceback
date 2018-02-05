var add_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/shop/menu/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [ {
		fieldLabel : "编号",
		name : "code",
		allowBlank : false
	}, {
		fieldLabel : "名称",
		name : "name"
	}/*, {
		xtype : "fieldcontainer",
		fieldLabel : "菜单树",
		layout : "hbox",
		items : [ treePanel ]
	}*/],
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
	title : "菜单模版添加",
	closeAction : "hide",
	items : add_form_panel,
	modal:true
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}