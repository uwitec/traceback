<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../jsp_cp/common/init.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" id="fkpath" val="${path}" />
<title>质量追溯系统 - 注册</title>
<!--  CSS -->
<link href="${path}/resource/style4cp/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/resource/style4cp/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${path}/resource/style4cp/extend/Validation/css/validationEngine.jquery.css">
<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="${path}/resource/style4cp/extend/Validation/js/jquery.validationEngine.min.js"></script>
<script src="${path}/resource/style4cp/extend/Validation/js/jquery.validationEngine-zh_CN.js"></script>
<style>
body{ background-color:#fff;  padding-top:20px;}
.reg{width:680px;}
</style>
</head>

<body>
<!--登陆-->
<div class="container reg">
	<h3 >质量追溯系统 - 注册</h3>
	<hr>
	<form id="reg-form" method="post" action="${path}/reg_shop.htm">
	<div class="form-group">
 	 <label >*企业名称：</label>
 		<input type="text" id="m_name" name="m_name" class="form-control" placeholder="企业/厂家全称"
 			data-validation-engine="validate[required,ajax[checkMerchantsNickUser]]" data-errormessage-value-missing="企业名称不能为空" />
 	</div>
 	<div class="form-group">
 	 <label id="add_f">*企业地址：</label>
 	 	<input type="text" name="province" hidden="hidden">
 	 	<input type="text" name="city" hidden="hidden">
 		<input type="text" id="addres" name="addres" class="form-control" placeholder="企业具体地址"
 			data-validation-engine="validate[required,ajax[checkMerchantsNickUser]]" data-errormessage-value-missing="企业地址必填" />
 	</div>
	<div class="form-group">
	 <label >*姓名：</label>
 		<input type="text" id="username" name="username" class="form-control" placeholder="管理人姓名" autocomplete="off"
 			data-validation-engine="validate[required]" data-errormessage-value-missing="联系人姓名不能为空" />
 	</div>
	<div class="form-group">
	 <label >*手机号码：</label>
 		<input type="text" id="inputUsername" name="mobile" class="form-control" placeholder="今后使用手机号码登录" autocomplete="off"
 			data-validation-engine="validate[required,[custom[mphone]]" data-errormessage-value-missing="手机号码不能为空" />
 	</div>
<!--  	<div class="form-group">
 	 <label >*短信校验码：</label>
 	</div>
 	 <div class="input-group">
      <input type="text" id="mcode" name="code" class="form-control" placeholder="短信验证码"
 			data-validation-engine="validate[required]" data-errormessage-value-missing="短信验证码不能为空" />
      <span class="input-group-btn">
      <button id="getMcode" class="form-control" type="button" disabled="disabled">获取验证码</button>
      </span>
    </div>/input-group
    <br> -->
    <div class="form-group ">
    <label >*设置密码：</label>
		<input type="password" id="pwd" name="pwd" class="form-control " placeholder="密码"
  			data-validation-engine="validate[required]" data-errormessage-value-missing="密码不能为空"/>
	</div>
	<div class="form-group ">
	 <label >*重复密码：</label>
	<input type="password" id="repwd" name="repwd" class="form-control" placeholder="确认密码"
  			data-validation-engine="validate[equals[pwd]]"/>
	</div>
	<br>
    <button id="mer-reg-btn" class="btn btn-block btn-primary btn-lg">注册</button>
    <div class="info" >
    <br>
    	<p><a href="${path}/login_shop.htm">已有账号? 请登录</a><p>
    </div>
	</form>
	<hr>
<div class="footer text-center">
 	<p >&copy; 2016 2016 All Rights Reserved.<a href="http://www.sxxlkj.com/" target="_blank" style="color: #fd5806;"> 讯龙科技 </a></p>
</div>
</div>


<script type="text/javascript">
$('#reg-form').validationEngine({
	scroll : false,
	promptPosition : 'topRight',
	maxErrorsPerField : 1,
	showOneMessage : true,
	ajaxFormValidation: true,
	ajaxFormValidationURL : '${path}/reg_shop.htm',
    ajaxFormValidationMethod: 'post',
    onAjaxFormComplete: ajaxValidationCallback
});
	
function ajaxValidationCallback(status, form, json, options){
	console.log(status)
	console.log(form)
	console.log(json)
	if(status === true){
		if(json.error == "false"){
			window.top.location.href = "${path}/login_shop.htm?loginMsg=注册成功,请登录"
		 }else{
	  		alert(json.msg);
		 }
	}
}


var b = true;
$(function() {
	$('#inputUsername').bind('input propertychange', function(){
	//手机校验
	var phoneNum = /^1[345789]\d{9}$/;
	var phone = $("#inputUsername").val();
		console.log(b);
		if(phoneNum.test(phone)){
			if (b == true) {
				$("#getMcode").attr('disabled', false);
			}
		}else{
			$("#getMcode").attr('disabled',true);
		}
	});
	
	$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(_result) {
		document.getElementById("add_f").innerText="*企业地址：("+remote_ip_info.province+"省"+remote_ip_info.city+"市"+remote_ip_info.district+")";
		$("input[name='province']").val(remote_ip_info.province+"省");
		$("input[name='city']").val(remote_ip_info.city+"市");
    });
});

function startInterval() {
	b = false;
	var i = 0;
	var Interval = setInterval(function() {
		i++;
		$("#getMcode").html((60 - i) + "s");
		if (i >= 60) {
			b = true;
			$("#getMcode").html("获取验证码");
			window.clearInterval(Interval); //清楚定时器
		}
	}, 1000);
}//#startInterval

$("#getMcode").click(function(){
	if (b == false) {
		return;
	}
	
	//手机校验
	var phoneNum = /^1[345789]\d{9}$/;
	var phone = $("#inputUsername").val();
	if(phoneNum.test(phone)){
		startInterval();
		$.get('${path}/sendCode.htm?phoneNum='+phone,function(result){
			if(result=="NO"){
				//layer.msg("手机号码已经存在！");
				alert("手机号码已经存在");
				return;
			}else{
				//layer.msg("发送成功");
				//alert("发送成功");
				$("#mcode").focus();
				$("#getMcode").attr('disabled',false);
			}
		})
	}else{
		//layer.msg("不是有效的手机号码");
		alert("不是有效的手机号码");
		$("#inputUsername").focus();
	}	
});

</script>
</body>
</html>
