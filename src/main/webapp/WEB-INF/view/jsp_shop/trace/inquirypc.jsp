<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>食品生产质量追溯</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   	<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
    <link rel="stylesheet" href="${path}/resource/rqq/css/weui.min.css">
    <link rel="stylesheet" href="${path}/resource/rqq/css/jquery-weui.min.css">
    <link rel="stylesheet" href="${path}/resource/rqq/css/inquirypc.css">
</head>
<body>
<div class="main-img">
    <img src="${path}/resource/rqq/img/bg1.jpg">
<div class="main">
    <img src="${path}/resource/rqq/img/bg.jpg" />
    <div class="weui-cells_form">
        <div class="weui-cell__hd">
            <label class="weui-label"></label>
        </div>
        <div class="weui-btn-area">
            <input class="device device-fixed" type="text" placeholder="请输入追溯码">
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">一键溯源</a>
        </div>
    </div>
    <div class="weui-footer"><p>关注质量安全   共建和谐社会</p></div>
</div>
</div>
<script src="${path}/resource/rqq/js/jquery-2.1.4.js"></script>
<script src="${path}/resource/rqq/js/jquery-weui.min.js"></script>
</body>
</html>