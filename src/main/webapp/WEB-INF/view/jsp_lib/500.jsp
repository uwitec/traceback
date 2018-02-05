<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jsp_cp/common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>服务器内部错误</title>
    <link rel="shortcut icon" href="${path}/resource/img/favicon.ico"> 
    <link href="${path}/resource/Hplus-v.4.1.0/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>
        <div class="error-desc">服务器好像出错了...
            <br/>您可以返回看看
            <br/><a href="javascript:history.back(-1);" class="btn btn-primary m-t">返回</a>
        </div>
    </div>
    <script src="${path}/resource/Hplus-v.4.1.0/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
