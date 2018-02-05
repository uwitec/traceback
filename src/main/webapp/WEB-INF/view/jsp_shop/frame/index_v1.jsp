<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>质量追溯系统 - 首页</title>
    <link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
    <link href="${path}/resource/Hplus-v.4.1.0/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="${path}/resource/Hplus-v.4.1.0/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="${path}/resource/sweetalert-master/dist/sweetalert.css">
	<script src="${path}/resource/sweetalert-master/dist/sweetalert.min.js"></script>
</head>
<body class="gray-bg">
    <div class="row">
        <div class="col-sm-9">
            <div class="wrapper wrapper-content animated fadeInUp">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="m-b-md">
                                    <!-- <a href="project_detail.html#" class="btn btn-white btn-xs pull-right">编辑企业信息</a> -->
                                    <h2>${shop.name }</h2>
                                </div>
                                <dl class="dl-horizontal">
                                    <dt>评分：</dt><dd><span class="label label-primary">${shop.level }</span></dd>
                                </dl>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-5">
                                <dl class="dl-horizontal">
                                    <dt>企业类型：</dt><dd>${shop.type }</dd>
                                    <dt>企业法人：</dt><dd>${shop.link_man }</dd>
                                    <dt>生产许可证号：</dt><dd>${yylicense.code }</dd>
                                    <dt>发证日期：</dt>
                                    <dd>
	                                    <jsp:useBean id="yystart_time" class="java.util.Date" />
										<jsp:setProperty name="yystart_time" property="time" value="${yylicense.start_time }"/>
										<fmt:formatDate value="${yystart_time}" type="date" dateStyle="long"/>
                                    </dd>
                                    <dt>有效期至：</dt><dd>
                                    	<jsp:useBean id="yyend_time" class="java.util.Date" />
										<jsp:setProperty name="yyend_time" property="time" value="${yylicense.end_time }"/>
										<fmt:formatDate value="${yyend_time}" type="date" dateStyle="long"/>
                                    </dd>
                                </dl>
                            </div>
                            <div class="col-sm-7" id="cluster_info">
                                <dl class="dl-horizontal">
                                    <dt>创建时间：</dt>
                                    <dd>
                                    	<jsp:useBean id="screated" class="java.util.Date" />
										<jsp:setProperty name="screated" property="time" value="${shop.created }"/>
										<fmt:formatDate value="${screated}" type="date" dateStyle="long"/>
                                    </dd>
                                    <dt>手机号码：</dt><dd>${shop.link_mobile}</dd>
                                    <dt>营业执照注册号：</dt><dd>${sclicense.code }</dd>
                                    <dt>发证日期：</dt>
                                    <dd>
                                        <jsp:useBean id="scstart_time" class="java.util.Date" />
										<jsp:setProperty name="scstart_time" property="time" value="${sclicense.start_time }"/>
										<fmt:formatDate value="${scstart_time}" type="date" dateStyle="long"/>
                                    </dd>
                                    <dt>有效期至：</dt>
                                    <dd>
                                        <jsp:useBean id="scend_time" class="java.util.Date" />
										<jsp:setProperty name="scend_time" property="time" value="${sclicense.end_time }"/>
										<fmt:formatDate value="${scend_time}" type="date" dateStyle="long"/>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                        <div class="row m-t-sm">
                            <div class="col-sm-12">
                                <div class="panel blank-panel">
                                    <div class="panel-heading">
                                        <div class="panel-options">
                                            <ul class="nav nav-tabs">
                                                <li><a href="project_detail.html#tab-2" data-toggle="tab">证件图片</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab-2">
                                            	<img style="width: 49.5%;height: 700px;border: 3px solid #39aef5;border-radius: 5px;" alt="" src="${yylicense.img_url }">
                                            	<img style="width: 49.5%;height: 700px;border: 3px solid #39aef5;border-radius: 5px;" alt="" src="${sclicense.img_url }">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="wrapper wrapper-content project-manager">
            	<div style="background: #fff;padding: 15px 20px 20px 20px;"> 
    		        <h4>${shop.address }宣：</h4> 
	                <img src="${path}/resource/img/jglogo.png" class="img-responsive">
	                <p class="small"><br> “加入追溯体系，服务放心消费”，追溯体系建设意义重大，需大家发挥协同力量。<br></p>
	                <h4>系统公告：</h4>
	                <p class="small">食品生产质量追溯云平台 v1.0版本  <a href="javascript:guidance();">系统使用教程</a></p>
            	</div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/bootstrap.min.js?v=3.3.6"></script>


    <!-- 自定义js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/content.js?v=1.0.0"></script>


    <script>
        $(document).ready(function () {

            $('#loading-example-btn').click(function () {
                btn = $(this);
                simpleLoad(btn, true)

                // Ajax example
                //                $.ajax().always(function () {
                //                    simpleLoad($(this), false)
                //                });

                simpleLoad(btn, false)
            });
        });

        function simpleLoad(btn, state) {
            if (state) {
                btn.children().addClass('fa-spin');
                btn.contents().last().replaceWith(" Loading");
            } else {
                setTimeout(function () {
                    btn.children().removeClass('fa-spin');
                    btn.contents().last().replaceWith(" Refresh");
                }, 2000);
            }
        }
        
        //返回 01-12 的月份值  
		function getMonth(date){ 
		  var month = ""; 
		  month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
		  if(month<10){ 
		    month = "0" + month; 
		  } 
		  return month; 
		} 
		//返回01-30的日期 
		function getDay(date){ 
		  var day = ""; 
		  day = date.getDate(); 
		  if(day<10){ 
		    day = "0" + day; 
		  } 
		  return day; 
		}
		
		function dateFormat_2(longTypeDate){ 
		  var dateType = ""; 
		  var date = new Date(); 
		  date.setTime(longTypeDate); 
		  dateType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);//yyyy-MM-dd格式日期
		  return dateType;
		}
		
		function guidance(){
			  swal("正在编写使用教程！", "如遇紧急问题请垂询下方客服");
		}
    </script>

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <!--统计代码，可删除-->
</body>
</html>
