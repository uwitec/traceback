var sonStore = Ext.create('Ext.data.Store', {
	remoteSort : true,
	autoLoad : true,
	pageSize : 8,
	sorters : {
		property : 'step',
		direction : 'ASC'
	},
	proxy : {
		type : "ajax",
		url : "/traceback/shop/shengcangc/list_data.htm",
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
			Ext.apply(sonStore.proxy.extraParams, params);
		}
	}
}); // #sonStore

var edit_son_panel = Ext.create('Ext.grid.Panel', {
	store : sonStore,
	tbar : [ {
		text : '添加',
		xtype : 'button',
		icon : jcapp.getIcon("add.png"),
		handler : function() {
			addSon();
		}
	} ],
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : sonStore, // same store GridPanel is using
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
		text : '开始时间',
		dataIndex : 'start_time',
		flex : 1,
		renderer : function(val) {
			if (val != '') {
				return Ext.Date.format(new Date(val), "Y-m-d H:i:s");
			}
		}
	}, {
		text : '结束时间',
		dataIndex : 'end_time',
		flex : 1,
		renderer : function(val) {
			if (val != '') {
				return Ext.Date.format(new Date(val), "Y-m-d H:i:s");
			}
		}
	}, {
		text : '工艺制作人',
		dataIndex : 'operator',
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
				editSon(rec.get('kid'));
			}
		},{xtype:"container"}, {
			xtype : 'button',
			tooltip : '删除',
			icon : jcapp.getIcon("cross.png"),
			handler : function(grid, rowIndex, colIndex) {
				var rec = grid.getStore().getAt(rowIndex);
				delSon(rec.get('kid'));					
			}				
		} ]

	} ]
}); // #edit_form_panel

var edit_son_panel_win = Ext.create("Ext.Window", {
	title : "产品生产过程",
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

var parent_id = '';
function myEditSon(kid) {
	parent_id = kid;
	edit_son_panel_win.show();
}// #myEditSon

var addSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/shengcangc/add.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "parent_id",
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
		xtype : 'datefield',
		fieldLabel : "开始时间",
		name : "dstart_time",
		format: 'Y-m-d H:i:s',
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "完成时间",
		name : "dend_time",
		format: 'Y-m-d H:i:s',
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "工艺制作人",
		name : "operator",
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
						addSon_form_panel_win.close();
						sonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var addSon_form_panel_win = Ext.create("Ext.Window", {
	title : "填写生产过程",
	closeAction : "hide",
	items : addSon_form_panel,
	modal:true
});

function addSon() {
	addSon_form_panel.getForm().reset();
	addSon_form_panel.getForm().findField('parent_id').setValue(parent_id);
	addSon_form_panel_win.show();
}

function delSon(kid) {
	Ext.Msg.confirm("提示:", "确定删除选定的记录?", function(e) { 
		if (e == "yes") {
			Ext.Ajax.request({
				url : "/traceback/shop/shengcangc/del.htm?kid=" + kid,
				success : function(response) {
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示", json.tip.msg);
					sonStore.load();
				},
				failure : function(response) {
					Ext.Msg.alert("提示", "操作失败!");
				}
			});
		}//#if
	});
}//#delSon

var editSon_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/shengcangc/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		fieldLabel : "父ID",
		name : "parent_id",
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
		xtype : 'datefield',
		fieldLabel : "开始时间",
		name : "dstart_time",
		format: 'Y-m-d H:i:s',
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "完成时间",
		name : "dend_time",
		format: 'Y-m-d H:i:s',
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "工艺制作人",
		name : "operator",
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
						editSon_form_panel_win.close();
						sonStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "操作失败,请联系管理员");
					}
				});
			}
		}
	} ]
});

var editSon_form_panel_win = Ext.create("Ext.Window", {
	title : "生产过程修改",
	closeAction : "hide",
	items : editSon_form_panel,
	modal:true
});

function editSon(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/shengcangc/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			editSon_form_panel.getForm().reset();
			editSon_form_panel.getForm().setValues(json);
			editSon_form_panel.getForm().findField('dstart_time').setValue(dateFormat_2(json.start_time));
			editSon_form_panel.getForm().findField('dend_time').setValue(dateFormat_2(json.end_time));
			editSon_form_panel_win.show();
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