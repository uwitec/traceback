Date.prototype.Format = function(fmt)   
 { //author: meizz   
   var o = {   
     "M+" : this.getMonth()+1,                 //月份   
     "d+" : this.getDate(),                    //日   
     "h+" : this.getHours(),                   //小时   
     "m+" : this.getMinutes(),                 //分   
     "s+" : this.getSeconds(),                 //秒   
     "q+" : Math.floor((this.getMonth()+3)/3), //季度   
     "S"  : this.getMilliseconds()             //毫秒   
   };   
   if(/(y+)/.test(fmt))   
     fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
   for(var k in o)   
     if(new RegExp("("+ k +")").test(fmt))   
   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
   return fmt;   
 } 

function getToday0(){
	///获取当前日期(到秒)
	var curDate = new Date()
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 00:00:00";
}

function getToday(){
	///获取当前日期(到秒)
	var curDate = new Date()
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time;
}


function getToday8(){
	///获取当前日期(到秒)
	var curDate = new Date()
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 08:00:00";
}

function getToday23(){
	///获取当前日期(到秒)
	var curDate = new Date()
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 23:59:59";
}

function getTomorrow8(){
	///获取当前日期(到秒)
	var curDate = new Date();
	curDate.setDate(curDate.getDate()+1);
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 08:00:00";
}

function getYesterday17(){
	///获取当前日期(到秒)
	var curDate = new Date();
	curDate.setDate(curDate.getDate()-1);
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 17:00:00";
}

function getTomorrow0(){
	///获取当前日期(到秒)
	var curDate = new Date();
	curDate.setDate(curDate.getDate()+1);
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time+" 00:00:00";
}

function getTomorrow(){
	///获取当前日期(到秒)
	var curDate = new Date();
	curDate.setDate(curDate.getDate()+1);
	//格式化;
	var time=Ext.Date.format(curDate, 'Y-m-d');
	return time;
}

function getNowYear(){
	var curDate = new Date()
	//格式化;
	return Ext.Date.format(curDate, 'Y');
}

function getNowMonthFristDay(){
	var curDate = new Date()
	//格式化;
	return Ext.Date.format(curDate, 'Y-m')+"-01";
}
