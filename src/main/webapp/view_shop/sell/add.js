var add_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/sell/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		xtype : 'textfield',
		fieldLabel : "供货商",
		name : "supplier_name",
		allowBlank : false
	},{
		xtype : 'numberfield',
		fieldLabel : "总价",
		name : 'total_price',
		value : '0',
		minValue : 0,
		allowDecimals : true, // 允许小数点
		allowNegative : false,
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
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var add_form_panel_win = Ext.create("Ext.Window", {
	title : "销售",
	closeAction : "hide",
	items : add_form_panel,
	modal:true
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}