<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<%@ include file="../common/global.jsp"%>
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<link href="<%=staticPath%>/css/timeline.css<%=version%>"
	rel="stylesheet" type="text/css" />

<%@ include file="../common/head-content.jsp"%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
	<%@ include file="../common/header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- 设置需要显示的属性 -->

		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<%@ include file="../common/sideBar.jsp"%>
		</div>
		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN STYLE CUSTOMIZER -->
						<div class="color-panel hidden-phone">
							<div class="color-mode-icons icon-color"></div>
							<div class="color-mode-icons icon-color-close"></div>
							<div class="color-mode">
								<p>主题颜色</p>
								<ul class="inline">
									<li class="color-black current color-default"
										data-style="default"></li>
									<li class="color-blue" data-style="blue"></li>
									<li class="color-brown" data-style="brown"></li>
									<li class="color-purple" data-style="purple"></li>
									<li class="color-grey" data-style="grey"></li>
									<li class="color-white color-light" data-style="light"></li>
								</ul>
								<label> <span>布局(Layout)</span> <select
									class="layout-option m-wrap small">
										<option value="fluid" selected>流式布局(Fluid)</option>
										<option value="boxed">盒式布局(Boxed)</option>
								</select>
								</label> <label> <span>标题(Header)</span> <select
									class="header-option m-wrap small">
										<option value="fixed" selected>固定(Fixed)</option>
										<option value="default">默认(Default)</option>
								</select>
								</label> <label> <span>边栏(SideBar)</span> <select
									class="sidebar-option m-wrap small">
										<option value="fixed">固定(Fixed)</option>
										<option value="default" selected>默认(Default)</option>
								</select>
								</label> <label> <span>底部(Footer)</span> <select
									class="footer-option m-wrap small">
										<option value="fixed">固定(Fixed)</option>
										<option value="default" selected>默认(Default)</option>
								</select>
								</label>
							</div>
						</div>
						<!-- END BEGIN STYLE CUSTOMIZER -->

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							<b>Kalor</b> <small>快速、敏捷、不凡 ...</small>
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a
								href="<%=contextPath%>/index">Home</a> <i
								class="icon-angle-right"></i></li>
							<li>关于 <i class="icon-angle-right"></i></li>
							<li>时间线</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->

				<div id="BlogListPage">
					<%@ include file="timeLinePage.jsp"%>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<%@ include file="../common/bottom.jsp"%>
</body>
<!-- END BODY -->
</html>