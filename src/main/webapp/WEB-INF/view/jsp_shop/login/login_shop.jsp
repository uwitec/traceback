<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../jsp_cp/common/init.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>食品生产质量追溯云平台 </title>
    <link href="${path}/resource/Hplus-v.4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/style.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/login.css" rel="stylesheet">
	<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
	<link rel="stylesheet" href="${path}/resource/sweetalert-master/dist/sweetalert.css">
	<script src="${path}/resource/sweetalert-master/dist/sweetalert.min.js"></script>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
        
        document.onreadystatechange = loadingChange;//当页面加载状态改变的时候执行这个方法.    
        function loadingChange() {
            if(document.readyState == "complete"){//当页面加载状态为完全结束时进入     
                window.setTimeout(function(){
	                var ui =document.getElementById("bbs");
    	        	ui.style.display="none";
                },1000);
            }     
        }
    </script>

</head>

<body class="signin">
    <div class="spiner-cx" id="bbs">
	     <div class="spiner-example">
		      <div class="sk-spinner sk-spinner-fading-circle">
		          <div class="sk-circle1 sk-circle"></div>
		          <div class="sk-circle2 sk-circle"></div>
		          <div class="sk-circle3 sk-circle"></div>
		          <div class="sk-circle4 sk-circle"></div>
		          <div class="sk-circle5 sk-circle"></div>
		          <div class="sk-circle6 sk-circle"></div>
		          <div class="sk-circle7 sk-circle"></div>
		          <div class="sk-circle8 sk-circle"></div>
		          <div class="sk-circle9 sk-circle"></div>
		          <div class="sk-circle10 sk-circle"></div>
		          <div class="sk-circle11 sk-circle"></div>
		          <div class="sk-circle12 sk-circle"></div>
		          <div class="sk-circle13 sk-circle"></div>
		          <div class="sk-circle14 sk-circle"></div>
		          <div class="sk-circle15 sk-circle"></div>
		      </div>
		  </div>
    </div>
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ 质量追溯系统 ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>质量追溯系统</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 食者无虑，  </li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 饮者无忧， </li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 监管有法， </li> 
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 安全有保，  </li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 百姓安康。  </li> 
                    </ul>
                    <strong><a href="javascript:reg_shop();">忘记密码？&raquo;</a></strong><br>
                    <strong><a href="javascript:feedback();">系统不是完美的，我们渴望您的建议&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form id="login-form" method="post" action="${path}/login_shop.htm">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到质量追溯系统</p>
                    <input type="text" class="form-control uname" name="userName" placeholder="手机号（账号） " data-validation-engine="validate[required]" data-errormessage-value-missing="账号不能为空"/>
                    <input type="password" class="form-control pword m-b" name="pwd" placeholder="密码" data-validation-engine="validate[required]" data-errormessage-value-missing="密码不能为空" />
                    <!-- <a href="" >忘记密码了？</a> -->
                    <div style="color: red;">${loginMsg}</div>
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
				 &copy; 2016-2018 All Rights Reserved. <a href="http://www.sxxlkj.com/" target="_blank"> 讯龙科技 </a>
            </div>
        </div>
    </div>
    <script type="text/javascript">
   		function feedback(){
			swal({
				  title: "服务中心",
				  text: "请描述您遇到的问题或您的意见、建议",
				  type: "input",
				  showCancelButton:true,
				  closeOnConfirm:false,
				  //animation: "slide-from-top", //动画
				  cancelButtonText: "取消",
				  confirmButtonText: "确定",
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
			  swal("暂未开放此功能！", "正在开发中...  #如遇紧急情况请拨打客服 0351-3853826");
		}
		
    </script>
</body>

</html>
