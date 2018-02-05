<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../jsp_cp/common/init.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" id="fkpath" val="${path}" />
<title>****</title>
<!--  CSS -->
<link rel="stylesheet" href="${path}/resource/style4cp/css/login.css">
<link rel="stylesheet" href="${path}/resource/style4cp/extend/Validation/css/validationEngine.jquery.css">
<link rel="stylesheet" href="${path}/resource/sweetalert-master/dist/sweetalert.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<link rel="shortcut icon" href="./resource/img/favicon.ico"/>
<script src="${path}/resource/style4cp/extend/Validation/js/jquery.validationEngine.min.js"></script>
<script src="${path}/resource/style4cp/extend/Validation/js/jquery.validationEngine-zh_CN.js"></script>
<script src="${path}/resource/style4cp/extend/Validation/js/jquery.validationEngine.min.js"></script>
<script src="${path}/resource/sweetalert-master/dist/sweetalert.min.js"></script>
</head>
<style>
input:-webkit-autofill {
    -webkit-box-shadow: 0 0 0px 1000px white inset;
}
.login-logo{
	border-bottom-width: 1px;
    border-bottom-style: solid;
    border-bottom-color: #CCC;
    text-align: center;
    margin-bottom: 15px;
}
</style>
<body>
<div id="Layer1" style="position:absolute; width:100%; height:99.7%; z-index:-1">    
<img src="./resource/img/login.jpg" height="100%" width="100%"/>    
</div> 
<div id="login_body">
<!--登陆-->
<div class="container login">
	<div class="login-logo">
		<img alt="" src="${path }/resource/img/logo.png">
	</div>
	<form  class="login-form" id="login-form"  action="${path}/login_shop.htm" method="post">
	<div class="input-group  ">
		<input type="text" id="m_user" name="userName" class="login-user  form-control login-user" placeholder="账号(手机号码)"
 			data-validation-engine="validate[required]" data-errormessage-value-missing="账号不能为空" />
 	</div>
    <div class="input-group ">
	<input type="password" id="m_password" name="pwd" class="form-control login-password" placeholder="密码"
		data-validation-engine="validate[required]" data-errormessage-value-missing="密码不能为空"/>
	</div>
    <button class="form-control login-btn" type="submit">登录</button>
    <div style="color: red;">${loginMsg}</div>
	</form>
  	<div class="info" >
  	<p><!-- <a href="http://weibo.com/ttarticle/p/show?id=2309404072900637417464" target="_blank">产品使用教程</a></p> -->
		<p><a href="javascript:reg_shop();">新商户，去注册</a></p>
		<p><a href="javascript:to_moblie();">手机端登录《****》</a></p>
		<p><a href="javascript:feedback();">**** 正式版 v1.1 意见反馈</a></p>
		<p style="color: black;text-align:center;font-size: 18px;">公告</p>
		<textarea rows="5" cols="35%" readonly>****</textarea>
		<%-- <p><img src="${path}/resource/app/qrcode.png" width="80%" /></p> --%>
    </div>
</div>
<div class="clearfix"></div>
<div class="footer">
  <p > 京ICP备16062816号 &copy; 2016 <a href="http://www.mengxsh.top" target="_blank" style="color: #337ab7;"> 北京梦享生活科技 </a> 版权所有</p>
</div>
</div>
<script type="text/javascript">
	$('#login-form').validationEngine({
		scroll : false,
		promptPosition : 'topRight',
		maxErrorsPerField : 1,
		showOneMessage : true,
	});
	
	function feedback(){
		swal({
			  title: "服务中心",
			  text: "请描述您遇到的问题或您的意见、建议",
			  type: "input",
			  showCancelButton:true,
			  closeOnConfirm:false,
			  animation: "slide-from-top", //动画
			  confirmButtonText: "确定",
			  cancelButtonText: "取消"
			},
			function(inputValue){
			  if (inputValue === false) return false;
			  if (inputValue === "") {
			    swal.showInputError("你需要填入您遇到的问题！");
			    return false
			  }
			  
			  $.ajax({
					url : "${path}/feedback.htm?content="+inputValue,
					success : function(response) {
						swal("感谢反馈!", "我们会尽快解决您反馈的问题。", "success");
					},
					failure : function(response) {
						alert("服务器连接不上!");
					}
				});
			  
		});
	}
	
	function reg_shop(){
		  swal("注册通道已关闭!", "请联系工作室管理员购买使用权。");
	}

	function to_moblie(){
		swal({
			  title: "",
			  text: "扫描二维码进入<a href='${path}/mlogin_shop.htm' target='_blank' style='color: #337ab7;'>手机端商户登录</a>",
			  imageUrl: "${path}/resource/img/qrlogo.png",
			  html: true
			});
		
	}
</script>
</body>
</html>