var tagDataStore = Ext.create('Ext.data.Store', {
	remoteSort : true,
	autoLoad : true,
	pageSize : 10,
	sorters : {
		property : 'created',
		direction : 'DESC'
	},
	proxy : { 
		type : "ajax",
		url : "/traceback/shop/production/tag/list_data.htm",
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
			Ext.apply(tagDataStore.proxy.extraParams, params);
		}
	}
}); //#tagDataStore

var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/production/new_tag.htm",
	buttonAlign : "center",
	width : 500,
	height : 140,
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		xtype : 'textfield',
		fieldLabel : "起始号段",
		name : "start_code",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "结束号段",
		name : "end_code",
		allowBlank : false
	}, {
		fieldLabel : "id",
		name : "kid",
		hidden : true
	}],
	buttons : [ {
		text : "分配",
		formBind : true, // only enabled once the form is valid
		disabled : true,
		handler : function() {
			var form = this.up("form").getForm();
			if (form.isValid()) {
				form.submit({
					waitMsg : "分配...",
					success : function(form, action) {
						Ext.Msg.alert("提示", action.result.tip.msg);
						tagDataStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败");
					}
				});
			}
		}
	} ]
});

var find_member_panel = Ext.create('Ext.grid.Panel', {
	tbar : edit_form_panel,
	store : tagDataStore,
	buttonAlign : "center",
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : tagDataStore, // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : [{
		text : '起始号段',
		dataIndex : 'start_code',
		flex : 1
	}, {
		text : '结束号段',
		dataIndex : 'end_code',
		flex : 1
	}, {
		text : '分配时间',
		dataIndex : 'created',
		flex : 1,
		renderer : function(val) {
			if (val != '') {
				return Ext.Date.format(new Date(val), "Y-m-d H:i:s");
			}
		}
	}]
});


var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "标签分配",
	closeAction : "hide",
	buttonAlign : "center",
	closeAction : "hide",
	width : 700, // 宽度
	layout : "fit", // 窗口布局类型
	maximizable : true, // 设置是否可以最大化
	items : [find_member_panel],
	modal:true
});

var parent_id = '';
function myEdit(kid) {
	parent_id = kid;
	Ext.Ajax.request({
		url : "/traceback/shop/production/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();
			edit_form_panel.getForm().setValues(json);
			edit_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #myEdit
