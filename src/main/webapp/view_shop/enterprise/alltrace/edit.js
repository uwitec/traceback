var sonStore = Ext.create('Ext.data.Store', {
	remoteSort : true,
	autoLoad : true,
	pageSize : 15,
	sorters : {
		property : 'created',
		direction : 'DESC'
	},
	proxy : {
		type : "ajax",
		url : "/traceback/shop/production/Super/list_data.htm",
		reader : {
			type : 'json',
			root : 'list',
			totalProperty : 'page.totalRow' 
		}
	},
	listeners : {
		'beforeload' : function(store, op, options) {
			var params = {
					shop_id : shop_id
			};
			Ext.apply(sonStore.proxy.extraParams, params);
		}
	}
}); // #detailStore

var edit_son_panel = Ext.create('Ext.grid.Panel', {
	store : sonStore,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : sonStore, // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : [{
		text : '生产日期(批次)',
		dataIndex : 'production_time',
		flex : 1,
		renderer : function(val) {
			if (val != '') {
				return Ext.Date.format(new Date(val), "Y-m-d ");
			}
		}
	}, {
		text : '产品名称',
		dataIndex : 'name',
		flex : 1
	}, {
		text : '条码',
		dataIndex : 'barcode',
		flex : 1
	}, {
		text : '追溯码',
		dataIndex : 'trace_code',
		flex : 1,
		renderer : function(value,cellmeta, record,rowIndex, columnIndex,store) {
			var trace_url = "/traceback/shop/frame/trace/inquiry_detial_pc.htm?trace_code=" + record.data['trace_code'];
			return '<a target="_blank" href="'+trace_url+'">'+record.data['trace_code']+'</a>';
		}
	}, {
		xtype : "actioncolumn",
		align : "center",
		text : '操作',
		flex : 1,
		items : [{
			xtype : 'button',
			tooltip : '详情',
			icon : jcapp.getIcon("application_form_magnify.png"),
			handler : function(grid, rowIndex, colIndex) {
				//Ext.MessageBox.wait("加载中...", "提示");
				var rec = grid.getStore().getAt(rowIndex);
				window.open("/traceback/shop/frame/trace/index.htm?kid="+rec.get('kid'));  
			}
		}]

	} ]
}); // #edit_form_panel

var edit_son_panel_win = Ext.create("Ext.Window", {
	title : "企业生产信息",
	closeAction : "hide",
	buttonAlign : "center",
	closeAction : "hide",
	width : 800, // 宽度
	height : 500, // 长度
	layout : "fit", // 窗口布局类型
	maximizable : true, // 设置是否可以最大化,
	modal:true,
	items : [ edit_son_panel ]
});

var shop_id = '';
function myEditSon(kid) {
	shop_id = kid;
	edit_son_panel_win.show();
}// #myEditSon
