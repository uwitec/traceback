function send() {

	var templatecode = test_panel.form.findField('templatecode').getValue();
	var send_test_sms = test_panel.form.findField('send_test_sms').getValue();

	Ext.Ajax.request({
		url : getServerHttp() + '/cp/sms/sms_test.htm',
		params : {
			'templatecode' : templatecode,
			'send_test_sms' : send_test_sms
		},
		success : function(response) {
			var json = Ext.decode(response.responseText);
			Ext.Msg.alert("提示", json.tip.msg);
		},
		failure : function(response) {
			Ext.Msg.alert("提示", response.tip.msg);
		}
	});
}