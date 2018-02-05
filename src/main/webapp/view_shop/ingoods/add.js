var add_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/ingoods/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [ {
		xtype : 'textfield',
		fieldLabel : "条码",
		name : "barcode",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "原料名称",
		name : "name",
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "进货日期",
		name : "dingoods_time",
		format: 'Y-m-d H:i:s'
	}, {
		xtype : 'textfield',
		fieldLabel : "批次",
		name : "batch",
		allowBlank : false
	}, {
		xtype : 'numberfield',
		fieldLabel : "进货数量",
		name : "number",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "单位",
		name : "unit",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "供货商",
		name : "supplier_name",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "合格证名称",
		name : "certificate_name",
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "签发日期",
		name : "dcertificate_start_time",
		format: 'Y-m-d H:i:s', 
		allowBlank : false
	}, {
		xtype : "radiogroup",
		fieldLabel : "合格证状态",
		allowBlank : false,
		style : 'margin-left:5px',
		labelWidth : 130,
		width : 300,
		items : [ {
			name : "certificate_status",
			boxLabel : "有效",
			inputValue : "有效",
			checked : true
		}, {
			name : "certificate_status",
			boxLabel : "无效",
			inputValue : "无效"
		} ]
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
	title : "进货",
	closeAction : "hide",
	items : add_form_panel,
	modal:true
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}