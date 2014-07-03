<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!-- BEGIN HEAD -->
<%@ include file="../common/global.jsp"%>
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<%@ include file="../common/head-content.jsp"%>
<link href="<%=staticPath%>/css/blog.css" rel="stylesheet"
	type="text/css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<%@ include file="../common/header.jsp"%>
	<input type="hidden" value="<%=contextPath %>" id="contextPath">
	<!-- BEGIN CONTAINER -->
	<div id="content">
		<!-- 设置需要显示的属性 -->
		<div class="container" id="main-wrapper">
			<div class="row margin-top-large">
				<div class="span9">
					<article class="page-set"></article>
					<div class="text-center hide" id="loadingDIV">
						<img alt="loading" src="<%=staticPath%>/img/loading.gif" />
					</div>
				</div>
				<div class="span3">
					<article class="side_ul">
						<section>
							<h3 class="major">
								<span><i class="icon-fire"></i> 最新博文</span>
							</h3>
							<ul class="unstyled side-ul">
								<c:forEach items="${hotDiaries }" var="diary">
									<li>
										<h5>
											<a href="<%=contextPath %>/blog/${diary.id }"><i class="icon-file-alt"></i> <c:choose>
													<c:when test="${fn:length(diary.title) > 20}">
														<c:out value="${fn:substring(diary.title, 0, 20)}......"
															escapeXml="true" />
													</c:when>
													<c:otherwise>
														<c:out value="${diary.title}" escapeXml="true" />
													</c:otherwise>
												</c:choose> </a>
										</h5>
										<ul class="meta inline">
											<li><i class="icon-time"></i><a
												href="javascript:void(0);"> <fmt:formatDate value="${diary.publish_time }" type="date" dateStyle="long"/></a></li>
											<li><i class="icon-comment"></i><a
												href="javascript:void(0);"> ${diary.reply_num}</a></li>
										</ul>
									</li>
								</c:forEach>
							</ul>
							<a class="button button-alt" href="#">查看全部 <i
								class="icon-hand-right"></i>
							</a>
						</section>
						
						<!-- 热门标签 -->
						<section id="hotTagSec">
							<h3 class="major">
								<span><i class="icon-tags"></i> Hot Tags</span>
							</h3>
							<p>
								<c:forEach items="${hotTags }" var="tag">
									<a href="#" class="margin-small tagSize${tag.weight }">${tag.name}</a>
								</c:forEach>
							</p>
							</section>
							<!-- /热门标签 -->
					</article>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<!-- 底部 -->
	<%@ include file="../common/bottom.jsp"%>
	<script src="<%=staticPath%>/js/jquery.ui.widget.js<%=version%>"
		type="text/javascript"></script>
	<script src="<%=staticPath%>/js/jquery.sausage.js<%=version%>"
		type="text/javascript"></script>
	<script src="<%=staticPath%>/js/blogList.js<%=version%>"
		type="text/javascript"></script>
</body>
</html>