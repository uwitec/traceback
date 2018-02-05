<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../jsp_cp/common/init.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>食品购销凭证</title>
<link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
<link rel="shortcut icon" href="${path }/resource/img/favicon.ico"/>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/swiper.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/city-picker.min.js"></script>
<style type="text/css">
	.demos-title {
	  text-align: center;
	  font-size: 34px;
	  /* color: #3cc51f; */
	  font-weight: 400;
	  margin: 0 15%;
	}
	
	.demos-header {
	  padding: 35px 0;
	  background-color: #fff;
	}
</style>
</head>
<body style="font-family: 微软雅黑;background-color: #EBEBEB" >
	<header class='demos-header'>
      <h1 class="demos-title">（电子一票通）</h1>
    </header>
	<div class="weui-form-preview">
	  <div class="weui-form-preview__hd">
	    <label class="weui-form-preview__label">账单日期</label>
	    <em class="weui-form-preview__value" style="font-size: 1.1em;">
	   			<jsp:useBean id="created_bill_time" class="java.util.Date" />
				<jsp:setProperty name="created_bill_time" property="time" value="${list1.created_bill_time }"/>
				<fmt:formatDate value="${created_bill_time}" type="date" dateStyle="long"/>
	    </em>
	  </div>
	  <div class="weui-form-preview__bd">
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">购货单位</label>
	      <span class="weui-form-preview__value">${list1.distributor_name }</span>
	    </div>
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">地址</label>
	      <span class="weui-form-preview__value">${list1.distributor_name }</span>
	    </div>
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">电话</label>
	      <span class="weui-form-preview__value"></span>
	    </div>
	  </div>
	  <div class="weui-form-preview__bd">
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">供货单位</label>
	      <span class="weui-form-preview__value">${shop.name }</span>
	    </div>
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">地址</label>
	      <span class="weui-form-preview__value">${shop.name }</span>
	    </div>
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">电话</label>
	      <span class="weui-form-preview__value">${shop.link_mobile }</span>
	    </div>
	  </div>
	</div> 
	<br>
   	<div class="weui-footer">
 		<p class="weui-footer__text" style="color:red;">供货方承诺：以下商品已履行进货检查验收法定程序，索验票证齐全</p>
	</div>
      <div class="weui-panel">
        <div class="weui-panel__hd">购销列表</div>
        <div class="weui-panel__bd">
			<c:forEach items="${list }" var="l" varStatus="status">
	          <div class="weui-media-box weui-media-box_text"> 
	            <h4 class="weui-media-box__title">${l.name }<span style="font-size: 13px;color:#999;float:right;">条码：${l.barcode }</span></h4> 
	            <p class="weui-media-box__desc">数量 ${l.number}（${l.unit}） x 单价 （元） = 金额 （元）</p>
	            <ul class="weui-media-box__info">
	              <li class="weui-media-box__info__meta">生产日期:
	              	<jsp:useBean id="production_time" class="java.util.Date" />
					<jsp:setProperty name="production_time" property="time" value="${l.production_time }"/>
					<fmt:formatDate value="${production_time}" type="date" dateStyle="long"/>
	              </li>
	              <li class="weui-media-box__info__meta">保质期： （天）</li>
	              <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">序号:${status.index +1}</li>
	            </ul>
	          </div>
			</c:forEach>
			<div class="weui-media-box weui-media-box_text"> 
	            <h4 class="weui-media-box__title">合计金额：（元）</h4> 
            </div> 
            <!-- <div class="weui-loadmore weui-loadmore_line">
			  <span class="weui-loadmore__tips">到底了</span>
			</div> 
            <div class="weui-panel__ft">
	          <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
	            <div class="weui-cell__bd">查看更多</div>
	            <span class="weui-cell__ft"></span>
	          </a>    
	        </div>-->
        </div>
      </div>
      <br>
      
		<div class="weui-footer weui-footer_fixed-bottom">
			<p class="weui-footer__text">甘肃省食品药品监督管理局监制</p>
		</div>
</body>
</html>