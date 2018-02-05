var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/production/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [  {
		xtype : 'textfield',
		fieldLabel : "条码",
		name : "barcode",
		allowBlank : false
	},/* {
		xtype : 'combo',
		multiSelect:false,
		minChars : 1,
		fieldLabel : "产品",
		name : "name",
		emptyText : '请选择产品',
		store : comboxStore,
		displayField : 'name',
		valueField : 'name',
		hiddenName : 'name',
		triggerAction : 'all',
		selectOnFocus : true,
		forceSelection : true,
		allowBlank : false
	}*/ {
		xtype : 'textfield',
		fieldLabel : "产品名称",
		name : "name",
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "生产日期",
		name : "dproduction_time",
		format: 'Y-m-d H:i:s'
	}, {
		xtype : 'numberfield',
		fieldLabel : "生产数量",
		name : "number",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "包装单位",
		name : "unit",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "合格证名称",
		name : "certificate_name",
		allowBlank : false
	}, {
		xtype : 'datefield',
		fieldLabel : "签发日期",
		name : "dcertificate_start_time",
		format: 'Y-m-d H:i:s', 
		allowBlank : false
	}, {
		xtype : "radiogroup",
		fieldLabel : "合格证状态",
		allowBlank : false,
		style : 'margin-left:5px',
		labelWidth : 130,
		width : 300,
		items : [ {
			name : "certificate_status",
			boxLabel : "有效",
			inputValue : "有效",
			checked : true
		}, {
			name : "certificate_status",
			boxLabel : "无效",
			inputValue : "无效"
		} ]
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
						edit_form_panel_win.close();
						dataStore.load();
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存失败");
					}
				});
			}
		}
	} ]
});

var edit_form_panel_win = Ext.create("Ext.Window", {
	title : "修改",
	closeAction : "hide",
	items : edit_form_panel,
	modal:true
});

function myEdit(kid) {
	Ext.Ajax.request({
		url : "/traceback/shop/production/edit_form.htm?kid=" + kid,
		success : function(response) {
			var json = Ext.JSON.decode(response.responseText);
			edit_form_panel.getForm().reset();

			edit_form_panel.getForm().setValues(json);
			edit_form_panel.getForm().findField('dproduction_time').setValue(dateFormat_2(json.production_time));
			edit_form_panel.getForm().findField('dcertificate_start_time').setValue(dateFormat_2(json.certificate_start_time));
			edit_form_panel_win.show();
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