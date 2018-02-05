<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>食品生产质量追溯</title>

    <!--图标样式和布局-->
   	<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
    <link href="${path}/resource/rqq/css/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/resource/rqq/css/font-awesome.min.css" rel="stylesheet">
    <!--公共样式-->
    <link rel="stylesheet" type="text/css" href="${path}/resource/rqq/css/demo.css">

</head>
<body>
<div class="header">
    <img src="${path}/resource/rqq/img/banner1.jpg" class="img-ban"/>
    <div class="logo1">
    <div class="logo">
        <img src="${path}/resource/rqq/img/logo.png" class="logo-img"/>
    </div>
    <p class="comp">${shop.name }</p>
    </div>
</div>
<div class="demo">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-0 col-md-12">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                   商品信息
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <div class="pro_img text-center">
                                    <%-- <img src="${path}/resource/rqq/img/pro1.png"> --%>
                                </div>
                                <div class="text-tit">
                                    <ul>
                                         <li><span>产品名称:</span><span>${production.name }</span></li>
                                         <li><span>生产日期:</span><span>
                             		         <jsp:useBean id="production_time" class="java.util.Date" />
											 <jsp:setProperty name="production_time" property="time" value="${production.production_time }"/>
											 <fmt:formatDate value="${production_time}" type="date" dateStyle="long"/>
                                         </span></li>
                                         <li><span>产品数量:</span><span>${production.number }${production.unit }</span></li>
                                         <li><span>产品条码:</span><span>${production.barcode }</span></li>
                                     </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    配料信息
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <div class="text-tit">
                                    <ul>
                                        <c:forEach items="${touliao }" var="tl">
                                             <li><span>配料：${tl.name } </span><span>配料员：${tl.scaleman }</span></li>
                                         </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    检验信息
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <td>检验项名称</td>
                                        <td>检验值</td>
                                        <td>检验结论</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
										<c:forEach items="${jianyan }" var="jy">
			                                <tr>
			                                    <td>${jy.name }</td>
			                                    <td>${jy.val }</td>
			                                    <td>${jy.result }</td>
			                                </tr>
				                    	</c:forEach>
                                    </tr>
                                    </tbody>
                                </table>
                                <div>
                                <!--     检验报告：<a href="" style="color:#FF030F;font-size:14px;">查看检验报告</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    工艺流程
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <table class="table table-bordered table-hover">
                                    <c:forEach items="${shengcangc }" var="gc">
                                           <tr>
                                                <td  align="center" style="vertical-align: middle;">${gc.name }</td>
                                                <td>
                                                    <div>开始时间:
														<jsp:useBean id="gcstart_time" class="java.util.Date" />
														<jsp:setProperty name="gcstart_time" property="time" value="${gc.start_time }"/>
														<fmt:formatDate value="${gcstart_time}" type="date" dateStyle="long"/>
													</div>
                                                    <div>结束时间:
														<jsp:useBean id="gcend_time" class="java.util.Date" />
														<jsp:setProperty name="gcend_time" property="time" value="${gc.end_time }"/>
														<fmt:formatDate value="${gcend_time}" type="date" dateStyle="long"/>
													</div>
                                                </td>
                                           </tr>
				                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    企业信息
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                            <div class="panel-body">
                                <div class="text-tit">
                                    <ul>
                                        <li><span>企业类型:</span><span>${shop.type }</span></li>
                                        <li><span>企业法人:</span><span>${shop.link_man }</span></li>
                                        <li><span>生产许可证号:</span><span>${yingyelicense.code }</span></li>
                                        <li><span>营业执照注册号:</span><span>${shengcanlicense.code }</span></li>
                                        <li><span>营业执照:</span><a href="${yingyelicense.img_url }" style="color:#FF030F;font-size:14px;"><span>点击查看营业执照</span></a></li>
                                        <li><span>生产许可证:</span><a href="${shengcanlicense.img_url }" style="color:#FF030F;font-size:14px;"><span>点击查看生产许可证</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${path}/resource/rqq/js/jquery-2.1.4.js" type="text/javascript"></script>
<script src="${path}/resource/rqq/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
/*     window.addEventListener('DOMContentLoaded', function() {
        if (!((/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i).test(navigator.userAgent))) {
            document.documentElement.innerHTML = "<p>请使用手机打开此页面哦~</p>";
        }
    });
    if(!/mobile/i.test(navigator.userAgent)){
        window.location = 'inquiry-detial-pc.html';
    } */
</script>
</body>
</html>
