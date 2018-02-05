function send() {

	var domainName = add_panel.form.findField('domainName').getValue();
	var templatecode = add_panel.form.findField('templateCode').getValue();

	Ext.Ajax.request({
		url :getServerHttp()+'/cp/email/send_email.htm',
		params : {
			'domainName' : domainName,
			'templateCode' : templateCode
		},
		success : function(response) {

			var json = Ext.decode(response.responseText);
			Ext.Msg.alert("提示", json.tip.msg);
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}