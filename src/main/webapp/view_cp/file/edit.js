var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/shop/information/edit.htm",
	buttonAlign : "cnter",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	width: 500,
	items : [ {
		fieldLabel : "链接",
		name : "url",
		anchor : '100%',
		readOnly : true
	}]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "文件地址",
	closeAction : "hide",
	items : edit_form_panel
});

function myEdit(kid) {
	// alert(kid);
	Ext.Ajax.request({
		url : getServerHttp()+"/cp/file/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();
			console.log(json);
			edit_form_panel.getForm().findField('url').setValue(window.location.host+'/video/'+json.folder_url+'/'+json.new_file_name);
			edit_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit
