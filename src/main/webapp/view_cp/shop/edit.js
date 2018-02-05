var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : getServerHttp()+"/cp/shop/edit.htm",
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
		}, {
			xtype : 'textfield',
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
								html : '<div><img id="logo_img3" src="../../resource/img/product.png" width="120" height="120"></div>'
										+ '<div id="uploader" class="wu-example">'
										+ '<div id="thelist" class="uploader-list">'
										+ '</div>'
										+ '<div class="btns">'
										+ '<div id="picker3">'
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
							html : '<div><img id="logo_img4" src="../../resource/img/product.png" width="120" height="120"></div>'
									+ '<div id="uploader" class="wu-example">'
									+ '<div id="thelist" class="uploader-list">'
									+ '</div>'
									+ '<div class="btns">'
									+ '<div id="picker4">'
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

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "企业信息编辑",
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
		url : getServerHttp()+"/cp/shop/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);

			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json.shop);
			
			if(json.shop_yingyelicense != null){
				edit_form_panel.getForm().findField('ycode').setValue(json.shop_yingyelicense.code);
				edit_form_panel.getForm().findField('ystart_time').setValue(dateFormat_2(json.shop_yingyelicense.start_time));
				edit_form_panel.getForm().findField('yend_time').setValue(dateFormat_2(json.shop_yingyelicense.end_time));
				edit_form_panel.getForm().findField('yimg_url').setValue(json.shop_yingyelicense.img_url);
			}

			if(json.shop_shengcanlicense != null){
				edit_form_panel.getForm().findField('scode').setValue(json.shop_shengcanlicense.code);
				edit_form_panel.getForm().findField('sstart_time').setValue(dateFormat_2(json.shop_shengcanlicense.start_time));
				edit_form_panel.getForm().findField('send_time').setValue(dateFormat_2(json.shop_shengcanlicense.end_time));
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


//返回 01-12 的月份值  
function getMonth(date){ 
  var month = ""; 
  month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
  if(month<10){ 
    month = "0" + month; 
  } 
  return month; 
} 
//返回01-30的日期 
function getDay(date){ 
  var day = ""; 
  day = date.getDate(); 
  if(day<10){ 
    day = "0" + day; 
  } 
  return day; 
}

function dateFormat_2(longTypeDate){ 
  var dateType = ""; 
  var date = new Date(); 
  date.setTime(longTypeDate); 
  dateType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);//yyyy-MM-dd格式日期
  return dateType;
}