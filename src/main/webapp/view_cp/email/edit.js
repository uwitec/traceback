var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/email/template/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [
			 {
			text : '模板编号',
			name : 'templateId',
			flex : 1
		}, {
			text : '模板名称',
			name : 'templateName',
			flex : 1
		},{
				fieldLabel : "id",
				name : "kid",
				hidden : true
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
						edit_form_panel_win.close();
						dataStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "失败");
					}
				});
			}
		}
	} ]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "管理员编辑",
	closeAction : "hide",
	items : edit_form_panel
});

function myEdit(kid) {

	Ext.Ajax.request({
		url : getServerHttp()+"/cp/email/template/edit_form_data.htm?kid="+kid ,
		success : function(response) {	
			var json = Ext.decode(response.responseText);
			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json.template);
			edit_form_panel_win.show();
		
			
			
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit
