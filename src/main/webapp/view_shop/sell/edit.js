var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/sell/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		xtype : 'textfield',
		fieldLabel : "供货商",
		name : "supplier_name",
		allowBlank : false
	}, {
		fieldLabel : "总价",
		xtype : 'numberfield',
		name : 'total_price',
		value : '0',
		minValue : 0,
		allowDecimals : true, // 允许小数点
		allowNegative : false,
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
						edit_form_panel_win.close();
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
	title : "修改",
	closeAction : "hide",
	items : edit_form_panel,
	modal:true
});

function myEdit(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/sell/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json);
			edit_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit
