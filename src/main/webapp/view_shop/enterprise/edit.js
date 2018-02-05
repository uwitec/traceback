var edit_form_panel = Ext.create("Ext.form.Panel", {
	width : 800,
	frame : true,
	buttonAlign : "center",
	bodyStyle:'padding:5px 10px 5px 10px;',  
	defaultType : "textfield",
	items : [{
		xtype : 'fieldset',
		checkbosToggle : true,
		title : '基础信息',
		autoHeight : true,
		defaultsType : 'textfield',
		defaults : {
			width : 360
		},
		items : [{
			xtype : 'displayfield',
			fieldLabel : "企业类型",
			name : "type",
			allowBlank : false
		}, {
			xtype : 'displayfield',
			fieldLabel : "企业名称",
			name : "name",
			allowBlank : false
		}, {
			xtype : 'displayfield',
			fieldLabel : "负责人",
			name : "link_man",
			allowBlank : false
		}, {
			xtype : 'displayfield',
			fieldLabel : "手机号码",
			name : "link_mobile",
			allowBlank : false
		}, {
			xtype : 'displayfield',
			fieldLabel : '监管单位',
			name : 'address',
		}, {
			xtype : 'displayfield',
			fieldLabel : "id",
			name : "kid",
			hidden : true
		} ]
	}, {
		xtype : 'container',
		layout : 'column',
		items : [
				{
					height : 280,
					columnWidth : .5,
					xtype : 'fieldset',
					checkbosToggle : true,
					title : '营业执照',
					autoHeight : true,
					defaults : { 
						width : 360
					},
					defaultsType : 'textfield',
					items : [{
								xtype : 'displayfield',
								fieldLabel : "注册号",
								name : "ycode"
							}, {
								xtype : 'displayfield',
								fieldLabel : "发证日期",
								name : "ystart_time",
								renderer : function(val) {
									if (val != '') {
										return Ext.Date.format(
												new Date(val),
												"Y-m-d H:i:s");
									}
								}
							}, {
								xtype : 'displayfield',
								fieldLabel : "有效期至",
								name : "yend_time",
								renderer : function(val) {
									if (val != '') {
										return Ext.Date.format(
												new Date(val),
												"Y-m-d H:i:s");
									}
								}
							},{
								xtype : "panel",
								html : '<div><img id="logo_img3" src="../../resource/img/product.png" width="120" height="120"></div>'
										+ '<div id="uploader" class="wu-example">'
										+ '<div id="thelist" class="uploader-list">'
										+ '</div>'
										+ '<div class="btns">'
										+ '</div>' + '</div>',
								width : '100%',
								height : 170,
								border : false,
								style : {
									marginBottom : '10px',
									paddingLeft : '130px',
								}
							},{
								xtype : 'textfield',
								fieldLabel : "图片链接",
								name :"yimg_url",
								hidden :true
							} ]
				}, {
					xtype : 'container',
					columnWidth : .5,
					layout : 'absolute',
					items : [ {
						height : 280,
						xtype : 'fieldset',
						checkbosToggle : true,
						title : '生产许可证',
						autoHeight : true,
						defaults : {	
							width : 360
						},
						defaultsType : 'textfield',
						items : [{
							xtype : 'displayfield',
							fieldLabel : "注册号",
							name : "scode"
						}, {
							xtype : 'displayfield',
							fieldLabel : "发证日期",
							name : "sstart_time",
							renderer : function(val) {
								if (val != '') {
									return Ext.Date.format(
											new Date(val),
											"Y-m-d H:i:s");
								}
							}
						}, {
							xtype : 'displayfield',
							fieldLabel : "发证日期",
							name : "send_time",
							renderer : function(val) {
								if (val != '') {
									return Ext.Date.format(
											new Date(val),
											"Y-m-d H:i:s");
								}
							}
						},{
							xtype : "panel",
							html : '<div><img id="logo_img4" src="../../resource/img/product.png" width="120" height="120"></div>'
									+ '<div id="uploader" class="wu-example">'
									+ '<div id="thelist" class="uploader-list">'
									+ '</div>'
									+ '<div class="btns">'
									+ '</div>' + '</div>',
							width : '100%',
							height : 170,
							border : false,
							style : {
								marginBottom : '10px',
								paddingLeft : '130px',
							}
						},{
							xtype : 'textfield',
							fieldLabel : "图片链接",
							name :"simg_url",
							hidden :true
						} ]
					} ]
				} ]
	}]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "企业详情",
	maximizable : true, // 设置是否可以最大化
	closeAction : "hide",
	layout : "fit", // 窗口布局类型
	width : 800, // 宽度
	items : edit_form_panel,
	modal:true
});

var isInit = false;
function myEdit(kid) {
	// alert(kid);
	Ext.Ajax.request({
		url : "/traceback/shop/enterprise/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);

			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json.shop);
			
			if(json.shop_yingyelicense != null){
				edit_form_panel.getForm().findField('ycode').setValue(json.shop_yingyelicense.code);
				edit_form_panel.getForm().findField('ystart_time').setValue(json.shop_yingyelicense.start_time);
				edit_form_panel.getForm().findField('yend_time').setValue(json.shop_yingyelicense.end_time);
				edit_form_panel.getForm().findField('yimg_url').setValue(json.shop_yingyelicense.img_url);
			}

			if(json.shop_shengcanlicense != null){
				edit_form_panel.getForm().findField('scode').setValue(json.shop_shengcanlicense.code);
				edit_form_panel.getForm().findField('sstart_time').setValue(json.shop_shengcanlicense.start_time);
				edit_form_panel.getForm().findField('send_time').setValue(json.shop_shengcanlicense.end_time);
				edit_form_panel.getForm().findField('simg_url').setValue(json.shop_shengcanlicense.img_url);
			}
			
			edit_form_panel_win.show();
			if(isInit==false){
				initUploader(3);
				initUploader(4);
				isInit = true;
			}
			$("#logo_img3").attr("src",json.shop_yingyelicense.img_url);
			$("#logo_img4").attr("src",json.shop_shengcanlicense.img_url);
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit