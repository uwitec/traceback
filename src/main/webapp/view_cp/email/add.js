var add_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/email/template/add_template.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	width : 400,
	items : [ {
		fieldLabel : "模板编号",
		name : "templateId",
		allowBlank : false
	},{
		fieldLabel : "模板名称",
		name : "templateName",
		allowBlank : false
	},{
		fieldLabel : "邮件标题",
		name : "subject",
		allowBlank : false
	},
	{
        xtype : 'htmleditor',
       // id : 'bio',
        height : 380,
        anchor : '100%',
        enableSourceEdit:false,
        enableLinks:false,
        fieldLabel : '邮件内容',
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
	title : "添加邮件模板",
	closeAction : "hide",
	items : add_form_panel
});

function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
}
