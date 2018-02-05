var edit_form_panel = Ext.create("Ext.form.Panel", {
	url : "/traceback/shop/product/edit.htm",
	buttonAlign : "center",
	bodyStyle : "padding: 10px;",
	defaultType : "textfield",
	items : [{
		xtype : 'textfield',
		fieldLabel : "产品名称",
		name : "name",
		allowBlank : false
	}, {
		xtype : 'textfield',
		fieldLabel : "包装单位",
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
		url : "/traceback/shop/product/edit_form.htm?kid=" + kid,
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