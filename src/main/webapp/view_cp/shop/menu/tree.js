/**
 * ———————————————————— 菜单权限开始  ——————————————————————————————————————————————————————————————————————————
 */
	var mymenu_id = ""; //全局 菜单模版ID
	var mymenu_auth_id =""; //全局 菜单ID
	
	var treeStore = Ext.create('Ext.data.TreeStore', {
			proxy : {
				type : 'ajax',
				url : getServerHttp() + "/cp/shop/menu/auth/auth_data_shop.htm",
				reader : {
					type : 'json',
				}
			},
			listeners : {
				'beforeload' : function(store, op, options) {
					var params = {
						menu_id : mymenu_id
					};
					Ext.apply(treeStore.proxy.extraParams, params);
				}
			}
	});
	
	var treePanel = Ext.create('Ext.tree.Panel', {
			store : treeStore,
			border : true,
			tbar:[{
                text:'新增菜单',  
                handler:function(){
                	myMenuAdd("root","一级");
                }  
            }],
			rootVisible : false,
			animCollapse : true,
			rootVisible : false,
			autoScroll : true,
			animate : true,
			width : 400,
			height : 300,
			listeners : {
				itemcontextmenu : function(view, rec, node, index, e) {
					e.stopEvent();
					e.preventDefault();
					// 添加一个节点（叶子）  
					var chlidNodeClickMenu = Ext.create('Ext.menu.Menu', {
						items : [ {
							text : '新增子菜单',
							icon : jcapp.getIcon("add.png"),
							handler : function() {
								var up_code = rec.get("id");
								var up_name = rec.get("text");
								myMenuAdd(up_code, up_name);
							}
						}, {
							text : '新增同级菜单',
							icon : jcapp.getIcon("vcard_add.png"),
							handler : function() {
								var up_code = rec.parentNode.get("id");
								var up_name = rec.parentNode.get("text");

								if (up_code == "root") {
									up_code = "";
								}
								myMenuAdd(up_code, up_name);
							}
						}, {
							text : '修改菜单',
							icon : jcapp.getIcon("vcard_edit.png"),
							handler : function() {
								//console.log(rec.raw.kid);
								myMenuEdit(rec.raw.kid);
							}
						}, {
							text : '删除菜单',
							icon : jcapp.getIcon("vcard_delete.png"),
							handler : function() {
								//console.log(rec);
								myMenuDel(rec.raw.kid);
							}
						} ]
					});

					//menu的showAt，不要忘记  
					chlidNodeClickMenu.showAt(e.getPoint());
					return false;
				}
			}
		//#listeners
		});//#treePanel

		//# myMenuDel 开始
		function myMenuDel(kid) {
			Ext.Msg.confirm("提示:", "确定删除选定的记录?", function(e) {
				if (e == "yes") {
					Ext.Ajax.request({
						url : getServerHttp() +"/cp/shop/menu/auth/del.htm?kid=" + kid,
						success : function(response) {
							var json = Ext.decode(response.responseText);
							Ext.Msg.alert("提示", json.tip.msg);
							treeStore.load();
						},
						failure : function(response) {
							Ext.Msg.alert("提示", "操作失败!");
						}
					});
				}//#if
			});
		}//@ myMenuDel 结束

		//myMenuAdd 开始 
		var menu_add_form_panel = Ext.create("Ext.form.Panel", {
			url : getServerHttp() + "/cp/shop/menu/auth/add.htm",
			buttonAlign : "center",
			bodyStyle : "padding: 10px;",
			width : 500,
			defaultType : "textfield",
			items : [ {
				name : "menu_id",
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
					maxLength : 3
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
				}/*, {
					name : "type",
					boxLabel : "按钮",
					inputValue : "按钮"
				} */]
			}],
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
								menu_add_form_panel_win.close();
								treeStore.load();
							},
							failure : function(form, action) {
								Ext.Msg.alert("提示", action.result.tip.msg);
							}
						});
					}
				}
			} ]
		});
		
		var menu_add_form_panel_win = Ext.create("Ext.Window", {
			title : "新增菜单",
			closeAction : "hide",
			items : menu_add_form_panel,
			modal:true
		});
		
		
		function myMenuAdd(up_code, up_name) {
			menu_add_form_panel.getForm().reset();
			menu_add_form_panel.getForm().setValues({
				
				"up_node" : up_name + "[" + up_code + "]",
				"up_code" : up_code
			});
			menu_add_form_panel.getForm().findField("menu_id").setValue(mymenu_id)
			menu_add_form_panel_win.show();
		}
		//@ myMenuAdd 结束
		
		

		//@ myMenuEdit 结束
		//校验 code 长度
		var codeLength = 0;
		var menu_edit_form_panel = Ext.create("Ext.form.Panel", {
			url : getServerHttp() + "/cp/shop/menu/auth/edit.htm",
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
				name : "code"
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
				}/*, {
					name : "type",
					boxLabel : "按钮",
					inputValue : "按钮"
				} */]
			}],
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
								menu_edit_form_panel_win.close();
								treeStore.load();
							},
							failure : function(form, action) {
								Ext.Msg.alert("提示", action.result.tip.msg);
							}
						});
					}
				}
			} ]
		});

		var menu_edit_form_panel_win = Ext.create("Ext.Window", {
			title : "菜单编辑",
			closeAction : "hide",
			items : menu_edit_form_panel,
			modal:true
		});

		function myMenuEdit(kid) {
			Ext.Ajax.request({
				url : getServerHttp() + "/cp/shop/menu/auth/edit_form.htm?kid=" + kid,
				success : function(response) {
					//console.log(response.responseText);
					var json = Ext.JSON.decode(response.responseText);
					//console.log(json);
					menu_edit_form_panel.getForm().reset();
					menu_edit_form_panel.getForm().setValues(json);
					menu_edit_form_panel_win.show();
					
					//alert(edit_form_panel.getForm().findField("kid").getValue());
					codeLength = Ext.getCmp("code").getValue().length;
				},
				failure : function(response) {
					Ext.Msg.alert("提示", "操作失败!");
				}
			});
		}//#myEdit

		
/**
 * ———————————————————— 菜单权限结束 ————————————————————————————————————————————————————————————————————————
 */