<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>质量追溯系统 - 修改密码</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
    <link href="${path}/resource/Hplus-v.4.1.0/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/style.css?v=4.1.0" rel="stylesheet">
   	<link rel="stylesheet" href="${path}/resource/sweetalert-master/dist/sweetalert.css">
	<script src="${path}/resource/sweetalert-master/dist/sweetalert.min.js"></script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改密码</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">当前密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/validate/messages_zh.min.js"></script>

    <script src="${path}/resource/Hplus-v.4.1.0/js/demo/form-validate-demo.js"></script>

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <!--统计代码，可删除-->

</body>

</html>
