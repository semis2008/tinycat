<%@ page pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]--><!-- BEGIN HEAD --><%@ include file="common/global.jsp"%><head><meta charset="utf-8" /><meta content="width=device-width, initial-scale=1.0" name="viewport" /><%@ include file="common/head-content.jsp"%><link href="<%=staticPath%>/css/index.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body>	<%@ include file="common/header.jsp"%>	<input type="hidden" value="<%=contextPath%>" id="contextPath">	<!-- BEGIN CONTAINER -->	<div id="content">		<header class="no-radius image hero-unit" id="banner-head">			<div class="container text-center logo">				<h1>					<img alt="Kalor" src="<%=staticPath%>/img/logo.png">				</h1>			</div>		</header>		<div id="main-wrapper" class="container">			<div class="row">				<div class="span3">					<!-- 活跃用户 -->					<section id="activeUserSec">						<h3 class="major">							<span><i class="icon-user-md"></i> 活跃用户</span>						</h3>						<ul class="unstyled inline">							<c:forEach items="${activeUsers }" var="user">								<li class="padding-mini"><a href="#" title="${user.name }">										<img src="<%=staticPath %>/${user.photo}" class="img-polaroid"										width="32px" height="32px" alt=""> <span										class="activeUserName text-center">${user.name} </span>								</a></li>							</c:forEach>						</ul>						<a class="button button-alt" href="javascript:void(0)">查看全部 <i							class="icon-hand-right"></i>						</a>					</section>					<!-- /活跃用户 -->				</div>			</div>			<div class="span9" id="right-content">				 			</div>		</div>	</div>	<!-- 底部 -->	<%@ include file="common/bottom.jsp"%>	<script src="<%=staticPath%>/js/goTop.js<%=version%>"		type="text/javascript"></script></body></html>