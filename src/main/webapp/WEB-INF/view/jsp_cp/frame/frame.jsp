<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/init.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Apple devices fullscreen -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- Apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>质量追溯系统 管理后台</title>
<link href="${path}/resource/admin/resource/js/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
<link href="${path}/resource/admin/templates/default/css/index.css" rel="stylesheet" type="text/css">
<link href="${path}/resource/admin/resource/font/css/font-awesome.min.css" rel="stylesheet" />
<link rel="shortcut icon" href="${path}/resource/img/favicon.ico"/>
<script type="text/javascript" src="${path}/resource/admin/resource/js/jquery.js"></script>
<script src="${path}/resource/lib/extjs6.0/ext-all.js"></script>
<script type="text/javascript" src="${path}/resource/js/app.js"></script>
<script type="text/javascript">
jcapp.server = '${path}';
</script>
<script type="text/javascript">
var PATH = '${path}';
var ADMIN_TEMPLATES_URL = '${path}/resource/admin/templates/default';
</script>
<link href="${path}/resource/js/jquery.Jcrop/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="admincp-map ui-widget-content" nctype="map_nav" style="display:none;" id="draggable" >
  <div class="title ui-widget-header" >
    <h3>管理中心全部菜单</h3>
    <h5>管理中心菜单预览</h5>
    <span><a nctype="map_off" href="JavaScript:void(0);">X</a></span> </div>
		<div class="content">
			<ul class="admincp-map-nav">
			<c:forEach items="${list }" var="v" varStatus="st">
			<li><a href="javascript:void(0);" data-param="map-${st.index }">${v.name }</a></li>
			</c:forEach>
			</ul>
			<c:forEach items="${sublist }" var="v" varStatus="st">
			<div class="admincp-map-div" data-param="map-${st.index }">
				<c:forEach items="${v.submenu }" var="su">
				<dl>
					<dt>${su.name }</dt>
					<c:forEach items="${su.submenu }" var="f">
					<dd class="">
						<a href="javascript:void(0);" data-param="${f.url }">${f.name }</a><i
							class="fa fa-check-square-o"></i>
					</dd>
					</c:forEach>
				</dl>
				</c:forEach>
			</div>
			</c:forEach>
		</div>
<script>
//固定层移动
$(function(){
	$(".nc-module-menu ul li").eq(0).addClass("active");
	
	//管理显示与隐藏
    $("#admin-manager-btn").click(function () {
    	if ($(".manager-menu").css("display") == "none") {
        	$(".manager-menu").css('display', 'block'); 
			$("#admin-manager-btn").attr("title","关闭快捷管理"); 
			$("#admin-manager-btn").removeClass().addClass("arrow-close");
    	}
      	else {
        	$(".manager-menu").css('display', 'none');
			$("#admin-manager-btn").attr("title","显示快捷管理");
			$("#admin-manager-btn").removeClass().addClass("arrow");
       	}           
 	});
	
	$("#draggable").draggable({
		handle: "div.title"
	});
	$("div.title").disableSelection()

});
function modifyPwd(){
	window.top.workspace.location.href='${path}/view_cp/admin/pwd.html';
}
</script> 
</div>
<div class="admincp-header">
  <div class="bgSelector"></div>
  <div id="foldSidebar"><i class="fa fa-outdent " title="展开/收起侧边导航"></i></div>
  <div class="admincp-name">
    <h1></h1>
    <h2>质量追溯系统 后台</h2>
  </div>
  <div class="nc-module-menu">
			<ul class="nc-row">
			<c:forEach items="${list }" var="v">
			<li data-param="${v.url }"><a href="javascript:void(0);">${v.name }</a></li>
			</c:forEach>
			</ul> 
		</div>
  <div class="admincp-header-r">
    <div class="manager">
      <dl>
        <dt class="name">${admin.uname }</dt>
        <dd class="group">${admin.name }</dd>
      </dl>
      <span class="avatar">
      <input name="_pic" type="text" class="admin-avatar-file" id="_pic"/>
      <img alt="" nctype="admin_avatar" src="${path}/resource/admin/templates/default/images/login/admin.png"> </span><i class="arrow" id="admin-manager-btn" title="显示快捷管理菜单"></i>
		<div class="manager-menu">
			<div class="title">
				<h4>最后登录</h4>
				<a href="javascript:void(0);" onClick="modifyPwd()"
					class="edit-password">修改密码</a>
			</div>
			<div class="login-date">
				2016-03-31 11:32:54 <span>(IP: 183.185.255.159 )</span>
			</div>
		</div>
	</div>
    <ul class="operate nc-row">
      <li style="display: none !important;" nctype="pending_matters"><a class="toast show-option" href="javascript:void(0);" onClick="$.cookie('commonPendingMatters', 0, {expires : -1});ajax_form('pending_matters', '待处理事项', 'http://tea.vososo.com/admin/index.php?act=common&op=pending_matters', '480');" title="查看待处理事项">&nbsp;<em>0</em></a></li>
      <li><a class="sitemap show-option" nctype="map_on" href="javascript:void(0);" title="查看全部管理菜单">&nbsp;</a></li>
      <li><a class="style-color show-option" id="trace_show" href="javascript:void(0);" title="给管理中心换个颜色">&nbsp;</a></li>
      <!-- <li><a class="homepage show-option" target="_blank" href="http://www.mengxsh.top/" title="新窗口打开官方首页">&nbsp;</a></li> -->
      <li><a class="login-out show-option" href="${path}/logout_cp.htm" title="安全退出管理中心">&nbsp;</a></li>
    </ul>
  </div>
  <div class="clear"></div>
</div>
<div class="admincp-container unfold">
  <div class="admincp-container-left">
    <div class="top-border"><span class="nav-side"></span><span class="sub-side"></span></div>
   	<c:forEach items="${sublist }" var="v">
		<div id="admincpNavTabs_${v.url }" class="nav-tabs">
			<c:forEach items="${v.submenu }" var="se">
			<dl>
				<dt>
					<a href="javascript:void(0);"><span class="${se.icon }"></span>
					<h3>${se.name }</h3></a>
				</dt>
				<dd class="sub-menu">
					<ul>
						<c:forEach items="${se.submenu }" var="f">
							<c:if test="${fn:length(f.url)>0}"><li><a href="javascript:void(0);" data-param="${f.url }">${f.name }</a></li></c:if>
							<c:if test="${fn:length(f.url)<=0}"><li><a href="javascript:void(0);" data-param="">${f.name }</a></li></c:if>
						</c:forEach>
					</ul>
				</dd>
			</dl>
			</c:forEach>
		</div>
	</c:forEach> 
  </div>
  <div class="admincp-container-right">
    <div class="top-border"></div>
    <iframe src="" id="workspace" name="workspace" style="overflow: visible;" frameborder="0" width="100%" height="94%" scrolling="yes" onload="window.parent"></iframe>
  </div>
</div>
<script type="text/javascript" src="${path}/resource/admin/resource/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script>
<script type="text/javascript" src="${path}/resource/admin/resource/js/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${path}/resource/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/resource/admin/resource/js/jquery.bgColorSelector.js"></script>
<script type="text/javascript" src="${path}/resource/admin/resource/js/admincp.js"></script> 
<script type="text/javascript" src="${path}/resource/js/jquery.Jcrop/jquery.Jcrop.js" id="cssfile2"></script>
</body>
</html>
