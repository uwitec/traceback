<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>食品安全追溯</title>
   	<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
    <link href="${path}/resource/rqq/css/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/resource/rqq/css/font-awesome.min.css" rel="stylesheet">
    <link href="${path}/resource/rqq/css/sfq.css" rel="stylesheet">
    <style type="text/css">

    </style>
</head>
<body style="background-color: #f3f3f4;">
<br/>
<div class="container">
        <div class="course-de">
            <div class="panel-title ">
	            <div class="row">
	            	<div class="col-xs-2 col-md-2 col-lg-1 text-align pro_btn">
	            		<a type="button" href="javascript:history.go(-1)" class="btn btn-info btn-lg">
	            			<i class="fa fa-angle-double-left">
	            			</i>
	            		</a>
	            	</div>
	            	<div class="col-xs-10 col-md-10 col-lg-11">
	            	<div class="row">
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 pro-lin">
			        <span>产品名称:</span><span>${production.name }</span>
			        </div>
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 pro-lin">
			        <span>生产日期:</span>
	                	<span>
	                		<jsp:useBean id="production_time" class="java.util.Date" />
							<jsp:setProperty name="production_time" property="time" value="${production.production_time }"/>
							<fmt:formatDate value="${production_time}" type="date" dateStyle="long"/>
	                	</span>
			        </div>
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 pro-lin">
			        <span>生产数量:</span><span>${production.number }${production.unit }</span>
			        </div>
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 pro-lin">
			        <span>产品条码:</span><span>${production.barcode }</span>
			        </div>
			        </div>
	            </div>
	            </div>
	        </div>
        </div>
        <div class="row">
        <div class="col-md-offset-0 col-md-12">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">投料</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>原料名称</td>
                                    <td>原料进货批次</td>
                                    <td>投料数量</td>
                                    <td>配料员</td>
                                    <td>投料时间</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${touliao }" var="tl">
	                                <tr>
	                                    <td>${tl.name }</td>
	                                    <td>${tl.batch }</td>
	                                    <td>${tl.number }${tl.unit }</td>
	                                    <td>${tl.scaleman }</td>
	                                    <td>
	                                    	<jsp:useBean id="dosing_time" class="java.util.Date" />
											<jsp:setProperty name="dosing_time" property="time" value="${tl.dosing_time }"/>
											<fmt:formatDate value="${dosing_time}" type="date" dateStyle="long"/>
	                                    </td>
	                                </tr>
			                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">关键控制点</a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>控制点</td>
                                    <td>值</td>
                                    <td>单位</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${guanjiankzd }" var="kzd">
	                                <tr>
	                                    <td>${kzd.name }</td>
	                                    <td>${kzd.val }</td>
	                                    <td>${kzd.unit }</td>
	                                </tr>
			                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">产品检验</a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>检验项</td>
                                    <td>类别</td>
                                    <td>值</td>
                                    <td>检验人</td>
                                    <td>检验时间</td>
                                    <td>检验结论</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${jianyan }" var="jy">
	                                <tr>
                                    <td>${jy.name }</td>
                                    <td>${jy.type }</td>
                                    <td>${jy.val }</td>
                                    <td>${jy.staff }</td>
                                    <td>
                                    	<jsp:useBean id="jy_time" class="java.util.Date" />
										<jsp:setProperty name="jy_time" property="time" value="${jy.jy_time }"/>
										<fmt:formatDate value="${jy_time}" type="date" dateStyle="long"/>
                                    </td>
                                    <td>${jy.result }</td>
                                </tr>
			                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">生产过程</a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>步骤</td>
                                    <td>工艺名称</td>
                                    <td>开始时间</td>
                                    <td>结束时间</td>
                                    <td>制作人员</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shengcangc }" var="gc">
	                                <tr>
                                    <td>第${gc.step }步</td>
                                    <td>${gc.name }</td>
                                    <td>
                                    	<jsp:useBean id="gcstart_time" class="java.util.Date" />
										<jsp:setProperty name="gcstart_time" property="time" value="${gc.start_time }"/>
										<fmt:formatDate value="${gcstart_time}" type="date" dateStyle="long"/>
                                    </td>
                                    <td>
                                    	<jsp:useBean id="gcend_time" class="java.util.Date" />
										<jsp:setProperty name="gcend_time" property="time" value="${gc.end_time }"/>
										<fmt:formatDate value="${gcend_time}" type="date" dateStyle="long"/>
                                    </td>
                                    <td>${gc.operator }</td>
                                </tr>
			                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${path}/resource/rqq/js/jquery-2.1.4.js"></script>
<script src="${path}/resource/rqq/js/bootstrap.min.js"></script>
<script>
$('#collapseOne').collapse('toggle');
$('#collapseTwo').collapse('toggle');
$('#collapseThree').collapse('toggle');
$('#collapseFour').collapse('toggle');
</script>
</body>
</html>