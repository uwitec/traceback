jcapp = {};
jcapp.weight="0";

// 图标资源
// http://7xidlb.com1.z0.glb.clouddn.com/icons/icon.html
jcapp.getIcon = function(icon) {
	return "http://7xidlb.com1.z0.glb.clouddn.com/icons/"+icon;
};
// 初始化tip插件
jcapp.initTipPlugin = function() {
	try{Ext.QuickTips.init();}catch(e){}
	Ext.Ajax.on('beforerequest', ext_beforerequest, this);
	Ext.Ajax.on('requestcomplete', ext_requestcomplete, this);

	function ext_beforerequest(conn, options) {
		try{
		if(options.url.substring(0,4)=="http"){
			return;
		}
		}catch(e){
			alert(e);
		};
		options.url = jcapp.server + options.url;
		//console.log(options);
		conn.setOptions(options);
	}
	function ext_requestcomplete(conn, response, options) {
		var respObj = Ext.JSON.decode(response.responseText);
		// console.log(response);
		if (respObj.loginVar == "false") {
			window.top.location.href = getServerHttp()+"/login_cp.html";
		}
		// if (respObj.tip != null && respObj.tip.msg != null) {
		// Ext.Msg.alert('提示', respObj.tip.msg);
		// }
	}
};

Ext.onReady(function() {
	try{Ext.QuickTips.init();}catch(e){}
	autoRefresh();
});

function autoRefresh(){
	// Ctrl+Enter 实现自动刷新 
	Ext.getDoc().on("keydown",function(e){
		if(e.ctrlKey && e.getKey() == 13){
			window.top.workspace.location.reload(true);
		}
    });
}

// 获取服务器 ip+端口
function getServerHttp() {
	if (window.parent === window) {
	}else{
		return window.top.jcapp.server;
	}
}


//初始化必填项*
Ext.override(Ext.form.field.Base, {
	initComponent : function() {
		if (this.allowBlank !== undefined && !this.allowBlank) {
			if (this.fieldLabel) {
				this.fieldLabel += '<span style="color:red">*</span>';
			}
		}
		this.callParent(arguments);
	}
});

//唯一性验证
function uniqueValidator(table,field,value) {
	var error = true;
	Ext.Ajax.request({
		url : '/valid/check_unique.htm',
		params : {
			table : table,
			field : field,
			value : value
		},
		scope : this,
		async : false,
		success : function(response) {
			var result = Ext.JSON.decode(response.responseText);
			if ("true" == result.fail) {
				error = "该字段己经存在,请重新输入！";
			}
		}
	});
	return error;
}	 

//获取地址栏参数值
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; //返回参数值
}


