var edit_form_panel;
var edit_form_panel_win;

Ext.onReady(function() {
	edit_form_panel = Ext.create("Ext.form.Panel", {
		url : "/traceback/shop/role/edit.htm",
		buttonAlign : "center",
		bodyStyle : "padding: 10px;",
		defaultType : "textfield",
		items : [ {
			fieldLabel : "权限组",
			name : "name",
			allowBlank : false
		}, {
			fieldLabel : "权限",
			name : "codes",
			hidden : true
		}, {
			xtype : "fieldcontainer",
			fieldLabel : "权限选择",
			layout : "hbox",
			items : [ editTreePanel ]
		}, {
			xtype : 'textareafield',
			fieldLabel : "备注",
			name : "note"
		}, {
			fieldLabel : "kid",
			name : "kid",
			hidden : true
		}],
		buttons : [ {
			text : "保存",
			formBind : true, // only enabled once the form is valid
			disabled : true,
			handler : function() {
				var form = this.up("form").getForm();

				// 设置选择框
				var codes = treePanel.getLeafIdSelections();
				form.setValues({
					"codes" : codes
				});

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

	edit_form_panel_win = Ext.create("Ext.Window", {
		title : "权限组编辑 ",
		closeAction : "hide",
		items : edit_form_panel,
		modal:true
	});
});

function myEdit(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/role/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json);
			// 设置树的值
			editTreePanel.setSelections(json["codes"]);

			edit_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit

