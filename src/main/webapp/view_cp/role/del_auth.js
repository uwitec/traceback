var dels_form_panel;
var dels_form_panel_win;
var delsTreePanel;
var delsTreeStore;

function myDels(name, id) {
	delsTreeStore = Ext.create('Ext.data.TreeStore', {
		proxy : {
			type : 'ajax',
			url : getServerHttp() + "/cp/role/dels_form.htm?id=" + id,
			reader : {
				type : 'json',
			}
		}
	});

	delsTreePanel = Ext.create('Ext.ux.tree.TriStateTreePanel', {
		store : delsTreeStore,
		rootVisible : false,
		width : 400,
		height : 300
	});

	dels_form_panel = Ext.create("Ext.form.Panel", {
		url : getServerHttp()+"/cp/role/dels",
		buttonAlign : "center",
		bodyStyle : "padding: 10px;",
		defaultType : "textfield",
		items : [ {
			fieldLabel : "角色名 ",
			name : "name",
			allowBlank : false,
			readOnly : true
		}, {
			fieldLabel : "权限",
			name : "codes",
			hidden : true
		}, {
			fieldLabel : "id",
			name : "kid",
			hidden : true
		}, {
			xtype : "fieldcontainer",
			fieldLabel : "权限选择",
			layout : "hbox",
			items : [ delsTreePanel ]
		} ],
		buttons : [ {
			text : "删除",
			formBind : true, // only enabled once the form is valid
			disabled : true,
			handler : function() {
				var form = this.up("form").getForm();
				Ext.Msg.confirm("提示:", "确定删除选定的权限?", function(e) {
					if (e == "yes") {
						// 设置选择框
						var codes = delsTreePanel.getLeafIdSelections();
						form.setValues({
							"codes" : codes
						});

						if (form.isValid()) {
							form.submit({
								waitMsg : "删除中...",
								success : function(form, action) {
									Ext.Msg.alert("提示", action.result.tip.msg);
									dels_form_panel_win.close();
									dataStore.load();
								},
								failure : function(form, action) {
									Ext.Msg.alert("提示", action.result.tip.msg);
								}
							});
						}
					}
				});
			}
		} ]
	});

	dels_form_panel_win = Ext.create("Ext.Window", {
		title : "批量删除角色 ",
		closeAction : "hide",
		items : dels_form_panel
	});
	dels_form_panel.getForm().reset();
	dels_form_panel.getForm().setValues({
		"name" : name,
		"kid" : id
	});
	dels_form_panel_win.show();
}
