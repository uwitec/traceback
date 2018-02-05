<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>食品生产质量追溯云平台</title>
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

<body class="fixed-sidebar full-height-layout gray-bg  pace-done skin-1" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span>
                            	<c:if test="${shop.type != '政府机构'}">
	                            	<img alt="image" class="img-circle" width="100px" height="100px" src="${path}/img/${shop.logo_file_id }.htm" />
								</c:if>
                            	<c:if test="${shop.type == '政府机构'}">
	                            	<img alt="image" class="img-circle" src="${path}/resource/img/jglogo.png" />
								</c:if>
                            </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                              	 <span class="block m-t-xs"><strong class="font-bold">${shop.name}</strong></span>
                               	 <span class="block m-t-xs"><strong class="font-bold">${admin.name}</strong> <span style="opacity: 0.8;">${admin.type}</span><b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs"> 
                                <li><a data-toggle="modal" data-target="#myModal">修改密码</a></li>
                                <!-- <li><a class="J_menuItem" href="profile.html">个人资料</a></li> -->
                                <!-- <li><a class="J_menuItem" href="contacts.html">企业信息</a></li> -->
                                <li class="divider"></li>
                                <li><a href="${path}/logout_shop.htm">安全退出</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">质量</div>
                    </li>
                    <c:forEach items="${sublist }" var="v">
                    	<c:if test="${v.submenu == null}">
                           	<li>
		                   		<a class="J_menuItem" href="${path}${v.url }" data-index="8"><i class="fa fa-table"></i><span class="nav-label">${v.name }</span></a>
		               		</li>
						</c:if>
						<c:if test="${v.submenu != null}">
                           	<li>
		                        <a href="#"><i class="fa fa-table"></i> <span class="nav-label">${v.name }</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level">
		                        	<c:forEach items="${v.submenu }" var="se">
		                           		<li><a class="J_menuItem" href="${path}${se.url }">${se.name }</a></li>
		                            </c:forEach>
		                        </ul>
		                    </li>
						</c:if>
                    </c:forEach>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="get" action="https://www.baidu.com/s">
                            <div class="form-group">
                                <input type="text" name="wd" placeholder="请输入您需要查找的内容 …" value="" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="right-sidebar-toggle count-info" data-toggle="dropdown">
                                <i class="fa fa-bell"></i> <span class="label label-primary">0</span>
                            </a>
                        </li>
                        <!-- <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li> -->
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${path}${index.url}">${index.name }</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${path}/logout_shop.htm" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${path}${index.url}" frameborder="0" data-id="${path}${index.url}" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2018 <a href="http://www.sxxlkj.com/" target="_blank">讯龙科技</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">
                    <li class="active"><a data-toggle="tab" href="#tab-1">通知</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-gear"></i>主题</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 最新通知</h3>
                            <small><i class="fa fa-tim"></i> 您当前有0条未读信息</small>
                        </div>
                        <div>
                            <%-- <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="${path}/resource/Hplus-v.4.1.0/img/profile_small.jpg">
                                    </div>
                                    <div class="media-body">系统管理员：您的系统服务费已到期，请尽快缴费<br>
                                    <small class="text-muted">2018年1月18日12:08:41</small>
                                    </div>
                                </a>
                            </div> --%>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
        <div class="small-chat-box fadeInRight animated">

            <div class="heading" draggable="true">
                <small class="chat-date pull-right">
                </small> 客服中心
            </div>
            <div class="content" style="height: 284px;">
                <div class="left">
                    <div class="chat-message active">
                        	工作时间：0351-3853826
                    </div>
                    <div class="chat-message active">
                        	茹工：15703414307
                    </div>
                    <div class="chat-message active"> 
                        	贾工：15034173944
                    </div>
                </div>
            </div>
            <div class="form-chat">
                <div class="input-group input-group-sm">
                    <img style="width: 100%;height: 100%" src="${path}/resource/img/xlkj.jpg" /> 
                </div>
            </div>

        </div>
        <div id="small-chat">
            <span class="badge badge-warning pull-right"></span>
            <a class="open-small-chat">
                <i class="fa fa-comments"></i>
            </a>
        </div>
        <!--mini聊天窗口结束-->
        
        <!--修改密码弹出框  -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                 <form class="form-horizontal m-t" id="signupForm"> 
                     <div class="form-group">
                         <label class="col-sm-3 control-label">当前密码：</label>
                         <div class="col-sm-8">
                             <input id="currentpwd" name="currentpwd" class="form-control" type="password">
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
                             <a class="btn btn-primary" href="javascript:edit_pwd()">提交</a>
                         </div>
                     </div>
                 </form>
            </div> 
        </div>
    </div>
    </div>
    <script type="text/javascript">
    	function edit_pwd(){
    		$.ajax({
                url: "${path}/shop/admin/pwd_form.htm",
                data: $('#signupForm').serialize(),// 你的formid
                success: function(result) {
                	result = $.parseJSON(result);
                	if(result.tip.type == 'success'){
                		$("#myModal").modal('hide');
	                    swal({ 
	                    	  title: result.tip.msg, 
	                    	  text: "请重新登录，将跳转至登录页面。", 
	                    	  type: "success",
	                    	},
	                    	function(){ 
			                    window.location.href="${path}/logout_shop.htm";
	                    	});
                	}else if(result.tip.type == 'error'){
	                    swal(result.tip.msg, "请确认当前密码后重新填写","error");
                	}
                },
                error: function(result) {
                	console.log(result);
                	result = $.parseJSON(result);
					console.log(result);
                	swal("服务器崩溃", "请确认联系管理员","error"); 
                }
            });
    	}
    </script>
    <!-- 全局js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="${path}/resource/Hplus-v.4.1.0/js/contabs.js"></script>
    
 	<!-- jQuery Validation plugin javascript-->
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/validate/messages_zh.min.js"></script>

    <script src="${path}/resource/Hplus-v.4.1.0/js/demo/form-validate-demo.js"></script>

    <!-- 第三方插件 -->
    <script src="${path}/resource/Hplus-v.4.1.0/js/plugins/pace/pace.min.js"></script>

</body>

</html>
