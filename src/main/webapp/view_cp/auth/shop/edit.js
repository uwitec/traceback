
	//校验 code 长度
	var codeLength = 0;
	
	var edit_form_panel = Ext.create("Ext.form.Panel", {
		url : getServerHttp() + "/cp/auth/edit.htm",
		buttonAlign : "center",
		bodyStyle : "padding: 10px;",
		width : 500,
		defaultType : "textfield",
		items : [ {
			id : "kid",
			name : "kid",
			hidden : true
		}, {
			id : "code",
			fieldLabel : "编号",
			name : "code",
			validator : function() {
				var error = true;
				var kid = Ext.getCmp("kid").getValue();
				if (kid == null || kid == "") {
					return true;
				}
				
				if (codeLength != this.value.length) {
					error = "长度必须为:" + codeLength;
					return error;
				}

				Ext.Ajax.request({
					url : getServerHttp() + '/cp/auth/auth_unique.htm',
					params : {
						kid : kid,
						code : this.value
					},
					scope : true,
					async : false,
					method : 'POST',
					success : function(response) {
						var result = Ext.JSON.decode(response.responseText);
						if ("true" == result.fail) {
							error = "该名称己经存在,请重新输入！";
						}
					}
				});
				return error;
			}//end_validator
		}, {
			fieldLabel : "名称",
			name : "name",
			allowBlank : false
		}, {
			fieldLabel : "图标",
			name : "icon"
		}, {
			fieldLabel : "URL",
			name : "url"
		}, {
			xtype : "radiogroup",
			fieldLabel : "类型",
			width : 300,
			items : [ {
				name : "type",
				boxLabel : "菜单",
				inputValue : "菜单"
			}, {
				name : "type",
				boxLabel : "按钮",
				inputValue : "按钮"
			} ]
		}, {
			xtype : "radiogroup",
			fieldLabel : "所属系统",
			width : 300,
			items : [ {
				name : "system",
				boxLabel : "商户",
				inputValue : "商户",
				checked : true
			} ]
		} ],
		buttons : [ {
			text : "保存",
			formBind : true, //only enabled once the form is valid
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
		title : "商户编辑",
		closeAction : "hide",
		items : edit_form_panel
	});

	function myEdit(kid) {
		Ext.Ajax.request({
			url : getServerHttp() + "/cp/auth/edit_form.htm?kid=" + kid,
			success : function(response) {
				console.log(response.responseText);
				var json = Ext.JSON.decode(response.responseText);
				edit_form_panel.getForm().reset();
				edit_form_panel.getForm().setValues(json);
				edit_form_panel_win.show();
				
				//alert(edit_form_panel.getForm().findField("kid").getValue());
				codeLength = Ext.getCmp("code").getValue().length;
			},
			failure : function(response) {
				Ext.Msg.alert("提示", "操作失败!");
			}
		});
	}//#myEdit
