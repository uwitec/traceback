var ssonStore = Ext.create('Ext.data.Store', {
	remoteSort : true,
	autoLoad : true,
	pageSize : 8,
	sorters : {
		property : 'step',
		direction : 'ASC'
	},
	proxy : {
		type : "ajax",
		url : "/traceback/shop/product/shengcangc/list_data.htm",
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
			Ext.apply(ssonStore.proxy.extraParams, params);
		}
	}
}); // #ssonStore

var sedit_son_panel = Ext.create('Ext.grid.Panel', {
	store : ssonStore,
	tbar : [ {
		text : '添加',
		xtype : 'button',
		icon : jcapp.getIcon("add.png"),
		handler : function() {
			saddSon();
		}
	} ],
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : ssonStore, // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : [{
		text : '步骤',
		dataIndex : 'step',
		flex : 1
	}, {
		text : '工艺名称',
		dataIndex : 'name',
		flex : 1
	}, {
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
				seditSon(rec.get('kid'));
			}
		},{xtype:"container"}, {
			xtype : 'button',
			tooltip : '删除',
			icon : jcapp.getIcon("cross.png"),
			handler : function(grid, rowIndex, colIndex) {
				var rec = grid.getStore().getAt(rowIndex);
				sdelSon(rec.get('kid'));					
			}				
		} ]

	} ]
}); // #sedit_form_panel

var sedit_son_panel_win = Ext.create("Ext.Window", {
	title : "产品生产过程设定",
	closeAction : "hide",
	buttonAlign : "center",
	closeAction : "hide",
	width : 800, // 宽度
	height : 500, // 长度
	layout : "fit", // 窗口布局类型
	maximizable : true, // 设置是否可以最大化,
	modal:true,
	items : [ sedit_son_panel ]
});

var parent_id = '';
function mySeditSon(kid) {
	parent_id = kid;
	sedit_son_panel_win.show();
}// #mySeditSon

var saddSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/product/shengcangc/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "product_id",
		hidden : true
	}, {
		xtype : 'numberfield',
		fieldLabel : "步骤",
		name : "step",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "工艺名称",
		name : "name",
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
						saddSon_form_panel_win.close();
						ssonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var saddSon_form_panel_win = Ext.create("Ext.Window", {
	title : "填写生产过程",
	closeAction : "hide",
	items : saddSon_form_panel,
	modal:true
});

function saddSon() {
	saddSon_form_panel.getForm().reset();
	saddSon_form_panel.getForm().findField('product_id').setValue(parent_id);
	saddSon_form_panel_win.show();
}

function sdelSon(kid) {
	Ext.Msg.confirm("提示:", "确定删除选定的记录?", function(e) { 
		if (e == "yes") {
			Ext.Ajax.request({
				url : "/traceback/shop/product/shengcangc/del.htm?kid=" + kid,
				success : function(response) {
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示", json.tip.msg);
					ssonStore.load();
				},
				failure : function(response) {
					Ext.Msg.alert("提示", "操作失败!");
				}
			});
		}//#if
	});
}//#delSon

var seditSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/product/shengcangc/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "product_id",
		hidden : true
	}, {
		xtype : 'numberfield',
		fieldLabel : "步骤",
		name : "step",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "工艺名称",
		name : "name",
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
						seditSon_form_panel_win.close();
						ssonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var seditSon_form_panel_win = Ext.create("Ext.Window", {
	title : "生产过程修改",
	closeAction : "hide",
	items : seditSon_form_panel,
	modal:true
});

function seditSon(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/product/shengcangc/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			seditSon_form_panel.getForm().reset();
			seditSon_form_panel.getForm().setValues(json);
			seditSon_form_panel_win.show();
		},
		failure : function(response) {
			Ext.Msg.alert("提示", "操作失败!");
		}
	});
}// #editAnswer

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