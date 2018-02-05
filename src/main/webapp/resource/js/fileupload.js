
var file_form_panel = Ext.create('Ext.form.Panel', {
		
		width : 400,
		bodyPadding : 10,
		frame : true,
		items : [ {
			xtype : 'filefield',
			name : 'file',
			fieldLabel : '文件:',
			labelWidth : 50,
			msgTarget : 'side',
			allowBlank : false,
			anchor : '100%',
			buttonText : '选择文件'
		} ],

		buttons : [ {
			text : '上传',
			handler : function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
					form.submit({
						url : '/upload',
						waitMsg : '处理中，请稍后...',
						success : function(fp, o) {
							var json = Ext.JSON.decode(o.response.responseText);
							saveExcelData2Db(json.file_id);
						}
					});
				}
			}
		} ]
	});

	var file_form_panel_win = Ext.create("Ext.Window", {
		title : "上传文件",
		closeAction : "hide",
		items : file_form_panel
	});

	function uploadfile() {
		file_form_panel.getForm().reset();
		file_form_panel_win.show();
	}
	
	
	