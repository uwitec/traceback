var gsonStore = Ext.create('Ext.data.Store', {
	remoteSort : true,
	autoLoad : true,
	pageSize : 8,
	sorters : {
		property : 'created',
		direction : 'ASC'
	},
	proxy : {
		type : "ajax",
		url : "/traceback/shop/product/guanjiankzd/list_data.htm",
		reader : {
			type : 'json',
			root : 'list',
			totalProperty : 'page.totalRow' 
		}
	},
	listeners : {
		'beforeload' : function(store, op, options) {
			var params = {
				kid : parent_id
			};
			Ext.apply(gsonStore.proxy.extraParams, params);
		}
	}
}); // #sonStore

var gedit_son_panel = Ext.create('Ext.grid.Panel', {
	store : gsonStore,
	tbar : [ {
		text : '添加',
		xtype : 'button',
		icon : jcapp.getIcon("add.png"),
		handler : function() {
			gaddSon();
		}
	} ],
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : gsonStore, // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : [{
		text : '控制点名称',
		dataIndex : 'name',
		flex : 1
	}, {
		text : '控制点单位',
		dataIndex : 'unit',
		flex : 1
	},{
		xtype : "actioncolumn",
		align : "center",
		text : '操作',
		flex : 1,
		items : [ {
			xtype : 'button',
			tooltip : '修改',
			icon : jcapp.getIcon("application_form_edit.png"),
			handler : function(grid, rowIndex, colIndex) {
				var rec = grid.getStore().getAt(rowIndex);
				//alert("编辑 " + rec.get('kid'));
				geditSon(rec.get('kid'));
			}
		},{xtype:"container"}, {
			xtype : 'button',
			tooltip : '删除',
			icon : jcapp.getIcon("cross.png"),
			handler : function(grid, rowIndex, colIndex) {
				var rec = grid.getStore().getAt(rowIndex);
				gdelSon(rec.get('kid'));					
			}				
		}]

	} ]
}); // #gedit_form_panel

var gedit_son_panel_win = Ext.create("Ext.Window", {
	title : "产品关键控制点设定",
	closeAction : "hide",
	buttonAlign : "center",
	closeAction : "hide",
	width : 800, // 宽度
	height : 500, // 长度
	layout : "fit", // 窗口布局类型
	maximizable : true, // 设置是否可以最大化,
	modal:true,
	items : [ gedit_son_panel ]
});

var parent_id = '';
function myGeditSon(kid) {
	parent_id = kid;
	gedit_son_panel_win.show();
}// #myGeditSon

var gaddSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/product/guanjiankzd/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "product_id",
		hidden : true
	}, {
		xtype : 'textfield',
		fieldLabel : "控制点名称",
		name : "name",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "控制点单位",
		name : "unit",
		allowBlank : false
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
						gaddSon_form_panel_win.close();
						gsonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var gaddSon_form_panel_win = Ext.create("Ext.Window", {
	title : "填写关键控制点",
	closeAction : "hide",
	items : gaddSon_form_panel,
	modal:true
});

function gaddSon() {
	gaddSon_form_panel.getForm().reset();
	gaddSon_form_panel.getForm().findField('product_id').setValue(parent_id);
	gaddSon_form_panel_win.show();
}

function gdelSon(kid) {
	Ext.Msg.confirm("提示:", "确定删除选定的记录?", function(e) { 
		if (e == "yes") {
			Ext.Ajax.request({
				url : "/traceback/shop/product/guanjiankzd/del.htm?kid=" + kid,
				success : function(response) {
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示", json.tip.msg);
					gsonStore.load();
				},
				failure : function(response) {
					Ext.Msg.alert("提示", "操作失败!");
				}
			});
		}//#if
	});
}//#gdelSon

var geditSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/product/guanjiankzd/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "product_id",
		hidden : true
	}, {
		xtype : 'textfield',
		fieldLabel : "控制点名称",
		name : "name",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "控制点单位",
		name : "unit",
		allowBlank : false
	}, {
		fieldLabel : "id",
		name : "kid",
		hidden : true
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
						geditSon_form_panel_win.close();
						gsonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var geditSon_form_panel_win = Ext.create("Ext.Window", {
	title : "关键控制点修改",
	closeAction : "hide",
	items : geditSon_form_panel,
	modal:true
});

function geditSon(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/product/guanjiankzd/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			geditSon_form_panel.getForm().reset();
			geditSon_form_panel.getForm().setValues(json);
			geditSon_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #geditSon