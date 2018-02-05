var add_form_panel = Ext.create("Ext.form.Panel", {
		url : getServerHttp() + "/cp/auth/add.htm",
		buttonAlign : "center",
		bodyStyle : "padding: 10px;",
		width : 500,
		defaultType : "textfield",
		items : [ {
			name : "kid",
			hidden : true
		}, {
			fieldLabel : "父节点",
			name : "up_node",
			readOnly : true
		}, {
			xtype : "fieldcontainer",
			fieldLabel : "编号",
			layout : "hbox",
			items : [ {
				width : 100,
				xtype : "textfield",
				id : "up_code",
				name : "up_code",
				readOnly : true
			}, {
				width : 80,
				xtype : "textfield",
				name : "code",
				allowBlank : false,
				minLength : 3,
				maxLength : 3,
				validator : function() {
					var error = true;
					Ext.Ajax.request({
						url : getServerHttp() + '/valid/check_unique.htm',
						params : {
							table:'t_fk_auth',
							field:'code',
							value : Ext.getCmp("up_code").getValue() + this.value
						},
						scope : true,
						async : false,
						method : 'POST',
						success : function(response) {
							var result = Ext.JSON.decode(response.responseText);
							if ("true" == result.fail) {
								error = "该编号己经存在,请重新输入！";
							}
						}
					});
					return error;
				}//end_validator  
			} ]
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
				inputValue : "菜单",
				checked : true
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
				boxLabel : "cp系统",
				inputValue : "cp系统",
				checked : true
			}]
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
		title : "权限添加",
		closeAction : "hide",
		items : add_form_panel
	});

	function myAdd(up_code, up_name) {
		add_form_panel.getForm().reset();
		add_form_panel.getForm().setValues({
			"up_node" : up_name + "[" + up_code + "]",
			"up_code" : up_code
		});

		add_form_panel_win.show();
	}
	
	