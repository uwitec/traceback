var add_form_panel = Ext.create("Ext.form.Panel", {
	url :  getServerHttp()+"/cp/shop/add.htm",
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
			xtype : 'textfield',
			fieldLabel : "企业类型",
			name : "type",
			allowBlank : false
		}, {
			xtype : 'textfield',
			fieldLabel : "企业名称",
			name : "name",
			allowBlank : false
		}, {
			xtype : 'textfield',
			fieldLabel : "负责人",
			name : "link_man",
			allowBlank : false
		}, {
			xtype : 'textfield',
			fieldLabel : "手机号码",
			name : "link_mobile",
			allowBlank : false
		}, {
			xtype : 'textfield',
			fieldLabel : '所属的地方管理局',
			name : 'address',
		}, {
			xtype : 'combo',
			editable:false,
			minChars : 1,
			fieldLabel : "菜单模版",
			name : "shop_menu_id",
			emptyText : '请选择菜单模版',
			store : comboxStore,
			displayField : 'name',
			valueField : 'kid',
			hiddenName : 'shop_menu_id',
			multiSelect:false,
			mode : 'remote',
			triggerAction : 'all',
			selectOnFocus : true,
			forceSelection : true,
		}]
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
								xtype : 'textfield',
								fieldLabel : "注册号",
								name : "ycode"
							}, {
								xtype : 'datefield',
								fieldLabel : "发证日期",
								format: 'Y-m-d H:i:s', 
								name : "ystart_time"
							}, {
								xtype : 'datefield',
								fieldLabel : "有效期至",
								format: 'Y-m-d H:i:s',
								name : "yend_time"
							},{
								xtype : "panel",
								html : '<div><img id="logo_img0" src="../../resource/img/product.png" width="120" height="120"></div>'
										+ '<div id="uploader" class="wu-example">'
										+ '<div id="thelist" class="uploader-list">'
										+ '</div>'
										+ '<div class="btns">'
										+ '<div id="picker0">'
										+ '上传营业执照图片'
										+ '</div>'
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
							xtype : 'textfield',
							fieldLabel : "注册号",
							name : "scode"
						}, {
							xtype : 'datefield',
							fieldLabel : "发证日期",
							format: 'Y-m-d H:i:s', 
							name : "sstart_time"
						}, {
							xtype : 'datefield',
							fieldLabel : "有效期至",
							format: 'Y-m-d H:i:s',
							name : "send_time"
						},{
							xtype : "panel",
							html : '<div><img id="logo_img1" src="../../resource/img/product.png" width="120" height="120"></div>'
									+ '<div id="uploader" class="wu-example">'
									+ '<div id="thelist" class="uploader-list">'
									+ '</div>'
									+ '<div class="btns">'
									+ '<div id="picker1">'
									+ '上传生产许可证图片'
									+ '</div>'
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
						Ext.Msg.alert("提示", action.result.tip.msg);
					}
				});
			}
		}
	} ]
});

var add_form_panel_win = Ext.create("Ext.Window", {	buttonAlign : "center",
	title : "企业信息添加",
	maximizable : true, // 设置是否可以最大化
	closeAction : "hide",
	layout : "fit", // 窗口布局类型
	width : 800, // 宽度
	items : add_form_panel,
	modal:true
});

var isInit = false;
function myAdd() {
	add_form_panel.getForm().reset();
	add_form_panel_win.show();
	if(isInit==false){
		initUploader(0);
		initUploader(1);
		isInit = true;
	}
}